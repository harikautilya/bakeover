package com.example.kautilya.bakeover.ui.stepvideo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;

import com.example.kautilya.bakeover.BR;
import com.example.kautilya.bakeover.Base.Classes.BaseActivity;
import com.example.kautilya.bakeover.Base.Classes.BaseRecycleViewAdapter;
import com.example.kautilya.bakeover.R;
import com.example.kautilya.bakeover.adapter.SimpleStringAdapter;
import com.example.kautilya.bakeover.databinding.ActivityStepsVideoBinding;
import com.example.kautilya.bakeover.objects.Recepie;
import com.example.kautilya.bakeover.objects.Steps;
import com.example.kautilya.bakeover.ui.tablet.TabletFragment;
import com.example.kautilya.bakeover.utils.Constants;
import com.example.kautilya.bakeover.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;

public class StepVideoActivity extends BaseActivity<ActivityStepsVideoBinding, StepVideoViewModel, StepVideoNavigator> implements StepVideoNavigator {

    @Inject
    DispatchingAndroidInjector<Fragment> androidInjector;
    private RecyclerView recyclerView;
    private SimpleStringAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_steps_video;
    }

    @Override
    public int getViewModelId() {
        return BR.steps_videos;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {

        recyclerView = findViewById(R.id.list);

        final int value = (int) getIntent().getExtras().get(Constants.IntentContants.RECIPE_ID);

        Recepie recepie = Utils.getRecepieById(this, value);
        List<String> data = new ArrayList<>();
        for (Steps step : recepie.getSteps()) {
            data.add(step.getShortdescription());

        }


        recyclerView.setAdapter(
                adapter = new SimpleStringAdapter(data, this, new BaseRecycleViewAdapter.ItemClickListener<String>() {
                    @Override
                    public void onItemClick(String object, int position) {
                        adapter.setCurrentSelected(position);

                        Bundle args = new Bundle();
                        args.putInt(Constants.IntentContants.RECIPE_ID, value);

                        args.putInt(Constants.IntentContants.POSITION, position);
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.item_detail_container, TabletFragment.instantiate(StepVideoActivity.this, TabletFragment.class.getName(), args));
                        transaction.commit();


                    }

                }));

    }

    @Override
    public void videoMoved(int position) {
        adapter.setCurrentSelected(position);
    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return androidInjector;
    }
}
