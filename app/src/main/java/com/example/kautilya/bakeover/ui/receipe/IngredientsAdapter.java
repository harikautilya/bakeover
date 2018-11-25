package com.example.kautilya.bakeover.ui.receipe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kautilya.bakeover.Base.Classes.BaseRecycleViewAdapter;
import com.example.kautilya.bakeover.R;
import com.example.kautilya.bakeover.databinding.ItemIngredientsBinding;
import com.example.kautilya.bakeover.objects.Ingredients;

import java.util.List;

public class IngredientsAdapter extends BaseRecycleViewAdapter<Ingredients, IngredientsAdapter.IngredientsHolder> {


    public IngredientsAdapter(List<Ingredients> data, Context context, boolean filterable, OnLoadMoreListener onLoadMoreListener, RecyclerView recyclerView) {
        super(data, context, filterable, onLoadMoreListener, recyclerView);
    }

    @NonNull
    @Override
    public IngredientsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientsHolder(LayoutInflater.from(context).inflate(R.layout.item_ingredients, parent, false));
    }

    class IngredientsHolder extends BaseRecycleViewAdapter<Ingredients, IngredientsAdapter.IngredientsHolder>.BaseViewHolder<ItemIngredientsBinding> {
        public IngredientsHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(Ingredients object) {
            super.bind(object);
            getViewDataBinding().setIngredent(object);
        }
    }
}
