package com.malcolmstone.androidswapi;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by Malcolm.Stone on 30/06/2016.
 */

public class PersonRepository {
    public ArrayList<Person> getPeople() {
        ArrayList<Person> people = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            people.add(i, generatePerson(i));
        }

        return people;
    }

    @NonNull
    private Person generatePerson(int i) {
        return new Person("Ms " + String.valueOf(i), String.valueOf(i));
    }
}
