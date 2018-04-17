package com.example.rpham.tonaltask.data;

import com.google.gson.annotations.SerializedName;

public class Coordinates {

    @SerializedName("lat")
    private double latitude;

    @SerializedName("lon")
    private double longitude;

    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
