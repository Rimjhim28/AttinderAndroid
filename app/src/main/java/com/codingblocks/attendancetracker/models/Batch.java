package com.codingblocks.attendancetracker.models;

import java.util.ArrayList;

/**
 * Created by piyush0 on 12/12/16.
 */

public class Batch {
    String name;
    private long id;


    public static ArrayList<String> getDummyBatches() {
        ArrayList<String> batches = new ArrayList<>();

        batches.add("Crux");
        batches.add("Pandora");
        batches.add("Launchpad");

        batches.add("Elixir");

        batches.add("Django");
        return batches;

    }

    public Batch(String name) {
        this.name = name;
    }

    public Batch(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
}
