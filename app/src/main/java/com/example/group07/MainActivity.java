package com.example.group07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Really, mine is better!");
        System.out.println("me");
        // Hello from Kira
    }


    public String saveNewEntry(Class<View> view) {
        return "hi";
    }
}