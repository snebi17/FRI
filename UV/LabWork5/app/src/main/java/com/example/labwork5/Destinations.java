package com.example.labwork5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Destinations extends AppCompatActivity implements RecyclerViewInterface {
    ArrayList<DestinationModel> destinationModels = new ArrayList<>();
    int[] destinationImages = {
            R.drawable.ny,
            R.drawable.london,
            R.drawable.amsterdam,
            R.drawable.madrid,
            R.drawable.lizbona,
            R.drawable.atene,
            R.drawable.marakes,
            R.drawable.sydney,
            R.drawable.wellington,
            R.drawable.bangkok
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinations);

        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);

        setupDestinationModels();

        Dest_RecyclerViewAdapter adapter = new Dest_RecyclerViewAdapter(this, destinationModels, this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupDestinationModels() {
        String[] countries = getResources().getStringArray(R.array.countries);
        String[] cities = getResources().getStringArray(R.array.cities);

        for (int i = 0; i < countries.length; i++) {
            destinationModels.add(new DestinationModel(cities[i], countries[i], destinationImages[i]));
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(Destinations.this, Details.class);

        intent.putExtra("CITY", destinationModels.get(position).getCity());
        intent.putExtra("IMAGE", destinationModels.get(position).getImage());

        startActivity(intent);
    }
}