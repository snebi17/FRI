package com.example.fifthlab;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;

public class Destinations extends AppCompatActivity {
    private HashMap<String, Destination> destinations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinations);
        destinations = new HashMap() {{
            put("ZDA", new Destination("La Guardia Airport", "New York"));
            put("Anglija", new Destination("London Luton Airport", "London"));
            put("Nizozemska", new Destination("Amsterdam Airport Schiphol", "Amsterdam"));
            put("Maroko", new Destination("Al Massira Airport", "Agadir"));
            put("Japonska", new Destination("Narita International Airport", "Tokyo"));
            put("Portugalska", new Destination("Madeira Airport", "Funchal"));
        }};
    }

    public class Destination {
        String airportName;
        String city;

        Destination(String airportName, String city) {
            this.airportName = airportName;
            this.city = city;
        }
    }
}