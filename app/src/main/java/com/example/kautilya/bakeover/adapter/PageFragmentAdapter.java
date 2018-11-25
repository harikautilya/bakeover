package com.example.kautilya.bakeover.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.kautilya.bakeover.Base.Classes.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class PageFragmentAdapter extends FragmentStatePagerAdapter {

    List<BaseFragment> fragments;
    List<Integer> colors;
    List<String> pageTitles;

    public PageFragmentAdapter(FragmentManager fm, List<BaseFragment> fragments, List<String> pageTitles) {
        super(fm);
        colors = new ArrayList<>();
        this.fragments = fragments;
        this.pageTitles = pageTitles;

        for (BaseFragment baseFragment : fragments)
            colors.add(baseFragment.getColor());
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public List<Integer> getColors() {
        return colors;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);
        if (pageTitles != null)
            return pageTitles.get(position);
        return "";
    }
}
