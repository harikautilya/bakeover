package com.example.kautilya.bakeover.ui.desc;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.kautilya.bakeover.BR;
import com.example.kautilya.bakeover.Base.Classes.BaseFragment;
import com.example.kautilya.bakeover.R;
import com.example.kautilya.bakeover.databinding.ItemStepDescBinding;
import com.example.kautilya.bakeover.objects.Steps;
import com.example.kautilya.bakeover.utils.Constants;
import com.example.kautilya.bakeover.utils.Utils;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

public class StepDescFragment extends BaseFragment<ItemStepDescBinding, StepDescViewModel, StepDescNavigator> implements StepDescNavigator {


    private void intilizePlayer(String videourl) {
        Uri mediaUri = Uri.parse(videourl);

        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(getContext());
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                Util.getUserAgent(getContext(), "BakeOver"));

        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(mediaUri);
        player.prepare(videoSource);
        getViewDataBinding().stepVideo.setPlayer(player);
    }


    void relasePlayer() {
        if (getViewDataBinding().stepVideo.getPlayer() != null) {
            getViewDataBinding().stepVideo.getPlayer().stop();
            getViewDataBinding().stepVideo.getPlayer().release();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        relasePlayer();
    }

    @Override
    public int getBindingVariable() {
        return BR.desc_view_model;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_step_desc;
    }

    @Override
    public void init(View view, Bundle savedInstances) {

    }

    @Override
    public int getColor() {
        return 0;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final int value = (int) getArguments().get(Constants.IntentContants.RECIPE_ID);
        final int step = (int) getArguments().get(Constants.IntentContants.STEP_ID);

        Steps steps = Utils.getRecepieById(getContext(), value).getSteps().get(step);

        if (!steps.getDescription().equals("")) {
            getViewDataBinding().stepDesc.setText(steps.getDescription());
        } else {
            getViewDataBinding().stepDesc.setVisibility(View.GONE);
        }

        if (!steps.getShortdescription().equals("")) {
            getViewDataBinding().stepShortDesc.setText(steps.getShortdescription());
        } else {
            getViewDataBinding().stepShortDesc.setVisibility(View.GONE);
        }

        if (!steps.getThumbnailurl().equals("")) {

            Picasso.with(getContext())
                    .load(steps.getThumbnailurl())
                    .into(getViewDataBinding().stepImage);

        } else {
            getViewDataBinding().stepImage.setVisibility(View.GONE);
        }
        if (!steps.getVideourl().equals("")) {

            try {

                intilizePlayer(steps.getVideourl());
            } catch (Exception e) {
                Toast.makeText(getContext(), "No Media found", Toast.LENGTH_LONG).show();
            }

        } else {
            getViewDataBinding().stepVideo.setVisibility(View.GONE);
        }
    }
}
