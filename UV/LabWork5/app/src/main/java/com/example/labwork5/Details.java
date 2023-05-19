package com.example.labwork5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Details extends AppCompatActivity {
    List<Passenger> passengers;
    TextView errors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        passengers = new ArrayList<>();

        String city = getIntent().getStringExtra("CITY");
        int image = getIntent().getIntExtra("IMAGE", 0);

        TextView tvCity = findViewById(R.id.textView4);
        ImageView imageView = findViewById(R.id.imageView2);

        tvCity.setText(city);
        imageView.setImageResource(image);

        errors = findViewById(R.id.errors);
    }

    public void onAddPerson(View v) {
        TextView textView = (TextView) findViewById(R.id.fullName);
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        if (!textView.getText().toString().equals("") && datePicker != null) {
            errors.setText("");
            String fullName = textView.getText().toString();
            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth() + 1;
            int year = datePicker.getYear();
            Date date = new GregorianCalendar(year, month - 1, day).getTime();
            Date currDate = new Date();
            long diff = currDate.getTime() - date.getTime();
            CheckBox checkBox = findViewById(R.id.returnTicket);
            boolean returnTicket = checkBox.isChecked();
            passengers.add(new Passenger(fullName, diff, returnTicket));
            textView.setText("");
            checkBox.clearFocus();
        } else {
            errors.setText("Izpolnite vsa polja!");
        }
    }

    public void proceedToCheckout(View v) {
        if (passengers.size() > 0) {
            errors.setText("");
            Intent intent = new Intent(Details.this, Checkout.class);
            intent.putExtra("PASSENGERS", (Serializable) passengers);
            startActivity(intent);
        } else {
            errors.setText("Dodajte vsaj enega potnika.");
        }
    }

    public static class Passenger implements Serializable {
        String fullName;
        long age;
        boolean returnTicket;

        Passenger(String fullName, long age, boolean returnTicket) {
            this.fullName = fullName;
            this.age = age;
            this.returnTicket = returnTicket;
        }
    }
}