package com.e_catalogue;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class LocalDatabaseHelper extends SQLiteAssetHelper {

    public LocalDatabaseHelper(Context context){
        super(context, "local.db", null, 1);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }




}
