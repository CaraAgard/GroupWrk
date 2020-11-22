package com.example.group07.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.group07.R;
import com.example.group07.classes.Entry;

/**
 * MainActivity: The screen where the app will start
 *
 * This screen will have text edit objects where the user will be able to create a new entry
 * and save it. Also includes the a button to open BrowseActivity.
 */
public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    public static final String Browse_Part = "Browse_Part";
    private Object EditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Really, mine is better!");
        System.out.println("me");

        setTitle("Gratitude App");
        Log.d(TAG, "onCreate");
    }


    /**
     * How a new entry will be saved
     * @param view stuff
     */
    public void saveNewEntry(View view) {
        Entry entry;

        Log.d(TAG , "saveNewEntry");
    }

    /**
     * How the user will get to the BrowseActivity
     * @param view stuff
     */
    public void openBrowseActivity(View view) {


        //Intent Implementation Here.
        Intent browseIntent = new Intent(this, BrowseActivity.class);
        startActivity(browseIntent);



        Log.d(TAG, "openBrowseActivity: Listview intent");
    }


}