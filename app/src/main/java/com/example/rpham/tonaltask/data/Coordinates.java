package com.example.rpham.tonaltask.data;

import com.google.gson.annotations.SerializedName;

public class Coordinates {

    @SerializedName("lat")
    private float latitude;

    @SerializedName("lon")
    private float longitude;

    public Coordinates(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }
}
