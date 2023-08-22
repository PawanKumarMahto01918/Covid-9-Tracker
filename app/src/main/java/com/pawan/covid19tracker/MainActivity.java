package com.pawan.covid19tracker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hbb20.CountryCodePicker;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button slot;

    CountryCodePicker countryCodePicker;
    TextView mtodaytotal, mtotal, mactive, mtodayactive, mrecovered, mtodayrecovered, mdeath, mtodaydeath;
    String country;
    TextView mfilter;
    Spinner spinner;
    String[] types = {"cases", "death", "recovered", "active"};
    private List<ModelClass> modelClassList;
    private List<ModelClass> modelClassList2;
    PieChart mpiechart;
    private RecyclerView recyclerView;
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slot = findViewById(R.id.slotscreen);
        slot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, slot.class);
                startActivity(intent);

            }
        });

        getSupportActionBar().hide();
        countryCodePicker = findViewById(R.id.ccp);
        mactive = findViewById(R.id.ActiveCase);
        mtodayactive = findViewById(R.id.TodayActive);
        mdeath = findViewById(R.id.TotalDeath);
        mtodaydeath = findViewById(R.id.TodayDeath);
        mrecovered = findViewById(R.id.RecoveredCase);
        mtodayrecovered = findViewById(R.id.TodayRecovered);
        mtotal = findViewById(R.id.TotalCase);
        mtodaytotal = findViewById(R.id.TodayTotal);

        mpiechart = findViewById(R.id.piechart);
        mfilter = findViewById(R.id.Filter);
        recyclerView = findViewById(R.id.recylerview);
        modelClassList = new ArrayList<>();
        modelClassList2 = new ArrayList<>();


        spinner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);

        ApiUtilites.getApiInterface().getcounrtydata().enqueue(new Callback<List<ModelClass>>() {
            @Override
            public void onResponse(Call<List<ModelClass>> call, Response<List<ModelClass>> response) {

                modelClassList2.addAll(response.body());

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ModelClass>> call, Throwable t) {

            }
        });

        adapter = new Adapter(getApplicationContext(), modelClassList2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        countryCodePicker.setAutoDetectedCountry(true);
        country = countryCodePicker.getSelectedCountryName();
        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {

                country = countryCodePicker.getSelectedCountryName();
                fetchdata();
            }
        });

        fetchdata();

    }

    private void fetchdata() {
        ApiUtilites.getApiInterface().getcounrtydata().enqueue(new Callback<List<ModelClass>>() {
            @Override
            public void onResponse(Call<List<ModelClass>> call, Response<List<ModelClass>> response) {
                modelClassList.addAll(response.body());
                for (int i = 0; i < modelClassList.size(); i++) {
                    if (modelClassList.get(i).getCountry().equals(country)) {
                        mactive.setText((modelClassList.get(i).getActive()));
                        mtodaydeath.setText((modelClassList.get(i).getTodayDeath()));
                        mtodayrecovered.setText((modelClassList.get(i).getTodayRecovered()));
                        mtodaytotal.setText((modelClassList.get(i).getTodayCases()));
                        mtotal.setText((modelClassList.get(i).getCases()));
                        mdeath.setText((modelClassList.get(i).getTodayDeath()));
                        mrecovered.setText((modelClassList.get(i).getRecovered()));

                        int active, total, recovered, death;

                        active = Integer.parseInt(modelClassList.get(i).getActive());
                        total = Integer.parseInt(modelClassList.get(i).getCases());
                        recovered = Integer.parseInt(modelClassList.get(i).getRecovered());
                        death = Integer.parseInt(modelClassList.get(i).getTodayDeath());

                        updategraph(active, total, recovered, death);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ModelClass>> call, Throwable t) {

            }
        });
    }

    private void updategraph(int active, int total, int recovered, int death) {
        mpiechart.clearChart();

        mpiechart.addPieSlice(new PieModel("confirm", total, Color.parseColor("#FFB701")));
        mpiechart.addPieSlice(new PieModel("Active", active, Color.parseColor("#FF4CAF59")));
        mpiechart.addPieSlice(new PieModel("Recovered", recovered, Color.parseColor("#38ACCD")));
        mpiechart.addPieSlice(new PieModel("Death", death, Color.parseColor("#F55C47")));
        mpiechart.startAnimation();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item = types[position];
        mfilter.setText(item);
        adapter.filter(item);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }
}