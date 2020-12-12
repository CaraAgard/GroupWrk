package com.example.group07.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.group07.classes.DatabaseFacade;
import com.example.group07.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

/**
 * BrowseActivity is the screen on the app where the user will be able to view a list of previous
 * entries that they have created. Will be displayed with a ListView
 */
public class BrowseActivity extends AppCompatActivity {

    private String TAG = "BrowseActivity";
    private String author;
    private Context contextBrowseActivity;
    private DatabaseFacade database;
    private FirebaseRecyclerAdapter firebaseRecyclerAdapter;


    /**
     * Where the magic of getting all the data from the list of entries and displaying them
     * back to the user will happen.
     * @param savedInstanceState stuff
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        setTitle("Browse Entries");

        Log.d(TAG, "onCreate");

        contextBrowseActivity = this;
        author = "testAuthor";

        /* *************************************************** */

        // create a recycler view and set its layout manager
        RecyclerView recyclerView = findViewById(R.id.browseRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        // connection to the database
        database = new DatabaseFacade(author);

        Log.d(TAG, "Creating firebaseRecyclerAdapter");
        firebaseRecyclerAdapter = database.getFirebaseRecyclerAdapter(contextBrowseActivity);

        Log.d(TAG, "setting the adapter");
        Log.d(TAG, "RecyclerAdapter:" + firebaseRecyclerAdapter.toString());
        recyclerView.setAdapter(firebaseRecyclerAdapter);


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in.
        // TODO: Add code to check if user is signed in.
    }

    @Override
    public void onPause() {
        firebaseRecyclerAdapter.stopListening();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        // allows the adapter to update content
        firebaseRecyclerAdapter.startListening();
    }


    public void goToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}