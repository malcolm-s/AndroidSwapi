package com.malcolmstone.androidswapi;

/**
 * Created by Malcolm.Stone on 30/06/2016.
 */

public class PersonListItemViewModel {
    private Person person;

    PersonListItemViewModel(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }
}
