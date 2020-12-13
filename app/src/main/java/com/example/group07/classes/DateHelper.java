package com.example.group07.classes;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;

public class DateHelper {

    private DateHelper() {
        // prevent someone from creating an instantiation of the class
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getPrettyDate(String date) {
        LocalDateTime localDateTime = LocalDateTime.parse(date);
        String month = localDateTime.getMonth().toString();
        int day = localDateTime.getDayOfMonth();
        int year = localDateTime.getYear();

        month = toTitleCase(month);

        return month + " " + day + ", " + year;
    }

    protected static String toTitleCase(String text) {
        String firstLetter;
        firstLetter = text.substring(0, 1).toUpperCase();
        return firstLetter + text.substring(1).toLowerCase();
    }
}
