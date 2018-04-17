package com.example.rpham.tonaltask.data;

import com.google.gson.annotations.SerializedName;

public class ForecastMainInfo {

    private float temp;

    @SerializedName("temp_min")
    private float minTemp;

    @SerializedName("temp_max")
    private float maxTemp;

    private int humidity;

    public ForecastMainInfo(float temp, float minTemp, float maxTemp, int humidity) {
        this.temp = temp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.humidity = humidity;
    }

    public float getTemp() {
        return temp;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public int getHumidity() {
        return humidity;
    }
}
