package com.example.group07.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.group07.classes.DatabaseFacade;
import com.example.group07.classes.Entry;
import com.example.group07.R;

/**
 * View Activity: Where the user will see a entry in full view
 */
public class ViewActivity extends AppCompatActivity {

    private String TAG = "ViewActivity";
    private TextView dateView;
    private TextView bodyView;
    private TextView titleView;

    private String dateStr;
    private String bodyStr;
    private String titleStr;
    private String idStr;

    // firebase reference
    DatabaseFacade database;

    /**
     * Loads in data from the BrowseActivity and gets the screen ready for display.
     * @param savedInstanceState stuff
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        setTitle("View Entry");
        Log.d(TAG, "ViewActivity Function");

        // set database
        database = new DatabaseFacade("testAuthor");

        setViews();
        // get views and set with data from intent
        getData();
        setTextInViews();
    }

    private void setViews() {
        dateView = findViewById(R.id.mainDate);
        titleView = findViewById(R.id.viewTitle);
        bodyView = findViewById(R.id.mainBody);
    }

    /**
     * Gets the data from the intent
     */
    private void getData() {
        dateStr = getIntent().getStringExtra(Entry.dateKey);
        bodyStr = getIntent().getStringExtra(Entry.bodyKey);
        titleStr = getIntent().getStringExtra(Entry.titleKey);
        idStr = getIntent().getStringExtra(Entry.idKey);
    }

    /**
     * Sets the views with the text
     */
    private void setTextInViews() {
        dateView.setText(dateStr);
        bodyView.setText(bodyStr);
        titleView.setText(titleStr);
    }

    /**
     * Delete an entry with its id
     * @param view to delete the entry
     */
    public void deleteEntry(View view) {
        // get reference to database and the entry id then delete data by
        // databaseReferenceToListOfEntries.child(entryID).removeValue();
        String id = getIntent().getStringExtra("id");
        database.removeEntry(id);

        // todo: ask the user to confirm that they want to delete the entry with a pop-up
        dateView.setText("");
        bodyView.setText("");
        titleView.setText("");

        backToBrowse();
    }

    /**
     * Go to Edit Activity
     */
    public void goToEditActivity(View view) {
        Intent intent = new Intent(this, EditActivity.class);
        // startActivity(intent);
        intent.putExtra(Entry.idKey, idStr);
        intent.putExtra(Entry.titleKey, titleStr);
        intent.putExtra(Entry.bodyKey, bodyStr);
        intent.putExtra(Entry.dateKey, dateStr);
        startActivity(intent);
    }

    /**
     * This function will get you back to the BrowseActivity
     * @param view to get back to Browse
     */
    public void backToBrowse(View view) {
        Intent intent = new Intent(this, BrowseActivity.class);
        startActivity(intent);
    }

    public void backToBrowse() {
        backToBrowse(null);
    }
}