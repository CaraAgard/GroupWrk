package com.example.group07.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.group07.R;

public class LoginActivity extends AppCompatActivity {

    public static final String APP_PREFS = "APPLICATION_PREFERENCES";
    private EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Define the password text box in view
        pass = (EditText) findViewById(R.id.editTextNumberPassword3);

        //Creating password intent to send to main activity
        Intent passwordIntent = new Intent(this, MainActivity.class);
    }

    public void onSubmit(View Submit) {
        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);

        //Bring the value from pass into password String
        String password = pass.getText().toString();

        if (sharedPreferences.getString() == password) { //figure out how sharedPreferences.getString works
            //do stuff
        } else {
            Toast.makeText(this, "The PIN is incorrect, try again.");
        }
    }
}