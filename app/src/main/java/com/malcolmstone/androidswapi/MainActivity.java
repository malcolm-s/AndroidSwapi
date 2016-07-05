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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        binding.setVm(vm);;

        setupPersonList();

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
        peopleSubscription = SwapiService.createSwapiService()
                .getPeople()
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<People>() {
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
                        onError(null);
                        return;
//                        vm.setPeople(people);
//                        listAdapter.setItemVms(vm.getItemViewModels());
//                        listAdapter.notifyDataSetChanged();
                    }
                });
    }
}
