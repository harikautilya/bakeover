package com.example.kautilya.bakeover.Base.Dependices;


import com.example.kautilya.bakeover.ui.desc.StepDescFragment;
import com.example.kautilya.bakeover.ui.desc.StepDescProvider;
import com.example.kautilya.bakeover.ui.main.MainActivity;
import com.example.kautilya.bakeover.ui.main.MainModule;
import com.example.kautilya.bakeover.ui.receipe.RecipeActivity;
import com.example.kautilya.bakeover.ui.receipe.RecipeModule;
import com.example.kautilya.bakeover.ui.step.StepActivity;
import com.example.kautilya.bakeover.ui.step.StepModule;
import com.example.kautilya.bakeover.ui.steplist.StepListActivity;
import com.example.kautilya.bakeover.ui.steplist.StepListModule;
import com.example.kautilya.bakeover.ui.stepvideo.StepVideoActivity;
import com.example.kautilya.bakeover.ui.stepvideo.StepVideoModule;
import com.example.kautilya.bakeover.ui.tablet.TabletProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


/**
 * Created by kautilya on 01/02/18.
 */
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {MainModule.class, StepDescProvider.class})
    abstract MainActivity provideMainActivity();

    @ContributesAndroidInjector(modules = RecipeModule.class)
    abstract RecipeActivity provideRecipeActivity();

    @ContributesAndroidInjector(modules = {StepModule.class, StepDescProvider.class})
    abstract StepActivity provideStepActivity();

    @ContributesAndroidInjector(modules = StepListModule.class)
    abstract StepListActivity provideStepListActivity();


    @ContributesAndroidInjector(modules = {StepVideoModule.class, StepDescProvider.class, TabletProvider.class})
    abstract StepVideoActivity provideStepVideoActivity();

}
