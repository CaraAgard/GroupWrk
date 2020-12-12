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

public class EditActivity extends AppCompatActivity {

    private final String TAG = "EditActivity";

    private EditText editTitle;
    private EditText editBody;
    private TextView viewDate;

    private String dateStr;
    private String bodyStr;
    private String titleStr;
    private String idStr;


    private String author = "testAuthor";

    DatabaseFacade database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        setTitle("Create new Entry");

        setTitle("Gratitude App");
        Log.d(TAG, "onCreate");

        // set up variables
        database = new DatabaseFacade(author);

        setViews();
        getData();
        setTextInViews();

        editBody.requestFocus();
    }

    private void setViews() {
        viewDate = findViewById(R.id.editDate);
        editTitle = findViewById(R.id.editTitle);
        editBody = findViewById(R.id.editBody);
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
        viewDate.setText(dateStr);
        editBody.setText(bodyStr);
        editTitle.setText(titleStr);
    }

    /**
     * How a new entry will be saved
     * @param view stuff
     */
    @RequiresApi(api = Build.VERSION_CODES.O) // needed for date
    public void updateEntry(View view) {
        titleStr = editTitle.getText().toString();
        bodyStr = editBody.getText().toString();

        database.updateEntry(idStr, titleStr, bodyStr);

        Log.d(TAG , "Updated the " + titleStr + " string");

        // go back to BrowseActivity
        Intent intent = new Intent(this, ViewActivity.class);
        intent.putExtra(Entry.idKey, idStr);
        intent.putExtra(Entry.bodyKey, bodyStr);
        intent.putExtra(Entry.titleKey, titleStr);
        intent.putExtra(Entry.dateKey, dateStr);
        startActivity(intent);
    }

}