package com.malcolmstone.androidswapi;

import android.net.UrlQuerySanitizer;

import java.util.List;

/**
 * Created by Malcolm.Stone on 30/06/2016.
 */
public class People {
    private int count;
    private List<Person> results;
    private String next;
    private String previous;

    public People(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public List<Person> getResults() {
        return results;
    }

    public String getNext() {
        return next;
    }

    public String getNextPageNumber() {
        UrlQuerySanitizer sanitizer = new UrlQuerySanitizer(getNext());

        return sanitizer.getValue("page");
    }

    public String getPrevious() {
        return previous;
    }

    public String getPreviousPageNumber() {
        UrlQuerySanitizer sanitizer = new UrlQuerySanitizer(getPrevious());

        return sanitizer.getValue("page");
    }
}
