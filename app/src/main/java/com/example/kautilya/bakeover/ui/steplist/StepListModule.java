package com.example.kautilya.bakeover.ui.steplist;

import com.example.kautilya.bakeover.Base.DataManager;
import com.example.kautilya.bakeover.Base.rx.SchedulerProvider;
import com.example.kautilya.bakeover.storage.BaseDataPackage;

import dagger.Module;
import dagger.Provides;

@Module
public class StepListModule {


    @Provides
    StepListViewModel provideStepList(BaseDataPackage baseDataPackage, DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new StepListViewModel(dataManager, schedulerProvider, baseDataPackage);
    }

    @Provides
    StepListViewNavigator provideStepListNavigtor(StepListActivity stepListActivity) {
        return stepListActivity;
    }
}
