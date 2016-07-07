package com.malcolmstone.androidswapi;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.malcolmstone.androidswapi.databinding.MainActivityBinding;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private MainActivityBinding binding;
    private Subscription peopleSubscription;
    private PersonListAdapter listAdapter;
    private PersonListViewModel vm = new PersonListViewModel();
    private SwapiApi swapiService;

    private Subscriber<People> getVmSubscriber() {
        return new Subscriber<People>() {
            @Override
            public void onCompleted() {
                vm.isLoading.set(false);
            }

            @Override
            public void onError(Throwable e) {
                vm.setError("Woops something went wrong!");
            }

            @Override
            public void onNext(People people) {
                vm.setPeople(people);
                listAdapter.setItemVms(vm.getItemViewModels());
                listAdapter.notifyDataSetChanged();
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        binding.setVm(vm);

        setupPersonList();

        swapiService = SwapiService.createSwapiService();

        binding.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (peopleSubscription != null) {
                    peopleSubscription.unsubscribe();
                }

                String nextPageNumber = vm.getPeople().getNextPageNumber();

                if (nextPageNumber == null) {
                    return;
                }

                peopleSubscription = swapiService
                        .getPeople(nextPageNumber)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(getVmSubscriber());
            }
        });

        binding.buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (peopleSubscription != null) {
                    peopleSubscription.unsubscribe();
                }

                String previousPageNumber = vm.getPeople().getPreviousPageNumber();

                if (previousPageNumber == null) {
                    return;
                }

                peopleSubscription = swapiService
                        .getPeople(previousPageNumber)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(getVmSubscriber());
            }
        });

        fetchData();
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

    private void fetchData() {
        peopleSubscription = swapiService
                .getPeople()
                .delay(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(getVmSubscriber());
    }
}
