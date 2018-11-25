package com.example.kautilya.bakeover.ui.step;

import com.example.kautilya.bakeover.Base.DataManager;
import com.example.kautilya.bakeover.Base.rx.SchedulerProvider;
import com.example.kautilya.bakeover.storage.BaseDataPackage;

import dagger.Module;
import dagger.Provides;

@Module
public class StepModule {


    @Provides
    StepViewModel provideViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, BaseDataPackage baseDataPackage) {
        return new StepViewModel(dataManager, schedulerProvider, baseDataPackage);
    }

    @Provides
    StepNavigator provideNavigator(StepActivity stepActivity) {
        return stepActivity;
    }
}
