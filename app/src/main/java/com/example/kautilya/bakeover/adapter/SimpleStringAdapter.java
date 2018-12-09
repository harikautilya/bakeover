package com.example.kautilya.bakeover.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kautilya.bakeover.Base.Classes.BaseRecycleViewAdapter;
import com.example.kautilya.bakeover.R;
import com.example.kautilya.bakeover.databinding.ItemStringBinding;

import java.util.List;

public class SimpleStringAdapter extends BaseRecycleViewAdapter<String, SimpleStringAdapter.SimpleStringHolder> {

    int position;

    public SimpleStringAdapter(List<String> data, Context context, ItemClickListener<String> onClickListener) {
        super(data, context, onClickListener);
        position = -1;
    }

    @NonNull
    @Override
    public SimpleStringHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SimpleStringHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_string, viewGroup, false));
    }

    public void setCurrentSelected(int position) {
        this.position = position;
        notifyDataSetChanged();
    }

    class SimpleStringHolder extends BaseRecycleViewAdapter<String, SimpleStringAdapter.SimpleStringHolder>.BaseViewHolder<ItemStringBinding> {

        public SimpleStringHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(String object) {
            super.bind(object);
            getViewDataBinding().simpleString.setText(object);
            if (getAdapterPosition() == position) {
                getViewDataBinding().simpleString.setTextColor(context.getResources().getColor(android.R.color.holo_red_dark));
            } else {
                getViewDataBinding().simpleString.setTextColor(context.getResources().getColor(android.R.color.black));

            }
        }
    }
}
