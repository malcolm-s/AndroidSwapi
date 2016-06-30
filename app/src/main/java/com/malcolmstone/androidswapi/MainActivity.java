package com.malcolmstone.androidswapi;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.malcolmstone.androidswapi.databinding.MainActivityBinding;
import com.malcolmstone.androidswapi.databinding.MainActivtyBinding;

public class MainActivity extends AppCompatActivity {

    private MainActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);

        binding.personList.setLayoutManager(new LinearLayoutManager(this));
        binding.personList.setHasFixedSize(true);
    }
}
