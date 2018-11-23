package com.example.kautilya.bakeover.Base.Dependices.Modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.movies.book.App;
import com.movies.book.Base.annotations.ApplicationContext;
import com.movies.book.Base.annotations.DatabaseInfo;
import com.movies.book.Base.rx.AppSchedulerProvider;
import com.movies.book.Base.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kautilya on 31/01/18.
 */
@Module
public class ApplicationModule {
    private App mApplication;
    private static final String spInstant = "AppPref";
    private static final String DATABASE_NAME = "movieData";

    public ApplicationModule(App application) {
        this.mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return DATABASE_NAME;
    }

    @Provides
    @DatabaseInfo
    Integer provideDatabaseVersion() {
        return 1;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    SharedPreferences provideSharedPrefs() {
        return mApplication.getSharedPreferences(spInstant, Context.MODE_PRIVATE);
    }

}
