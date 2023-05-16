package com.example.lab5;

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
            put("ZDA", new Destination(40.77719879, -73.87259674,"La Guardia Airport", "New York"));
            put("Anglija", new Destination(51.874698638916016,-0.36833301186561584, "London Luton Airport", "London"));
            put("Nizozemska", new Destination(52.308601,4.76389, "Amsterdam Airport Schiphol", "Amsterdam"));
            put("Maroko", new Destination(30.325000762939453,-9.413069725036621, "Al Massira Airport", "Agadir"));
            put("Japonska", new Destination(35.7647018433,140.386001587, "Narita International Airport", "Tokyo"));
            put("Portugalska", new Destination(32.697899,-16.7745,"Madeira Airport", "Funchal"));
        }};
    }

    static class Destination {
        static int id;
        double latitude;
        double longitude;
        String airportName;
        String city;

        Destination(
                double latitude,
                double longitude,
                String airportName,
                String city)
        {
            ++id;
            this.latitude = latitude;
            this.longitude = longitude;
            this.airportName = airportName;
            this.city = city;
        }
    }
}