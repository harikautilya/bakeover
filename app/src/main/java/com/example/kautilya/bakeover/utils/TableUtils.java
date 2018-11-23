package com.example.kautilya.bakeover.utils;

import android.text.TextUtils;

import java.util.List;

public class TableUtils {


    public static String createTable(String tableName, List<String> textField, List<String> numberFields, String primary) {

        String main = "CREATE TABLE " + tableName;
        String textf = "";
        String intf = "";

        if (textf.equals("")) {
            textf += "(";
        }
        if (!primary.equals(""))
            textf += primary + " INTEGER PRIMARY KEY AUTOINCREMENT,";
        textf += TextUtils.join(" TEXT,", textField);
        if (!textf.equals("")) {
            if (numberFields.size() == 0)
                textf += " TEXT ";
            else
                textf += " TEXT,";
        }

        if (textf.equals("")) {
            intf += "(";
        }
        intf += TextUtils.join(" INTEGER,", numberFields);

        if (!intf.equals("")) {
            intf += " INTEGER";
        }
        return main + textf + intf + ")";
    }


}
