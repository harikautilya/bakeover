package com.example.kautilya.bakeover.ui.tablet;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.kautilya.bakeover.BR;
import com.example.kautilya.bakeover.Base.Classes.BaseFragment;
import com.example.kautilya.bakeover.R;
import com.example.kautilya.bakeover.adapter.PageFragmentAdapter;
import com.example.kautilya.bakeover.databinding.FragmentTabletBinding;
import com.example.kautilya.bakeover.ui.desc.StepDescFragment;
import com.example.kautilya.bakeover.ui.main.MainActivity;
import com.example.kautilya.bakeover.ui.main.TableFragmentInteraction;
import com.example.kautilya.bakeover.ui.receipe.IngredientsAdapter;
import com.example.kautilya.bakeover.utils.Constants;
import com.example.kautilya.bakeover.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TabletFragment extends BaseFragment<FragmentTabletBinding, TableViewModel, TabletNavigator> implements TabletNavigator {
    private int currentPage;
    private TableFragmentInteraction tableFragmentInteraction;

    @Override
    public int getBindingVariable() {
        return BR.tablet_fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tablet;
    }

    @Override
    public void init(View view, Bundle savedInstances) {
        final int value = (int) getArguments().getInt(Constants.IntentContants.RECIPE_ID);
        final int position = (int) getArguments().getInt(Constants.IntentContants.POSITION);
        if (!Utils.getRecepieById(getContext(), value).getImage().equals("")) {
            Picasso.with(getContext())
                    .load(Utils.getRecepieById(getContext(), value).getImage())
                    .into(getViewDataBinding().image);
        }
        getViewDataBinding().servings.setText(String.format(getString(R.string.Servings), Utils.getRecepieById(getContext(), value).getServings()));

        getViewDataBinding().ingredents.setLayoutManager(new LinearLayoutManager(getContext()));
        getViewDataBinding().ingredents.setAdapter(new IngredientsAdapter(Utils.getRecepieById(getContext(), value).getIngredients(), getContext(), false, null, null));
        currentPage = 0;
        final List<BaseFragment> baseFragmentList = new ArrayList<>();
        for (int i = 0; i < Utils.getRecepieById(getContext(), value).getSteps().size(); i++) {
            Bundle args = new Bundle();
            args.putInt(Constants.IntentContants.RECIPE_ID, value);
            args.putInt(Constants.IntentContants.STEP_ID, i);
            baseFragmentList.add((BaseFragment) StepDescFragment.instantiate(getContext(), StepDescFragment.class.getName(), args));
        }

        getViewDataBinding().steps.setAdapter(new PageFragmentAdapter(getActivity().getSupportFragmentManager(), baseFragmentList, null));
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

        getViewDataBinding().steps.setCurrentItem(position);

        getViewDataBinding().steps.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (tableFragmentInteraction != null) {
                    tableFragmentInteraction.videoMoved(position);
                }
                currentPage = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof TableFragmentInteraction) {
            tableFragmentInteraction = (TableFragmentInteraction) getActivity();
        } else {
            throw new IllegalStateException("Wrong parent call the fragment");
        }
    }

    @Override
    public int getColor() {
        return 0;
    }
}
