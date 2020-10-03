package com.e_catalogue;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteAssetHelper {

    public DatabaseHelper(Context context) {
        super(context, "a.db", null, 1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    public ArrayList<Restaurants> getData(){
        ArrayList<Restaurants> resarray = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Restaurants", null);

        while(cursor.moveToNext()){

            String name = cursor.getString(3);
            String city = cursor.getString(2);
            String venueid = cursor.getString(0);
            String venue = cursor.getString(1);
            byte[] img = cursor.getBlob(4);
            Log.d("MyData", "name = " + name);
            Log.d("MyData", "name = " + city);
            Restaurants res = new Restaurants(venueid, venue, city,name,null);
            resarray.add(res);

        }
        return resarray;
    }

    public ArrayList<Items> getDataItems(String venue ){
        //new array to store the items
        ArrayList<Items> itemarr = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Items WHERE VenueID = " + "'" +venue+ "'" , null);

        while(cursor.moveToNext()){
            int itemid = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            String venue1 = cursor.getString(4);
            String type = cursor.getString(6);
            double price = cursor.getDouble(7);
            String SecType = cursor.getString(8);
            byte[] img = cursor.getBlob(3);
            Log.d("MyData", "price = " + price);
            Log.d("MyData", "name = " + name);
            Log.d("MyData", "des = " + description);
            Log.d("MyData", "type = " + type);
            Items item = new Items(itemid, name,description,venue1,img,type,SecType,price);
            itemarr.add(item);

        }
        return itemarr;
    }
    public ArrayList<String> cities(){
        ArrayList<String> cities = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Cities", null);
        while(cursor.moveToNext()){
            String city = cursor.getString(0);
            cities.add(city);
        }
        return cities;
    }


}
