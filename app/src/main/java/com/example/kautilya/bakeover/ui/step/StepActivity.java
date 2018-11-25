package com.example.kautilya.bakeover.ui.step;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.kautilya.bakeover.BR;
import com.example.kautilya.bakeover.Base.Classes.BaseActivity;
import com.example.kautilya.bakeover.R;
import com.example.kautilya.bakeover.databinding.ActivityStepBinding;
import com.example.kautilya.bakeover.ui.desc.StepDescActivity;
import com.example.kautilya.bakeover.ui.receipe.RecipeActivity;
import com.example.kautilya.bakeover.utils.Constants;
import com.example.kautilya.bakeover.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class StepActivity extends BaseActivity<ActivityStepBinding, StepViewModel, StepNavigator> implements StepNavigator {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_step;
    }

    @Override
    public int getViewModelId() {
        return BR.step_view_model;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        final int value = (int) getIntent().getExtras().get(Constants.IntentContants.RECIPE_ID);

        final List<String> data = new ArrayList<>();
        for (int i = 0; i < Utils.getRecepieById(this, value).getSteps().size(); i++) {
            data.add("Step " + i);
        }

        setTitle(Utils.getRecepieById(StepActivity.this, value).getName());
        getViewDataBinding().steps.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data));
        getViewDataBinding().steps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(StepActivity.this, StepDescActivity.class);
                intent.putExtra(Constants.IntentContants.RECIPE_ID, value);
                intent.putExtra(Constants.IntentContants.STEP_ID, position);
                startActivity(intent);
            }
        });


    }
}
