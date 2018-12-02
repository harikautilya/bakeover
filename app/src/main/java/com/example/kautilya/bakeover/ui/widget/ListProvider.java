package com.example.kautilya.bakeover.ui.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.kautilya.bakeover.R;
import com.example.kautilya.bakeover.ui.receipe.RecipeActivity;
import com.example.kautilya.bakeover.utils.Constants;
import com.example.kautilya.bakeover.utils.Utils;

import java.util.ArrayList;

public class ListProvider implements RemoteViewsService.RemoteViewsFactory {

    private Context context;

    public ListProvider(Context context, Intent intent) {
        this.context = context;


    }


    @Override
    public int getCount() {
        return Utils.getData(context).size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /*
     *Similar to getView of Adapter where instead of View
     *we return RemoteViews
     *
     */
    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews remoteView = new RemoteViews(
                context.getPackageName(), R.layout.list_row);

        remoteView.setTextViewText(R.id.heading, Utils.getData(context).get(position).getName());
        Intent intent = new Intent(context, RecipeActivity.class);
        intent.putExtra(Constants.IntentContants.RECIPE_ID, Utils.getData(context).get(position).getId());
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        remoteView.setOnClickPendingIntent(R.id.view_complete, pendingIntent);


        return remoteView;
    }


    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
    }

    @Override
    public void onDestroy() {
    }

}