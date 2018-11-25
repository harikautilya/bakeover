package com.example.kautilya.bakeover;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.example.kautilya.bakeover.Base.DataManager;
import com.example.kautilya.bakeover.Base.Dependices.Components.ApplicationComponent;
import com.example.kautilya.bakeover.Base.Dependices.Components.DaggerApplicationComponent;
import com.example.kautilya.bakeover.Base.Dependices.Modules.ApplicationModule;
import com.google.firebase.FirebaseApp;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class App  extends Application implements HasActivityInjector {
    static App instance;
    @Inject
    DataManager dataManager;
    protected ApplicationComponent applicationComponent;

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    public static synchronized App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();


        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        applicationComponent.inject(this);
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }


}
