package com.example.kautilya.bakeover.ui.tablet;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TabletProvider {

    @ContributesAndroidInjector(modules = TabletModule.class)
    abstract TabletFragment provideTabletFragment();
}
