package com.example.group07;

import android.view.View;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void subtraction_isCorrect() { assertEquals(4, 8 - 4);}

    // todo: make sure that saveNewEntry does save to something
//    @Test
//    public void saveNewEntryReturnsAString() {
//        MainActivity mainActivity = new MainActivity();
//        String result = mainActivity.saveNewEntry(View.class);
//
//        assertTrue("hi".equals(result));
//    }



}
