package com.pawan.covid19tracker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    String BASE_URL = "https://corona.lmao.ninja/v2/";

    @GET("countries")
    Call<List<ModelClass>> getcounrtydata();

}
