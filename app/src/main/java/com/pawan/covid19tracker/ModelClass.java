package com.pawan.covid19tracker;

public class ModelClass {

    String cases, todayCases, todayDeath, recovered, todayRecovered, active, country;

    public ModelClass(String cases, String todayCases, String todayDeath, String recovered, String todayRecovered, String active, String country) {
        this.cases = cases;
        this.todayCases = todayCases;
        this.todayDeath = todayDeath;
        this.recovered = recovered;
        this.todayRecovered = todayRecovered;
        this.active = active;
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getTodayDeath() {
        return todayDeath;
    }

    public void setTodayDeath(String todayDeath) {
        this.todayDeath = todayDeath;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getTodayRecovered() {
        return todayRecovered;
    }

    public void setTodayRecovered(String todayRecovered) {
        this.todayRecovered = todayRecovered;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
