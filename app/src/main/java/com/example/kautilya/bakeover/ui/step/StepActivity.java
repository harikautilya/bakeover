package com.example.kautilya.bakeover.ui.step;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.kautilya.bakeover.BR;
import com.example.kautilya.bakeover.Base.Classes.BaseActivity;
import com.example.kautilya.bakeover.Base.Classes.BaseFragment;
import com.example.kautilya.bakeover.R;
import com.example.kautilya.bakeover.adapter.PageFragmentAdapter;
import com.example.kautilya.bakeover.databinding.ActivityStepBinding;
import com.example.kautilya.bakeover.ui.desc.StepDescFragment;
import com.example.kautilya.bakeover.utils.Constants;
import com.example.kautilya.bakeover.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;

public class StepActivity extends BaseActivity<ActivityStepBinding, StepViewModel, StepNavigator> implements StepNavigator {
    @Inject
    DispatchingAndroidInjector<Fragment> androidInjector;

    int currentPage;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_step;
    }

    @Override
    public int getViewModelId() {
        return BR.step_view_model;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        final int value = (int) getIntent().getExtras().get(Constants.IntentContants.RECIPE_ID);
        setTitle(Utils.getRecepieById(StepActivity.this, value).getName());
        currentPage = 0;
        final List<BaseFragment> baseFragmentList = new ArrayList<>();
        for (int i = 0; i < Utils.getRecepieById(StepActivity.this, value).getSteps().size(); i++) {
            Bundle args = new Bundle();
            args.putInt(Constants.IntentContants.RECIPE_ID, value);
            args.putInt(Constants.IntentContants.STEP_ID, i);
            baseFragmentList.add((BaseFragment) StepDescFragment.instantiate(this, StepDescFragment.class.getName(), args));
        }

        getViewDataBinding().steps.setAdapter(new PageFragmentAdapter(getSupportFragmentManager(), baseFragmentList, null));
        getViewDataBinding().next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPage == (baseFragmentList.size() - 1)) {
                    return;
                }
                currentPage++;

                getViewDataBinding().steps.setCurrentItem(currentPage);
            }
        });
        getViewDataBinding().back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPage == 0) {
                    return;
                }
                currentPage--;
                getViewDataBinding().steps.setCurrentItem(currentPage);
            }
        });

        getViewDataBinding().steps.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return androidInjector;
    }
}
