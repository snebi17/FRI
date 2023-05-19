package com.example.fifthlab;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class Details extends AppCompatActivity {
    List<Person> passengers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    public void addPerson(String fullName, int age) {
        passengers.add(new Person(fullName, age));
    }

    public void proceedToCheckout() {

    }

    class Person {
        String fullName;
        int age;

        Person(String fullName, int age) {
            this.fullName = fullName;
            this.age = age;
        }
    }
}