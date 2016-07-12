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
    private PersonListViewModel vm;
    private SwapiApi swapiService;
    private Handlers handlers;

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

        setupBindings();

        setupPersonList();

        swapiService = SwapiService.createSwapiService();

        handlers.handlePagingClick(null);

        fetchData(null);
    }

    private void setupBindings() {
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        vm = new PersonListViewModel();
        binding.setVm(vm);

        handlers = new Handlers();
        binding.setHandlers(handlers);
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

    private void fetchData(String pageNumber) {
        if (peopleSubscription != null) {
            peopleSubscription.unsubscribe();
        }

        vm.isLoading.set(true);

        peopleSubscription = swapiService
                .getPeople(pageNumber)
                .delay(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(getVmSubscriber());
    }

    public class Handlers {
        public void onClickPrevious(View view) {
            String previousPageNumber = vm.getPeople().getPreviousPageNumber();
            handlePagingClick(previousPageNumber);
        }

        public void onClickNext(View view) {
            String nextPageNumber = vm.getPeople().getNextPageNumber();
            handlePagingClick(nextPageNumber);
        }

        void handlePagingClick(String pageNumber) {
            if (pageNumber == null) {
                return;
            }

            fetchData(pageNumber);
        }
    }
}
