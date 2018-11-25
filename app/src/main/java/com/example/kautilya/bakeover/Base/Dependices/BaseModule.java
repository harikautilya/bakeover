package com.example.kautilya.bakeover.Base.Dependices;


import com.example.kautilya.bakeover.Base.Classes.BaseActivity;
import com.example.kautilya.bakeover.Base.Classes.BaseNavigator;
import com.example.kautilya.bakeover.Base.Classes.BaseViewModel;
import com.example.kautilya.bakeover.Base.DataManager;
import com.example.kautilya.bakeover.Base.rx.SchedulerProvider;
import com.example.kautilya.bakeover.storage.BaseDataPackage;

public interface BaseModule<T extends BaseViewModel, A extends BaseActivity, L extends BaseNavigator> {


    T provideViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, BaseDataPackage baseDataPackage);

    L provideActivity(A activity);

}
