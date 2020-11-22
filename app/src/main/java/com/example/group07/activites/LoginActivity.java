package com.example.group07.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.group07.R;

import static com.example.group07.activites.PreLoginActivity.PASS_PREFS;
import static com.example.group07.activites.PreLoginActivity.PIN_KEY;

public class LoginActivity extends AppCompatActivity {

    private String TAG = "LoginActivity";

    private EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.d(TAG, "onCreate");

        Intent preLoginIntent = new Intent(this, PreLoginActivity.class);

        //Getting pin from sharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PASS_PREFS, Context.MODE_PRIVATE);
        String pinString = sharedPreferences.getString(PIN_KEY, "");

        if (pinString.isEmpty())
            startActivity(preLoginIntent);

        //Define the password text box in view
        pass = (EditText) findViewById(R.id.editTextNumberPassword3);
    }

    public void onSubmit(View Submit) {
        Log.d(TAG, "onSubmit: start");

        //Creating password intent to send to main activity
        Intent passwordIntent = new Intent(this, MainActivity.class);

        //Getting PASS_PREFS
        SharedPreferences sharedPreferences = getSharedPreferences(PASS_PREFS, Context.MODE_PRIVATE);

        //Bring the value from pass into password String
        String password = pass.getText().toString();

        int count = 0;

        do {
            //Check if PASS_PREFS string is equivalent to password
            if (sharedPreferences.getString(PASS_PREFS, null).equals(password)) {
                startActivity(passwordIntent);
            } else {
                //Increase the counter
                count++;

                //If not, then provide a toast with an error message about how many attempts are left
                Toast.makeText(this, "An error occurred with your PIN, try again. You "
                        + "have " + (5 - count) + "attempts left", Toast.LENGTH_LONG).show();
            }
        } while (count < 5);

        //Do something else here if the user was unable to login after 5 attempts.
    }
}