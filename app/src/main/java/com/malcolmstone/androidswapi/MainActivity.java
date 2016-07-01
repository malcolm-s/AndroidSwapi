package com.malcolmstone.androidswapi;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.malcolmstone.androidswapi.databinding.MainActivityBinding;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private MainActivityBinding binding;
    private PersonRepository repository;
    private SwapiApi swapiService;
    private Subscription peopleSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);

        swapiService = SwapiService.createSwapiService();

        peopleSubscription = swapiService.getPeople()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<People>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(People people) {
                        Log.d("App", "onNext: " + people.getCount());
                    }
                });

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
