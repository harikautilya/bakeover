package com.example.kautilya.bakeover.ui.step;

import android.content.Context;
import android.os.Bundle;

import com.example.kautilya.bakeover.Base.Classes.BaseViewModel;
import com.example.kautilya.bakeover.Base.DataManager;
import com.example.kautilya.bakeover.Base.rx.SchedulerProvider;
import com.example.kautilya.bakeover.storage.BaseDataPackage;

public class StepViewModel extends BaseViewModel<StepNavigator> {
    public StepViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, BaseDataPackage baseDataPackage) {
        super(dataManager, schedulerProvider, baseDataPackage);
    }

    @Override
    public void init(Bundle savedInstanceState, Context context) {

    }
}
