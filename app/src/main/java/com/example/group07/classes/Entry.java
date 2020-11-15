package com.example.group07.classes;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;

import java.time.LocalDateTime;

/**
 * Stores the data of an entry made by the user
 *
 * Each entry must have a header, body, and date with it. Date is created automatically when the
 * entry is made. Header gives a title to the entry, and body stores the thoughts of the user,
 * intending to be something that they are grateful for.
 */
public class Entry {
    private String header;
    private String body;
    private LocalDateTime date;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Entry(String header, String body) {
        this.header = header;
        this.body = body;
        date = LocalDateTime.now();
        Log.d("Entry", "User entry function worked.");
    }

    public String getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getTime() {
        return date;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
