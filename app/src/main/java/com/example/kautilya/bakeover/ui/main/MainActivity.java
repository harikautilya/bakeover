package com.example.kautilya.bakeover.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.kautilya.bakeover.BR;
import com.example.kautilya.bakeover.Base.Classes.BaseActivity;
import com.example.kautilya.bakeover.Base.Classes.BaseRecycleViewAdapter;
import com.example.kautilya.bakeover.R;
import com.example.kautilya.bakeover.adapter.SimpleStringAdapter;
import com.example.kautilya.bakeover.databinding.ActivityMainBinding;
import com.example.kautilya.bakeover.objects.Recepie;
import com.example.kautilya.bakeover.ui.receipe.RecipeActivity;
import com.example.kautilya.bakeover.utils.Constants;
import com.example.kautilya.bakeover.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel, MainNavigator> implements MainNavigator {


    boolean isTablet;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getViewModelId() {
        return BR.main_view;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {

        final List<String> data = new ArrayList<>();
        for (Recepie recepie : Utils.getData(this)) {
            data.add(recepie.getName());
        }

        if (getViewDataBinding().contentMain.itemDetailContainer == null) {
            isTablet = false;
        }

        getViewDataBinding().contentMain.list.setAdapter(
                new SimpleStringAdapter(data, this, new BaseRecycleViewAdapter.ItemClickListener<String>() {
                    @Override
                    public void onItemClick(String object, int position) {
                        if (isTablet) {

                        } else {
                            Intent intent = new Intent(MainActivity.this, RecipeActivity.class);
                            intent.putExtra(Constants.IntentContants.RECIPE_ID, Utils.getData(MainActivity.this).get(position).getId());
                            startActivity(intent);
                        }
                    }

                }));


    }

}
