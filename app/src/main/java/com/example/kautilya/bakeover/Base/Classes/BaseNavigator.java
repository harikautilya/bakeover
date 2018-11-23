package com.example.kautilya.bakeover.Base.Classes;

import android.content.DialogInterface;

/**
 * Created by kautilya on 05/02/18.
 */

public interface BaseNavigator {


    void showLoading(String title, String message);

    void hideLoading();

    void showToast(String message);

    void showAlertDialog(String title, String message, String positiveText, DialogInterface.OnClickListener pClickListener, String negative, DialogInterface.OnClickListener nClickListener);
}
