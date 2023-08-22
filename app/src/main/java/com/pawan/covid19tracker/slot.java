package com.pawan.covid19tracker;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class slot extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Button getresult;

    String baseURL = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin";
    private EditText areaPINcode;
    private Button forwardbtn;
    ProgressBar holdOnProgress;
    private ArrayList<Vaccinemodel> vaccination_center;
    private RecyclerView resultRecyclerView;
    String areaPIN, avlDate;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_slot);

        getresult = findViewById(R.id.result);
        getresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(slot.this, centerResult.class);
                startActivity(intent);
            }
        });

        mapViews();

        onClickSetup();
    }

    private void onClickSetup() {

        forwardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holdOnProgress.setVisibility(View.VISIBLE);
                DialogFragment dp = new picdate();
                dp.show(getSupportFragmentManager(), "pick a date");
            }
        });
    }

    private void mapViews() {
        forwardbtn = findViewById(R.id.getResult);
        holdOnProgress = findViewById(R.id.progress_circular);
        areaPINcode = findViewById(R.id.enterpincode);
        resultRecyclerView = findViewById(R.id.recyclerView);
        resultRecyclerView.setHasFixedSize(true);
        vaccination_center = new ArrayList<Vaccinemodel>();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Calendar k = Calendar.getInstance();
        k.set(Calendar.YEAR, year);
        k.set(Calendar.MONTH, month);
        k.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        dateFormat.setTimeZone(k.getTimeZone());
        String d = dateFormat.format(k.getTime());
        setup(d);
    }

    private void setup(String d) {
        avlDate = d;
        fetchDataNow();
    }

    private void fetchDataNow() {
        vaccination_center.clear();
        areaPIN = areaPINcode.getText().toString();
        String url_api = baseURL + "pincode=" + areaPIN + "&date=" + avlDate;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_api, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray sessonArray = object.getJSONArray("sessions");
                    for (int i = 0; i < sessonArray.length(); i++) {
                        JSONObject sessObject = sessonArray.getJSONObject(i);
                        Vaccinemodel vaccinemodel = new Vaccinemodel();
                        vaccinemodel.setVaccineCenter(sessObject.getString("name"));
                        vaccinemodel.setVaccineCenterAddress(sessObject.getString("address"));
                        vaccinemodel.setVaccinationTiming(sessObject.getString("from"));
                        vaccinemodel.setVaccineCenterTime(sessObject.getString("to"));
                        vaccinemodel.setVaccineName(sessObject.getString("vaccine"));
                        vaccinemodel.setVaccinationCharges(sessObject.getString("fee"));
                        vaccinemodel.setVaccinationAge(sessObject.getString("min_age_limit"));
                        vaccinemodel.setVaccineAvailable(sessObject.getString("available_capacity"));
                        vaccination_center.add(vaccinemodel);
                    }

                    vaccinationinfoadapter Vaccinationinfoadapter = new vaccinationinfoadapter(getApplicationContext(), vaccination_center);
                    resultRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    resultRecyclerView.setAdapter((Vaccinationinfoadapter));
                    holdOnProgress.setVisibility(View.INVISIBLE);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                holdOnProgress.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}