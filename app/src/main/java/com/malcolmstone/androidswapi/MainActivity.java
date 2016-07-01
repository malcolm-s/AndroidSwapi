package com.malcolmstone.androidswapi;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.malcolmstone.androidswapi.databinding.MainActivityBinding;

import java.util.ArrayList;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private MainActivityBinding binding;
    private PersonRepository repository;
    private SwapiApi swapiService;
    private Subscription peopleSubscription;
    private PersonListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);

        swapiService = SwapiService.createSwapiService();

        peopleSubscription = swapiService.getPeople()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .map(new Func1<People, ArrayList<PersonListItemViewModel>>() {
                    @Override
                    public ArrayList<PersonListItemViewModel> call(People people) {
                        ArrayList<PersonListItemViewModel> vms = new ArrayList<>();

                        for (Person person : people.getResults()) {
                            vms.add(new PersonListItemViewModel(person));
                        }

                        return vms;
                    }
                })
                .subscribe(new Subscriber<ArrayList<PersonListItemViewModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ArrayList<PersonListItemViewModel> vms) {
                        listAdapter.setVms(vms);
                        listAdapter.notifyDataSetChanged();
                    }
                });

        repository = new PersonRepository();

        setupPersonList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        peopleSubscription.unsubscribe();
    }

    private void setupPersonList() {
        binding.personList.setLayoutManager(new LinearLayoutManager(this));
        binding.personList.setHasFixedSize(true);
        listAdapter = new PersonListAdapter();
        binding.personList.setAdapter(listAdapter);
    }

    private ArrayList<PersonListItemViewModel> getPeopleVMs() {
        ArrayList<PersonListItemViewModel> result = new ArrayList<>();

        for (Person person : repository.getPeople()) {
            result.add(new PersonListItemViewModel(person));
        }

        return result;
    }
}
