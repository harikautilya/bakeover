package com.example.kautilya.bakeover.Base.Dependices;



import com.example.kautilya.bakeover.ui.main.MainActivity;
import com.example.kautilya.bakeover.ui.main.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


/**
 * Created by kautilya on 01/02/18.
 */
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity provideMainActivity();



}
