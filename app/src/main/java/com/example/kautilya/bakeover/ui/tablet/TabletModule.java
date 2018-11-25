package com.example.kautilya.bakeover.ui.tablet;

import com.example.kautilya.bakeover.Base.DataManager;
import com.example.kautilya.bakeover.Base.rx.SchedulerProvider;
import com.example.kautilya.bakeover.storage.BaseDataPackage;

import dagger.Module;
import dagger.Provides;

@Module
public class TabletModule {
    @Provides
    TableViewModel provideViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, BaseDataPackage baseDataPackage) {
        return new TableViewModel(dataManager, schedulerProvider, baseDataPackage);
    }

    @Provides
    TabletNavigator provideNavigator(TabletFragment tabletFragment) {
        return tabletFragment;
    }
}
