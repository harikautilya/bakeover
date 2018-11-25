package com.example.kautilya.bakeover.Base.Dependices.Components;

import android.app.Application;
import android.content.Context;


import com.example.kautilya.bakeover.App;
import com.example.kautilya.bakeover.Base.DBHelper;
import com.example.kautilya.bakeover.Base.DataManager;
import com.example.kautilya.bakeover.Base.Dependices.ActivityBuilder;
import com.example.kautilya.bakeover.Base.Dependices.Modules.ApplicationModule;
import com.example.kautilya.bakeover.Base.SharedPrefs;
import com.example.kautilya.bakeover.Base.annotations.ApplicationContext;
import com.example.kautilya.bakeover.storage.BaseDataPackage;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;


/**
 * Created by kautilya on 31/01/18.
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class, AndroidSupportInjectionModule.class, ActivityBuilder.class, ApplicationModule.class})
public interface ApplicationComponent {


    void inject(App demoApplication);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    DataManager getDataManager();

    SharedPrefs getPreferenceHelper();

    DBHelper getDbHelper();

    BaseDataPackage getBaseDataPackage();
}
