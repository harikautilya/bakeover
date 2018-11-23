package com.example.kautilya.bakeover.ui.main;



import com.example.kautilya.bakeover.Base.DataManager;
import com.example.kautilya.bakeover.Base.rx.SchedulerProvider;
import com.example.kautilya.bakeover.storage.BaseDataPackage;

import dagger.Module;
import dagger.Provides;

@Module

public class MainModule {

    @Provides
    MainViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, BaseDataPackage baseDataPackage) {
        return new MainViewModel(dataManager, schedulerProvider, baseDataPackage);
    }


    @Provides
    MainNavigator provideMainNavigator(MainActivity mainActivity) {
        return mainActivity;
    }


}
