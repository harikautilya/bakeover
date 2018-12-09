package com.example.kautilya.bakeover.ui.stepvideo;

import com.example.kautilya.bakeover.Base.DataManager;
import com.example.kautilya.bakeover.Base.rx.SchedulerProvider;
import com.example.kautilya.bakeover.storage.BaseDataPackage;

import dagger.Module;
import dagger.Provides;

@Module
public class StepVideoModule {


    @Provides
    StepVideoViewModel stepVideoViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, BaseDataPackage baseDataPackage) {
        return new StepVideoViewModel(dataManager, schedulerProvider, baseDataPackage);
    }

    @Provides
    StepVideoNavigator stepVideoNavigator(StepVideoActivity stepVideoActivity) {
        return stepVideoActivity;
    }
}
