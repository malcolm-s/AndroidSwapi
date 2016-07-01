package com.malcolmstone.androidswapi;

import java.util.ArrayList;

/**
 * Created by Malcolm.Stone on 01/07/2016.
 */

public class PersonListViewModel {
    private People people;
    private ArrayList<PersonListItemViewModel> itemViewModels;

    public void setPeople(People people) {
        this.people = people;

        ArrayList<PersonListItemViewModel> itemViewModels = new ArrayList<>();

        for (Person person : people.getResults()) {
            itemViewModels.add(new PersonListItemViewModel(person));
        }
     
        setItemViewModels(itemViewModels);
    }

    public ArrayList<PersonListItemViewModel> getItemViewModels() {
        return itemViewModels;
    }

    public void setItemViewModels(ArrayList<PersonListItemViewModel> vms) {
        this.itemViewModels = vms;
    }
}
