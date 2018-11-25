package com.example.kautilya.bakeover.ui.receipe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.kautilya.bakeover.BR;
import com.example.kautilya.bakeover.Base.Classes.BaseActivity;
import com.example.kautilya.bakeover.R;
import com.example.kautilya.bakeover.databinding.ActivityRecepieBinding;
import com.example.kautilya.bakeover.ui.step.StepActivity;
import com.example.kautilya.bakeover.utils.Constants;
import com.example.kautilya.bakeover.utils.Utils;
import com.squareup.picasso.Picasso;

public class RecipeActivity extends BaseActivity<ActivityRecepieBinding, RecipeViewModel, RecipeNavigator> implements RecipeNavigator {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_recepie;
    }

    @Override
    public int getViewModelId() {
        return BR.recepie_view_model;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        final int value = (int) getIntent().getExtras().get(Constants.IntentContants.RECIPE_ID);


        setTitle(Utils.getRecepieById(RecipeActivity.this, value).getName());
        if (!Utils.getRecepieById(RecipeActivity.this, value).getImage().equals(""))
            Picasso.with(this)
                    .load(Utils.getRecepieById(RecipeActivity.this, value).getImage())
                    .into(getViewDataBinding().image);
        getViewDataBinding().servings.setText(String.format(getString(R.string.Servings), Utils.getRecepieById(RecipeActivity.this, value).getServings()));

        getViewDataBinding().ingredents.setLayoutManager(new LinearLayoutManager(this));
        getViewDataBinding().ingredents.setAdapter(new IngredientsAdapter(Utils.getRecepieById(RecipeActivity.this, value).getIngredients(), this, false, null, null));
        getViewDataBinding().viewSteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(RecipeActivity.this, StepActivity.class);
                intent.putExtra(Constants.IntentContants.RECIPE_ID, value);
                startActivity(intent);
            }
        });

    }
}
