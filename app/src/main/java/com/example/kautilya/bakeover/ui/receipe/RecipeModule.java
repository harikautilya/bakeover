package com.example.kautilya.bakeover.ui.receipe;

import com.example.kautilya.bakeover.Base.DataManager;
import com.example.kautilya.bakeover.Base.rx.SchedulerProvider;
import com.example.kautilya.bakeover.storage.BaseDataPackage;

import dagger.Module;
import dagger.Provides;

@Module
public class RecipeModule {
    @Provides
    public RecipeViewModel provideViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, BaseDataPackage baseDataPackage) {
        return new RecipeViewModel(dataManager, schedulerProvider, baseDataPackage);
    }

    @Provides
    public RecipeNavigator provideActivity(RecipeActivity activity) {
        return activity;
    }
}
