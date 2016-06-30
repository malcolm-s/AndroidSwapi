package com.malcolmstone.androidswapi;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.malcolmstone.androidswapi.databinding.MainActivityBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MainActivityBinding binding;
    private PersonRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        repository = new PersonRepository();

        setupPersonList();
    }

    private void setupPersonList() {
        binding.personList.setLayoutManager(new LinearLayoutManager(this));
        binding.personList.setHasFixedSize(true);
        binding.personList.setAdapter(new PersonListAdapter(getPeopleVMs()));
    }

    private ArrayList<PersonListItemViewModel> getPeopleVMs() {
        ArrayList<PersonListItemViewModel> result = new ArrayList<>();

        for (Person person : repository.getPeople()) {
            result.add(new PersonListItemViewModel(person));
        }

        return result;
    }
}
