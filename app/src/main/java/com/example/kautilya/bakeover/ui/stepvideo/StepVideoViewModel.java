package com.example.kautilya.bakeover.ui.stepvideo;

import android.content.Context;
import android.os.Bundle;

import com.example.kautilya.bakeover.Base.Classes.BaseViewModel;
import com.example.kautilya.bakeover.Base.DataManager;
import com.example.kautilya.bakeover.Base.rx.SchedulerProvider;
import com.example.kautilya.bakeover.storage.BaseDataPackage;

public class StepVideoViewModel extends BaseViewModel<StepVideoNavigator> {

    public StepVideoViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, BaseDataPackage baseDataPackage) {
        super(dataManager, schedulerProvider, baseDataPackage);
    }

    @Override
    public void init(Bundle savedInstanceState, Context context) {

    }
}
