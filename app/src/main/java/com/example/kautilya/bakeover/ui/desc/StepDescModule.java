package com.example.kautilya.bakeover.ui.desc;

import com.example.kautilya.bakeover.Base.DataManager;
import com.example.kautilya.bakeover.Base.rx.SchedulerProvider;
import com.example.kautilya.bakeover.storage.BaseDataPackage;

import dagger.Module;
import dagger.Provides;

@Module
public class StepDescModule {


    @Provides
    StepDescNavigator provideStepDescNavigator(StepDescFragment stepDescFragment) {
        return stepDescFragment;
    }

    @Provides
    StepDescViewModel provideViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, BaseDataPackage baseDataPackage) {
        return new StepDescViewModel(dataManager, schedulerProvider, baseDataPackage);
    }
}
