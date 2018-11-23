package com.example.kautilya.bakeover.views;

import android.support.v4.view.ViewPager;


/**
 * Created by kautilya on 19/01/18.
 * Used for the indication of the view pager
 */
public interface PageIndicator extends ViewPager.OnPageChangeListener {


    /**
     * Used to bind the viewpager
     *
     * @param pager view pager
     */
    void setUpViewPager(ViewPager pager);

    /**
     * Setup the initial postion of the viewPager
     *
     * @param pager  view pager
     * @param initialPosition current item
     */
    void setUpViewPager(ViewPager pager, int initialPosition);

    /**
     * <p>Set the current page of both the ViewPager and indicator.</p>
     * <p>
     * <p>This <strong>must</strong> be used if you need to set the page before
     * the views are drawn on screen (e.g., default start page).</p>
     *
     * @param position current item
     * @throws IllegalStateException if {@link #setUpViewPager(ViewPager)} is not called first
     */
    void setCurrentItem(int position);

    /**
     * bind the listener
     *
     * @param pageChangeListener listener
     */
    void setupOnPageChangeListener(ViewPager.OnPageChangeListener pageChangeListener);

    /**
     * Notify that data has changed
     */
    void notifyDataSetChanged();


}