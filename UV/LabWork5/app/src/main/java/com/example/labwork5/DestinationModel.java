package com.example.labwork5;

public class DestinationModel {
    String city;
    String country;
    int image;

    public DestinationModel(String city, String country, int image) {
        this.city = city;
        this.country = country;
        this.image = image;
    }


    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public int getImage() {
        return image;
    }
}
