package com.malcolmstone.androidswapi;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.malcolmstone.androidswapi.databinding.PersonListItemBinding;

import java.util.ArrayList;

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.ViewHolder> {
    private ArrayList<PersonListItemViewModel> personListItemViewModels = new ArrayList<>();

    public void setItemVms(ArrayList<PersonListItemViewModel> vms) {
        this.personListItemViewModels = vms;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        PersonListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.person_list_item, parent, false);

        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PersonListItemViewModel viewModel = personListItemViewModels.get(position);

        holder.binding.setVm(viewModel);
    }

    @Override
    public int getItemCount() {
        return personListItemViewModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public final PersonListItemBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);

            binding = DataBindingUtil.bind(itemView);
        }
    }
}
