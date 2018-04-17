package com.example.rpham.tonaltask.data;

import com.google.gson.annotations.SerializedName;

public class City {

    private long id;

    private String name;

    @SerializedName("coord")
    private Coordinates coordinates;

    private String country;

    public City(long id, String name, Coordinates coordinates, String country) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getCountry() {
        return country;
    }
}
