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

    private String TAG = "PreLoginActivity";

    public static final String PASS_PREFS = "PASS_PREFERENCES";
    public static final String PIN_KEY = "PreLoginActivity_pin_key";
    private EditText pinObj;
    private EditText pinConfirmObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_login);

        int count = 0;

        pinObj = (EditText) findViewById(R.id.editTextNumberPassword);
        pinConfirmObj = (EditText) findViewById(R.id.editTextNumberPassword2);

        String pinString = pinObj.toString();
        String pinConfirmString = pinConfirmObj.toString();

        if (pinString != null && pinString.equals(pinConfirmString)) {
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

        Log.d(TAG,"onSubmit: About to save " + pinObj);

        sharedPreferencesEditor.putString(String.valueOf(pinObj), PASS_PREFS);
        sharedPreferencesEditor.apply();
    }
}