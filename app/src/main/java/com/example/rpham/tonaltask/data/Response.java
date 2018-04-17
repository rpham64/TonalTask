package com.example.rpham.tonaltask.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    private City city;

    @SerializedName("list")
    private List<Forecast> forecasts;
}
