package com.example.rpham.tonaltask.data;

import com.google.gson.annotations.SerializedName;

public class Forecast {

    @SerializedName("main")
    private ForecastMainInfo mainInfo;

    public Forecast(ForecastMainInfo mainInfo) {
        this.mainInfo = mainInfo;
    }

    public ForecastMainInfo getMainInfo() {
        return mainInfo;
    }
}
