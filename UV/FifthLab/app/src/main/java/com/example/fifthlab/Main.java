package com.example.fifthlab;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Main extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDestinations(View v) {
//        setContentView(R.layout.activity_destinations);
        Intent destinations = new Intent(this, Destinations.class);
        destinations.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(destinations);
    }
}