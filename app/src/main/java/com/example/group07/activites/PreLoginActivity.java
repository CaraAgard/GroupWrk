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

    //Intent to go back to Login
    private Intent passwordSetupSuccessIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_login);
        setTitle(null);

        pinObj = (EditText) findViewById(R.id.editTextNumberPassword);
        pinConfirmObj = (EditText) findViewById(R.id.editTextNumberPassword2);
    }

    public void onSubmit(View Submit) {
        // get pins from the user
        String pinString = pinObj.getText().toString();
        Log.d(TAG, "pin is " + pinString);
        String pinConfirmString = pinConfirmObj.getText().toString();
        Log.d(TAG, "pinConfirm is " + pinConfirmString);


        // pin will only save when pin and pinConfirm are equal
        if (!pinString.isEmpty() && pinString.equals(pinConfirmString)) {
            Log.d(TAG,"onSubmit: About to save pin " + pinString);

            // save pin in SharedPreferences
            // get editor first
            SharedPreferences sharedPreferences = getSharedPreferences(PASS_PREFS, Context.MODE_PRIVATE);
            SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
            // save pin via (key, value)
            sharedPreferencesEditor.putString(PIN_KEY, pinString);
            sharedPreferencesEditor.apply();

            // todo: move from source code folder to test code folder
            testPin(pinString);

            Log.d(TAG, "Going back to LoginActivity");
            passwordSetupSuccessIntent = new Intent(this, LoginActivity.class);
            startActivity(passwordSetupSuccessIntent);
        } else {
            Toast.makeText(this, "An error occurred with your PIN, try again.",
                    Toast.LENGTH_LONG).show();
        }
    }


    /**
     * A test to make sure that pin was saved in SharedPreferences.
     *   Should be moved to a testing folder.
     *   Only creates an error message for now.
     */
    private void testPin(String pinCreated) {
        SharedPreferences sharedPreferences = getSharedPreferences(PASS_PREFS, Context.MODE_PRIVATE);
        String pinSaved = sharedPreferences.getString(PIN_KEY, "");

        if (!pinSaved.equals(pinCreated)) {
            Log.e(TAG, "pin saved does not match pin created");
        }
    }
}