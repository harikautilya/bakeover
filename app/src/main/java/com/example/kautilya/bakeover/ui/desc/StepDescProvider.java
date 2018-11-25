package com.example.kautilya.bakeover.ui.desc;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class StepDescProvider {

    @ContributesAndroidInjector(modules = StepDescModule.class)
    abstract StepDescFragment provideNewsFragment();
}
