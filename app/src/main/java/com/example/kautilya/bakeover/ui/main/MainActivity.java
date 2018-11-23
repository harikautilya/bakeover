package com.example.kautilya.bakeover.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.kautilya.bakeover.BR;
import com.example.kautilya.bakeover.Base.Classes.BaseActivity;
import com.example.kautilya.bakeover.R;
import com.example.kautilya.bakeover.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel, MainNavigator> implements MainNavigator {


    enum SELECT_DATA_TYPE {
        POPULAR, FAV, TOP
    }


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


    }

}
