package com.malcolmstone.androidswapi;

import java.util.List;

/**
 * Created by Malcolm.Stone on 30/06/2016.
 */
public class People {
    private int count;
    private List<Person> results;

    public People(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public List<Person> getResults() {
        return results;
    }
}
