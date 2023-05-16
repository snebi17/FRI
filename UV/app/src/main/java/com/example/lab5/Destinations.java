package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class Destinations extends AppCompatActivity {
    HashMap<String, String> cardData = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinations);

        cardData.put("Card 1", "Text for Card 1");
        cardData.put("Card 2", "Text for Card 2");
// ...

        LinearLayout cardContainer = findViewById(R.id.cardContainer); // Replace with your parent container's ID

// Iterate over the HashMap
        for (Map.Entry<String, String> entry : cardData.entrySet()) {
            String title = entry.getKey();
            String text = entry.getValue();

            // Inflate the card layout
            View cardView = getLayoutInflater().inflate(R.layout.card_layout, null);

            // Set the title and text in the card layout
            TextView titleTextView = cardView.findViewById(R.id.titleTextView); // Replace with your title TextView's ID
            TextView textTextView = cardView.findViewById(R.id.textTextView); // Replace with your text TextView's ID
            titleTextView.setText(title);
            textTextView.setText(text);

            // Add the card view to the parent container
            cardContainer.addView(cardView);
        }
    }
}