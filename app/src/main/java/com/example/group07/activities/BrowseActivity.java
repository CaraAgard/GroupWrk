package com.example.group07.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.group07.classes.DatabaseFacade;
import com.example.group07.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import java.util.Calendar;

/**
 * BrowseActivity is the screen on the app where the user will be able to view a list of previous
 * entries that they have created. Will be displayed with a ListView
 */
public class BrowseActivity extends AppCompatActivity {
   // MenuInflater inflater = getMenuInflater();

    private String TAG = "BrowseActivity";
    private String author;
    private Context contextBrowseActivity;
    private DatabaseFacade database;
    private FirebaseRecyclerAdapter firebaseRecyclerAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.browse_menu, menu);
        return true;
    }




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

       // findViewById(R.id.view_notification_button).setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
         //       createNotification();

         //   }
      //  });

    }

    private void createNotification() {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 14);
        calendar.set(Calendar.SECOND, 0);

        Intent intent = new Intent(getApplicationContext(), Notification_receiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
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
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.notification:
                createNotification();

                Toast.makeText(this, "Notification selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

        }}