package com.malcolmstone.androidswapi;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.malcolmstone.androidswapi.databinding.MainActivityBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MainActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);

        setupPersonList();
    }

    private void setupPersonList() {
        binding.personList.setLayoutManager(new LinearLayoutManager(this));
        binding.personList.setHasFixedSize(true);
        binding.personList.setAdapter(new PersonListAdapter(getPeopleVMs()));
    }

    private ArrayList<PersonListItemViewModel> getPeopleVMs() {
        ArrayList<PersonListItemViewModel> result = new ArrayList<>();

        for (Person person : getPeople()) {
            result.add(new PersonListItemViewModel(person));
        }

        return result;
    }

    private ArrayList<Person> getPeople() {
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
