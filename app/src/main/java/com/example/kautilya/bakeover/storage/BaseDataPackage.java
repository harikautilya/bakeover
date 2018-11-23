package com.example.kautilya.bakeover.storage;


import com.example.kautilya.bakeover.Base.DBHelper;

import javax.inject.Inject;

public class BaseDataPackage implements BaseDataRepo {


    private DBHelper dbHelper;

    @Inject
    public BaseDataPackage(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }


}
