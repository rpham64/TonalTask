package com.example.rpham.tonaltask.data;

public class Forecast {

    private ForecastMainInfo mainInfo;

    public Forecast(ForecastMainInfo mainInfo) {
        this.mainInfo = mainInfo;
    }

    public ForecastMainInfo getMainInfo() {
        return mainInfo;
    }
}
