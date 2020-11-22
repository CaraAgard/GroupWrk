package com.example.group07.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.group07.classes.Entry;
import com.example.group07.R;

import java.util.ArrayList;
import java.util.List;

/**
 * BrowseActivity is the screen on the app where the user will be able to view a list of previous
 * entries that they have created. Will be displayed with a ListView
 */
public class BrowseActivity extends AppCompatActivity {

    private String TAG = "BrowseActivity";

    /**
     * Where the magic of getting all the data from the list of entries and displaying them
     * back to the user will happen.
     * @param savedInstanceState stuff
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        Intent intent = getIntent();
        Log.d(TAG, "onCreate");
    }

    private List<Entry> listOfEntries;

    /**
     * Will take the user to the ViewActivity
     * @param view required to use in xml object i.e. a button
     */
    public void viewEntry(View view) {

        //Implement intent here.
        Intent viewIntent = new Intent(this, ViewActivity.class);
        startActivity(viewIntent);
        Log.d(TAG, "viewEntry");
    }

    /**
     * Loads in the entries from the save file
     * @return listOfEntries
     */
    private List<Entry> loadEntries() {
        Log.d(TAG, "loadEntries");

        return new ArrayList<>();
    }
}