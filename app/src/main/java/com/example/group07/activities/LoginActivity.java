package com.example.group07.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.group07.R;

import static com.example.group07.activities.PreLoginActivity.PASS_PREFS;
import static com.example.group07.activities.PreLoginActivity.PIN_KEY;

public class LoginActivity extends AppCompatActivity {

    private String TAG = "LoginActivity";
    private EditText passwordObj;
    private String pinString;
    private int numAttemptsLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

        //Create a constraint on the input for the passwordObj to only 4 characters
        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter(4);
        passwordObj.setFilters(filterArray);

        // set number of attempts
        numAttemptsLeft = 5;
    }

    public void onSubmit(View Submit) {
        Log.d(TAG, "onSubmit: start");

        //Bring the value from passwordObj into password String
        String password = passwordObj.getText().toString();

        //Check if pinString string is equivalent to password
        if (numAttemptsLeft <= 1) {
            Toast.makeText(this, "Sorry, you exceeded your number of attempts",
                    Toast.LENGTH_LONG).show();
        } else if (pinString.equals(password)) {
            //Creating password intent to send to main activity
           // Intent passwordSuccessfulIntent = new Intent(this, MainActivity.class);
            Intent passwordSuccessfulIntent = new Intent(this, BrowseActivity.class);
            startActivity(passwordSuccessfulIntent);

            //This should stop the user from using the back button to get back to this screen.
            finish();

        } else {
            //Increase the counter
            numAttemptsLeft--;

            //If not, then provide a toast with an error message about how many attempts are left
            Toast.makeText(this, "An error occurred with your PIN, try again. You "
                    + "have " + numAttemptsLeft + " attempts left", Toast.LENGTH_LONG).show();
        }
        //Do something else here if the user was unable to login after 5 attempts.
    }

    public void onReset(View Reset) {
        Log.d(TAG, "onReset:start");

        //Bring the value from passwordObj into password String
        String password = passwordObj.getText().toString();

        if (pinString.equals(password)) {
            Intent preLoginIntent = new Intent(this, PreLoginActivity.class);
            startActivity(preLoginIntent);
        } else {
            Toast.makeText(this, "You need to enter your PIN above in order to reset it.",
                    Toast.LENGTH_LONG).show();
        }
    }
}