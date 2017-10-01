package com.codingblocks.attendancetracker.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by piyush0 on 12/12/16.
 */


public class Batch {
    String name;
    static ArrayList<String> batches = new ArrayList<>();

    public static ArrayList<String> getDummyBatches() {

        batches.add("Crux");
        batches.add("Pandora");
        batches.add("Launchpad");
        batches.add("Elixir");
        batches.add("Django");

        return batches;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Batch(String name) {
        this.name = name;
    }

    public static void addNewBatch(String newBatch){
        batches.add(newBatch);
    }
}
