package com.example.labwork5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.media.MediaCodec;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.List;

public class Checkout extends AppCompatActivity {
    private List<Details.Passenger> passengers;
    private LinearLayout content;
    private int total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Intent i = getIntent();
        passengers = (List<Details.Passenger>) i.getSerializableExtra("PASSENGERS");

        content = findViewById(R.id.content);
        passengers.forEach(passenger -> {
            TextView textView = new TextView(this);
            textView.setText(passenger.fullName);
            content.addView(textView);

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(passenger.age);
            int age = c.get(Calendar.YEAR);

            if (age > 2 && age <= 12) {
                total += passenger.returnTicket ? 75 : 50;
            } else if (age > 12) {
                total += passenger.returnTicket ? 150 : 100;
            }
        });
    }


}