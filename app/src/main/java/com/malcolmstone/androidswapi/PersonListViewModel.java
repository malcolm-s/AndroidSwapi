package com.malcolmstone.androidswapi;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import java.util.ArrayList;

/**
 * Created by Malcolm.Stone on 01/07/2016.
 */

public class PersonListViewModel {
    public final ObservableBoolean isLoading = new ObservableBoolean(true);
    public final ObservableBoolean hasError = new ObservableBoolean(true);
    public final ObservableField<String> error = new ObservableField<>();
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

    public void setError(String message) {
        error.set(message);
        hasError.set(true);
    }
}
