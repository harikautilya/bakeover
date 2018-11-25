package com.example.kautilya.bakeover.ui.desc;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.example.kautilya.bakeover.BR;
import com.example.kautilya.bakeover.Base.Classes.BaseActivity;
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

public class StepDescActivity extends BaseActivity<ItemStepDescBinding, StepDescViewModel, StepDescNavigator> implements StepDescNavigator {


    private void intilizePlayer(String videourl) {
        Uri mediaUri = Uri.parse(videourl);

        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(this);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "BakeOver"));

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
    protected void onDestroy() {
        super.onDestroy();
        relasePlayer();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_step_desc;
    }

    @Override
    public int getViewModelId() {
        return BR.desc_view_model;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {

        final int value = (int) getIntent().getExtras().get(Constants.IntentContants.RECIPE_ID);
        final int step = (int) getIntent().getExtras().get(Constants.IntentContants.STEP_ID);

        Steps steps = Utils.getRecepieById(this, value).getSteps().get(step);

        setTitle(Utils.getRecepieById(StepDescActivity.this, value).getName());

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

            Picasso.with(this)
                    .load(steps.getThumbnailurl())
                    .into(getViewDataBinding().stepImage);

        } else {
            getViewDataBinding().stepImage.setVisibility(View.GONE);
        }
        if (!steps.getVideourl().equals("")) {

            try {

                intilizePlayer(steps.getVideourl());
            } catch (Exception e) {
                Toast.makeText(this, "No Media found", Toast.LENGTH_LONG).show();
            }

        } else {
            getViewDataBinding().stepVideo.setVisibility(View.GONE);
        }
    }
}
