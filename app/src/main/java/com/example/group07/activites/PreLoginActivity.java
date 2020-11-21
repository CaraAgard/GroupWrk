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

public class PreLoginActivity extends AppCompatActivity {

    public static final String PASS_PREFS = "PASS_PREFERENCES";
    private EditText pin;
    private EditText pinConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_login);

        int count = 0;

        pin = (EditText) findViewById(R.id.editTextNumberPassword);
        pinConfirm = (EditText) findViewById(R.id.editTextNumberPassword2);

        String pinString = pin.toString();
        String pinConfirmString = pinConfirm.toString();

        if (pinString == pinConfirmString) {
            Intent passwordSetupIntent = new Intent(this, LoginActivity.class);

            startActivity(passwordSetupIntent);
        } else {
            Toast.makeText(this, "An error occurred with your PIN, try again.",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void onSubmit(View Submit) {
        SharedPreferences sharedPreferences = getSharedPreferences(PASS_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();

        Log.d("LoginActivity:onSubmit","About to save " + pin);

        sharedPreferencesEditor.putString(String.valueOf(pin), PASS_PREFS);
        sharedPreferencesEditor.apply();
    }
}