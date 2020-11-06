package com.example.group07.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.group07.R;
import com.example.group07.classes.Entry;

/**
 * MainActivity: The screen where the app will start
 *
 * This screen will have text edit objects where the user will be able to create a new entry
 * and save it. Also includes the a button to open BrowseActivity.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Really, mine is better!");
        System.out.println("me");

        setTitle("Gratitude App");
    }


    /**
     * How a new entry will be saved
     * @param view
     */
    public void saveNewEntry(Class<View> view) {
        Entry entry;
    }

    /**
     * How the user will get to the BrowseActivity
     * @param view
     */
    public void openBrowseActivity(View view) {
    }


}