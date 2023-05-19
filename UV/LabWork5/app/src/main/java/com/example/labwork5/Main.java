package com.example.labwork5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDestinations(View v) {
        Intent destinations = new Intent(this, Destinations.class);
        destinations.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(destinations);
    }
}