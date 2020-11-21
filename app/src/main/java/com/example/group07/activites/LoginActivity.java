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

public class LoginActivity extends AppCompatActivity {

    private EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent preLoginIntent = new Intent(this, PreLoginActivity.class);

        //Getting PASS_PREFS
        SharedPreferences sharedPreferences = getSharedPreferences(PASS_PREFS, Context.MODE_PRIVATE);

        if (PASS_PREFS == null)
            startActivity(preLoginIntent);

        //Define the password text box in view
        pass = (EditText) findViewById(R.id.editTextNumberPassword3);
    }

    public void onSubmit(View Submit) {
        //Creating password intent to send to main activity
        Intent passwordIntent = new Intent(this, MainActivity.class);

        //Getting PASS_PREFS
        SharedPreferences sharedPreferences = getSharedPreferences(PASS_PREFS, Context.MODE_PRIVATE);

        //Bring the value from pass into password String
        String password = pass.getText().toString();

        int count = 0;

        do {
            //Check if PASS_PREFS string is equivalent to password
            if (sharedPreferences.getString(PASS_PREFS, null) == password) {
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