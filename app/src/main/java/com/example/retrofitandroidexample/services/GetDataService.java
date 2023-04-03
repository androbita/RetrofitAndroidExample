package com.example.retrofitandroidexample.services;

import com.example.retrofitandroidexample.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("/countries.json")
    public Call<List<Country>> getCountries();
}
