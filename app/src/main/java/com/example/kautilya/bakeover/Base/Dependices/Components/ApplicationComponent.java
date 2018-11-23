package com.example.kautilya.bakeover.Base.Dependices.Components;

import android.app.Application;
import android.content.Context;

import com.movies.book.App;
import com.movies.book.Base.DBHelper;
import com.movies.book.Base.DataManager;
import com.movies.book.Base.Dependices.ActivityBuilder;
import com.movies.book.Base.Dependices.Modules.ApplicationModule;
import com.movies.book.Base.SharedPrefs;
import com.movies.book.Base.annotations.ApplicationContext;
import com.movies.book.storage.BaseDataPackage;

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
