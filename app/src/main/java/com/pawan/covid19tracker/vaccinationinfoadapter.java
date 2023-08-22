package com.pawan.covid19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class vaccinationinfoadapter extends RecyclerView.Adapter<vaccinationinfoadapter.viewHolder> {

    private LayoutInflater layoutInflater;
    private List<Vaccinemodel> list_vaccine_center;

    public vaccinationinfoadapter(Context mcontext, List<Vaccinemodel> list_vaccine_center) {
        this.layoutInflater = LayoutInflater.from(mcontext);
        this.list_vaccine_center = list_vaccine_center;
    }

    @NonNull
    @Override
    public vaccinationinfoadapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.center_result, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull vaccinationinfoadapter.viewHolder holder, int position) {
        holder.vaccinationCenter.setText(list_vaccine_center.get(position).getVaccineCenter());
        holder.vaccineCenterAddress.setText(list_vaccine_center.get(position).getVaccineCenterAddress());
        holder.vaccineCenterTime.setText(list_vaccine_center.get(position).getVaccinationTiming() + "-" +
                list_vaccine_center.get(position).getVaccineCenterTime());
        holder.vaccinationName.setText(list_vaccine_center.get(position).getVaccineName());
        holder.vaccineAvailable.setText(list_vaccine_center.get(position).getVaccineAvailable());
        holder.vaccinationCharges.setText(list_vaccine_center.get(position).getVaccinationCharges());
        holder.vaccinationAge.setText(list_vaccine_center.get(position).getVaccinationAge());
    }

    @Override
    public int getItemCount() {

        return list_vaccine_center.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView vaccinationCenter;
        TextView vaccinationCharges;
        TextView vaccinationAge;
        TextView vaccinationName;
        TextView vaccineCenterTime;
        TextView vaccineCenterTiming;
        TextView vaccineCenterAddress;
        TextView vaccineAvailable;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            vaccinationAge = itemView.findViewById(R.id.age);
            vaccineAvailable = itemView.findViewById(R.id.Avaliability);
            vaccinationCenter = itemView.findViewById(R.id.centerlocation);
            vaccinationCharges = itemView.findViewById(R.id.VaccinatinFees);
            vaccinationName = itemView.findViewById(R.id.VaccineName);
            vaccineCenterAddress = itemView.findViewById(R.id.centerlocation);
            vaccineCenterTime = itemView.findViewById(R.id.centertiming);
        }
    }
}
