package com.example.kautilya.bakeover.ui.main;

import android.content.Intent;
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
import com.example.kautilya.bakeover.databinding.ActivityMainBinding;
import com.example.kautilya.bakeover.objects.Recepie;
import com.example.kautilya.bakeover.ui.receipe.RecipeActivity;
import com.example.kautilya.bakeover.ui.tablet.TabletFragment;
import com.example.kautilya.bakeover.utils.Constants;
import com.example.kautilya.bakeover.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel, MainNavigator> implements MainNavigator {
    @Inject
    DispatchingAndroidInjector<Fragment> androidInjector;


    boolean isTablet;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getViewModelId() {
        return BR.main_view;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {

        final List<String> data = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.list);
        for (Recepie recepie : Utils.getData(this)) {
            data.add(recepie.getName());
        }
        isTablet = false;
        if (findViewById(R.id.item_detail_container) != null) {
            isTablet = true;
        }


        recyclerView.setAdapter(
                new SimpleStringAdapter(data, this, new BaseRecycleViewAdapter.ItemClickListener<String>() {
                    @Override
                    public void onItemClick(String object, int position) {
                        if (isTablet) {
                            Bundle args = new Bundle();
                            args.putInt(Constants.IntentContants.RECIPE_ID, Utils.getData(MainActivity.this).get(position).getId());

                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.item_detail_container, TabletFragment.instantiate(MainActivity.this, TabletFragment.class.getName(), args));
                            transaction.commit();

                        } else {
                            Intent intent = new Intent(MainActivity.this, RecipeActivity.class);
                            intent.putExtra(Constants.IntentContants.RECIPE_ID, Utils.getData(MainActivity.this).get(position).getId());
                            startActivity(intent);
                        }
                    }

                }));


    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return androidInjector;
    }
}
