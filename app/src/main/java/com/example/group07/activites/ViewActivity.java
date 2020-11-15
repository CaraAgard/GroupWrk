package com.example.group07.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.group07.classes.Entry;
import com.example.group07.R;

/**
 * View Activity: Where the user will see a entry in full view
 */
public class ViewActivity extends AppCompatActivity {

    /**
     * Loads in data from the BrowseActivity and gets the screen ready for display.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Entry entry; // this is where we will store the entry data loaded in from the BrowseActivity
        Intent intent = getIntent();
        Log.d("ViewActivity", "ViewActivity Function");
    }




}