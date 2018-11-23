package com.example.kautilya.bakeover.Base;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kautilya.bakeover.Base.annotations.ApplicationContext;
import com.example.kautilya.bakeover.Base.annotations.DatabaseInfo;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper instance = null;

    private static final String LOG_TAG = DBHelper.class.getName();


    @Inject
    public DBHelper(@ApplicationContext Context context,
                    @DatabaseInfo String dbName,
                    @DatabaseInfo Integer version) {
        super(context, dbName, null, version);

        context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);

        instance = this;
    }

    public static synchronized DBHelper get() {
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createAllTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        deleteAllTables(db);
        onCreate(db);
    }

    private static void deleteAllTables(SQLiteDatabase db) {

    }


    private static void createAllTables(SQLiteDatabase db) {


        try {

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void reCreateAllTables() {
        SQLiteDatabase db = DBHelper.get().getWritableDatabase();
        deleteAllTables(db);
        createAllTables(db);

    }


}
