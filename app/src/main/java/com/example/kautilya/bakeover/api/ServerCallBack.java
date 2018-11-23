package com.example.kautilya.bakeover.api;

import android.content.Context;
import android.widget.Toast;


import com.example.kautilya.bakeover.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ServerCallBack<T> implements Callback<T> {

    private String message;
    private Context context;
    private boolean showMessage;


    public ServerCallBack(Context context, boolean showMessage) {
        this.context = context;
        this.showMessage = showMessage;
        this.message = context.getString(R.string.server_error);
    }

    public ServerCallBack(String message, Context context) {
        this.message = message;
        this.context = context;
        this.showMessage = false;
    }

    public ServerCallBack(String message, Context context, boolean showMessage) {
        this.message = message;
        this.context = context;
        this.showMessage = showMessage;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.body() != null) {
            onResponse(response.body());
        } else {
            showError(response.errorBody().toString());
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (showMessage)
            showError(message);
    }

    void showError(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


    public abstract void onResponse(T response);

}
