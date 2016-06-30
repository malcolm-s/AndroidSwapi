package com.malcolmstone.androidswapi;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.malcolmstone.androidswapi.databinding.PersonListItemBinding;

public class PersonListAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        PersonListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.person_list_item, parent, false);

        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private final PersonListItemBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);

            binding = DataBindingUtil.bind(itemView);
        }
    }
}
