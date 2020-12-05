package com.example.group07.classes;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;

/**
 * Stores the data of an entry made by the user
 *
 * Each entry must have a header, body, and date with it. Date is created automatically when the
 * entry is made. Header gives a title to the entry, and body stores the thoughts of the user,
 * intending to be something that they are grateful for.
 */
public class Entry {
    private String id;
    private String title;
    private String body;
    private String date; // firebase cannot convert string to localdatetime

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Entry(String title, String body) {
        this.title = title;
        this.body = body;
        date = LocalDateTime.now().toString();
        Log.d("Entry", "entry function");
    }

    public Entry() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getDate() {
        return date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
