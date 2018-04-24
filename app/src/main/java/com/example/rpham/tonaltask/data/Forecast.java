package com.example.rpham.tonaltask.data;

import com.google.gson.annotations.SerializedName;

public class Forecast {

    @SerializedName("dt")
    private long time;

    @SerializedName("main")
    private ForecastMainInfo mainInfo;

    public Forecast(long time, ForecastMainInfo mainInfo) {
        this.time = time;
        this.mainInfo = mainInfo;
    }

    public long getTime() {
        return time;
    }

    public ForecastMainInfo getMainInfo() {
        return mainInfo;
    }
}
