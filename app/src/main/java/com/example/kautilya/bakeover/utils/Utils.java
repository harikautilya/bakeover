package com.example.kautilya.bakeover.utils;


import android.app.ProgressDialog;
import android.content.Context;

import com.example.kautilya.bakeover.R;
import com.example.kautilya.bakeover.objects.Recepie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class Utils {

    public static ProgressDialog showLoadingDialog(Context context, String title, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    public static List<Recepie> getData(Context context) {


       /* List<Recepie> recepies = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(context.getString(R.string.data_final));
            for (int i = 0; i < jsonArray.length(); i++) {
                recepies.add(new Gson().fromJson(jsonArray.get(i).toString(), Recepie.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recepies;*/
       return new Gson().fromJson(context.getString(R.string.data_final), new TypeToken<List<Recepie>>() {
        }.getType());
    }


    public static Recepie getRecepieById(Context context, long id) {
        Recepie currentRecepie = null;
        for (Recepie recepie : getData(context)) {
            if (recepie.getId() == id) {
                currentRecepie = recepie;

            }
        }
        return currentRecepie;
    }

}
