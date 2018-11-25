package com.example.kautilya.bakeover.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.kautilya.bakeover.BR;
import com.example.kautilya.bakeover.Base.Classes.BaseActivity;
import com.example.kautilya.bakeover.R;
import com.example.kautilya.bakeover.databinding.ActivityMainBinding;
import com.example.kautilya.bakeover.objects.Recepie;
import com.example.kautilya.bakeover.ui.receipe.RecipeActivity;
import com.example.kautilya.bakeover.utils.Constants;
import com.example.kautilya.bakeover.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel, MainNavigator> implements MainNavigator {


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

        getViewDataBinding().list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data));
        getViewDataBinding().list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(MainActivity.this, RecipeActivity.class);
                intent.putExtra(Constants.IntentContants.RECIPE_ID, Utils.getData(MainActivity.this).get(position).getId());
                startActivity(intent);
            }
        });

    }

}
