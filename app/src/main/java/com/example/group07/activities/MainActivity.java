package com.example.group07.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.group07.R;
import com.example.group07.classes.DatabaseFacade;
import com.example.group07.classes.Entry;

/**
 * MainActivity: The screen where the app will start
 *
 * This screen will have text edit objects where the user will be able to create a new entry
 * and save it. Also includes the a button to open BrowseActivity.
 */
public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    // todo: get a username from the user?
    // Or automatically create a unique id when they set a pin
    // for the first time
    private String author = "testAuthor";

    private EditText editTitle;
    private EditText editBody;
    private TextView viewDate;

    DatabaseFacade database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Create new Entry");

        setTitle("Gratitude App");
        Log.d(TAG, "onCreate");

        // set up variables
        database = new DatabaseFacade(author);
        editTitle = findViewById(R.id.mainTitle);
        editBody = findViewById(R.id.mainBody);
        viewDate = findViewById(R.id.mainDate);
    }


    /**
     * How a new entry will be saved
     * @param view stuff
     */
    @RequiresApi(api = Build.VERSION_CODES.O) // needed for date
    public void saveNewEntry(View view) {
        String titleStr = editTitle.getText().toString();
        String bodyStr = editBody.getText().toString();

        Entry entry = new Entry(titleStr, bodyStr);

        database.addValue(entry);

        clean();
        Log.d(TAG , "saveNewEntry");
    }

    /**
     * resets editViews to add in a new entry
     */
    private void clean() {
        editBody.setText("");
        editTitle.setText("");

        editTitle.requestFocus();
    }

    /**
     * How the user will get to the BrowseActivity
     * @param view stuff
     */
    public void openBrowseActivity(View view) {
       // Intent Implementation Here.
        Intent browseIntent = new Intent(this, BrowseActivity.class);
        startActivity(browseIntent);

        Log.d(TAG, "openBrowseActivity: Listview intent");
    }
}