package com.pawan.covid19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    int n = 1;
    Context context;
    List<ModelClass> countryList;

    public Adapter(Context context, List<ModelClass> countryList) {
        this.context = context;
        this.countryList = countryList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, null, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        ModelClass modelClass = countryList.get(position);
        if (n == 1) {
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getCases())));
        } else if (n == 2) {
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getRecovered())));
        } else if (n == 3) {
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getTodayDeath())));
        } else {
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getActive())));
        }

        holder.country.setText(modelClass.getCountry());

    }

    @Override
    public int getItemCount() {

        return countryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cases, country;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cases = itemView.findViewById(R.id.countrycase);
            country = itemView.findViewById(R.id.countryname);
        }
    }

    public void filter(String charText) {
        if (charText.equals("cases")) {
            n = 1;
        } else if (charText.equals("recovered")) {
            n = 2;
        } else if (charText.equals("death")) {
            n = 3;
        } else {
            n = 4;
        }
    }


}
