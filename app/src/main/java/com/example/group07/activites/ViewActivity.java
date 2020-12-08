package com.example.group07.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

    /**
     * Loads in data from the BrowseActivity and gets the screen ready for display.
     * @param savedInstanceState stuff
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        setTitle("View Entry");

        Entry entry; // this is where we will store the entry data loaded in from the BrowseActivity
        Intent intent = getIntent();
        Log.d(TAG, "ViewActivity Function");

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
        dateStr = getIntent().getStringExtra("date");
        bodyStr = getIntent().getStringExtra("body");
        titleStr = getIntent().getStringExtra("title");
    }

    /**
     * Sets the views with the text
     */
    private void setTextInViews() {
        dateView.setText(dateStr);
        bodyView.setText(bodyStr);
        titleView.setText(titleStr);
    }
}