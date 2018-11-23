package com.example.kautilya.bakeover.Base.Classes;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import com.example.kautilya.bakeover.Base.DataManager;
import com.example.kautilya.bakeover.utils.Utils;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;

/**
 * Created by kautilya on 01/02/18.
 */

public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel, N extends BaseNavigator> extends AppCompatActivity implements BaseNavigator, BaseFragment.Callback {

    public final String LOG_TAG = this.getClass().getName();
    private ProgressDialog mProgressDialog;
    protected final int PERMISSION_REQUEST = 101;
    private T mViewDataBinding;

    @Inject
    V mViewModel;

    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentInjector;

    @Inject
    N navigator;

    @Inject
    DataManager dataManager;

    protected DataManager getDataManager() {
        return dataManager;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        performDataBinding();
        getViewModel().setNavigator(navigator);
        getViewModel().init(savedInstanceState, this);
        init(savedInstanceState);
    }

    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        mViewDataBinding.setVariable(getViewModelId(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }


    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            Objects.requireNonNull(imm).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    // TODO: 01/02/18 Initiate login activty
    public void openActivityOnTokenExpire() {

    }

    // TODO: 01/02/18 Please change the network connection analog
    public boolean isNetworkConnected() {
        return false;
    }


    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    public N getNavigator() {
        return navigator;
    }


    public V getViewModel() {
        return mViewModel;
    }


    /**
     * @return layout resource id
     */
    protected abstract
    @LayoutRes
    int getLayoutId();

    public abstract int getViewModelId();

    public abstract void init(@Nullable Bundle savedInstanceState);

    public void performDependencyInjection() {
        AndroidInjection.inject(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionGranted();
                } else {
                    permissionNotGranted();
                }
            }

        }
    }

    public void permissionGranted() {

    }

    public void permissionNotGranted() {

    }

    @Override
    public void showAlertDialog(String title, String message, String positiveText, DialogInterface.OnClickListener pClickListener, String negative, DialogInterface.OnClickListener nClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        if (pClickListener != null)
            builder.setPositiveButton(positiveText, pClickListener);

        if (nClickListener != null) {
            builder.setNegativeButton(negative, nClickListener);
        }
        builder.create().show();

    }

    @Override
    public void showLoading(String title, String message) {
        hideLoading();
        mProgressDialog = Utils.showLoadingDialog(this, title, message);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


}
