package com.example.kautilya.bakeover.ui.steplist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.kautilya.bakeover.BR;
import com.example.kautilya.bakeover.Base.Classes.BaseActivity;
import com.example.kautilya.bakeover.R;
import com.example.kautilya.bakeover.databinding.ActivtyStepListBinding;
import com.example.kautilya.bakeover.objects.Recepie;
import com.example.kautilya.bakeover.objects.Steps;
import com.example.kautilya.bakeover.ui.step.StepActivity;
import com.example.kautilya.bakeover.utils.Constants;
import com.example.kautilya.bakeover.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class StepListActivity extends BaseActivity<ActivtyStepListBinding, StepListViewModel, StepListViewNavigator> implements StepListViewNavigator {


    @Override
    protected int getLayoutId() {
        return R.layout.activty_step_list;
    }

    @Override
    public int getViewModelId() {
        return BR.step_list_view;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        final int value = (int) getIntent().getExtras().get(Constants.IntentContants.RECIPE_ID);

        Recepie recepie = Utils.getRecepieById(this, value);
        List<String> data = new ArrayList<>();
        for (Steps step : recepie.getSteps()) {
            data.add(step.getShortdescription());

        }
        getViewDataBinding().stepListDesc.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data));
        getViewDataBinding().stepListDesc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(StepListActivity.this, StepActivity.class);
                intent.putExtra(Constants.IntentContants.POSITION, position);

                intent.putExtra(Constants.IntentContants.RECIPE_ID, value);
                startActivity(intent);
            }
        });
    }
}
