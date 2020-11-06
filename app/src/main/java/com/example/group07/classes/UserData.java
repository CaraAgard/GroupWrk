package com.example.group07.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores the data of the user of the app
 *
 * Author stores the name or username of the user. ListOfEntries stores the entries that the user
 * has written.
 */
public class UserData {
    private List<Entry> listOfEntries;
    private String author;

    public UserData(String author) {
        this.author = author;
        listOfEntries = new ArrayList<>();
    }

    public String getAuthor() {
        return author;
    }

    // may want to find a way to return a specific entry and not the whole list of entries
    public List<Entry> getListOfEntries() {
        return listOfEntries;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void addEntry(Entry entry) {
        listOfEntries.add(entry);
    }
}
