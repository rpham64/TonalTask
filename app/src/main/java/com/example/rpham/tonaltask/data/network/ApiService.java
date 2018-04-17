package com.example.rpham.tonaltask.data.network;

import com.example.rpham.tonaltask.data.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("data/2.5/forecast")
    Call<Response> getForecast(@Query("zip") int zipCode);
}
