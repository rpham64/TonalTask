package com.example.rpham.tonaltask.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    private City city;

    @SerializedName("list")
    private List<Forecast> forecasts;

    public Response(City city, List<Forecast> forecasts) {
        this.city = city;
        this.forecasts = forecasts;
    }

    public City getCity() {
        return city;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }
}
