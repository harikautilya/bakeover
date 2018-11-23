package com.example.kautilya.bakeover.ui.main;

import android.content.Context;
import android.os.Bundle;

import com.example.kautilya.bakeover.Base.Classes.BaseViewModel;
import com.example.kautilya.bakeover.Base.DataManager;
import com.example.kautilya.bakeover.Base.rx.SchedulerProvider;
import com.example.kautilya.bakeover.storage.BaseDataPackage;


public class MainViewModel extends BaseViewModel<MainNavigator> {


    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, BaseDataPackage baseDataPackage) {
        super(dataManager, schedulerProvider, baseDataPackage);
    }

    @Override
    public void init(Bundle savedInstanceState, Context context) {

    }


}
