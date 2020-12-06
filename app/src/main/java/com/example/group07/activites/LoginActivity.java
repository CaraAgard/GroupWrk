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

    private EditText passwordObj;
    private String pinString;
    private int numAttemptsLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(null);

        Log.d(TAG, "onCreate");

        Intent preLoginIntent = new Intent(this, PreLoginActivity.class);

        //Getting pin from sharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PASS_PREFS, Context.MODE_PRIVATE);
        pinString = sharedPreferences.getString(PIN_KEY, "");
        Log.d(TAG, "pin is " + pinString);

        if (pinString.isEmpty())
            startActivity(preLoginIntent);

        //Define the password text box in view
        passwordObj = (EditText) findViewById(R.id.editTextNumberPassword3);

        // set number of attempts
        numAttemptsLeft = 5;
    }

    public void onSubmit(View Submit) {
        Log.d(TAG, "onSubmit: start");

        //Bring the value from passwordObj into password String
        String password = passwordObj.getText().toString();
        Log.d(TAG, "user entered: " + password);

        //Check if pinString string is equivalent to password
        if (numAttemptsLeft <= 1) {
            Toast.makeText(this, "Sorry, you exceeded your number of attempts",
                    Toast.LENGTH_LONG).show();
        } else if (pinString.equals(password)) {
            //Creating password intent to send to main activity
            Intent passwordSuccessfulIntent = new Intent(this, MainActivity.class);
            startActivity(passwordSuccessfulIntent);
        } else {
            //Increase the counter
            numAttemptsLeft--;

            //If not, then provide a toast with an error message about how many attempts are left
            Toast.makeText(this, "An error occurred with your PIN, try again. You "
                    + "have " + numAttemptsLeft + " attempts left", Toast.LENGTH_LONG).show();
        }
        //Do something else here if the user was unable to login after 5 attempts.
    }
}