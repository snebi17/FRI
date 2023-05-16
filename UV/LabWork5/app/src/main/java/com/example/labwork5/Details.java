package com.example.labwork5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class Details extends AppCompatActivity {
    List<Passenger> passengers;
    Passenger passenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String city = getIntent().getStringExtra("CITY");
        int image = getIntent().getIntExtra("IMAGE", 0);

        TextView tvCity = findViewById(R.id.textView4);
        ImageView imageView = findViewById(R.id.imageView2);

        tvCity.setText(city);
        imageView.setImageResource(image);
    }

    public void onAddPerson(String fullName, int age) {
        passengers.add(passenger);
    }

    public void proceedToCheckout() {
        Intent intent = new Intent(this, Checkout.class);
        intent.putExtra("PASSENGERS", new Serializable() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
    }

    class Passenger {
        String fullName;
        int age;

        Passenger(String fullName, int age) {
            this.fullName = fullName;
            this.age = age;
        }
    }
}