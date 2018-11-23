package com.example.kautilya.bakeover.Base.Dependices;

import com.movies.book.Base.Classes.BaseActivity;
import com.movies.book.Base.Classes.BaseNavigator;
import com.movies.book.Base.DataManager;
import com.movies.book.Base.rx.SchedulerProvider;
import com.movies.book.storage.BaseDataPackage;

public interface BaseModule<T, A extends BaseActivity,L extends BaseNavigator> {


    T provideViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, BaseDataPackage baseDataPackage);

    L provideActivity(A activity);

}
