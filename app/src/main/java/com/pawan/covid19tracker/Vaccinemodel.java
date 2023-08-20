package com.pawan.covid19tracker;

public class Vaccinemodel {

    private String vaccineCenter;
    private String vaccinationCharges;
    private String vaccinationAge;
    private String vaccinationTiming;
    private String vaccineName;
    private String vaccineCenterTime;
    private String vaccineCenterAddress;
    private String vaccineAvailable;

    public Vaccinemodel() {
    }


    public String getVaccineCenter() {
        return vaccineCenter;
    }

    public void setVaccineCenter(String vaccineCenter) {
        this.vaccineCenter = vaccineCenter;
    }

    public String getVaccinationCharges() {
        return vaccinationCharges;
    }

    public void setVaccinationCharges(String vaccinationCharges) {
        this.vaccinationCharges = vaccinationCharges;
    }

    public String getVaccinationAge() {
        return vaccinationAge;
    }

    public void setVaccinationAge(String vaccinationAge) {
        this.vaccinationAge = vaccinationAge;
    }

    public String getVaccinationTiming() {
        return vaccinationTiming;
    }

    public void setVaccinationTiming(String vaccinationTiming) {
        this.vaccinationTiming = vaccinationTiming;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getVaccineCenterTime() {
        return vaccineCenterTime;
    }

    public void setVaccineCenterTime(String vaccineCenterTime) {
        this.vaccineCenterTime = vaccineCenterTime;
    }

    public String getVaccineCenterAddress() {
        return vaccineCenterAddress;
    }

    public void setVaccineCenterAddress(String vaccineCenterAddress) {
        this.vaccineCenterAddress = vaccineCenterAddress;
    }

    public String getVaccineAvailable() {
        return vaccineAvailable;
    }

    public void setVaccineAvailable(String vaccineAvailable) {
        this.vaccineAvailable = vaccineAvailable;
    }

    public Vaccinemodel(String vaccineCenter, String vaccinationCharges, String vaccinationAge, String vaccinationTiming, String vaccineName, String vaccineCenterTime, String vaccineCenterAddress, String vaccineAvailable) {
        this.vaccineCenter = vaccineCenter;
        this.vaccinationCharges = vaccinationCharges;
        this.vaccinationAge = vaccinationAge;
        this.vaccinationTiming = vaccinationTiming;
        this.vaccineName = vaccineName;
        this.vaccineCenterTime = vaccineCenterTime;
        this.vaccineCenterAddress = vaccineCenterAddress;
        this.vaccineAvailable = vaccineAvailable;
    }
}
