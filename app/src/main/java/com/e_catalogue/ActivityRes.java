package com.e_catalogue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class ActivityRes extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    dbconnect connect;
    ListView l1;
    private ArrayList<Restaurants> arr;
    MyAdapter adapter;
    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant);
        databaseHelper = new DatabaseHelper(this);
       arr = new ArrayList<>();
        /* try {
            connect = new dbconnect();
            ArrayList<Restaurants1> res1 = connect.getRestaurants();
            for(int i = 0; i < res1.size(); i++){
                Restaurants res = new Restaurants(res1.get(i).VenueID,res1.get(i).Address, res1.get(i).City,res1.get(i).Name,null);
                arr.add(res);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } */

        arr = databaseHelper.getData();

        l1 = findViewById(R.id.gridView);

        //arr = databaseHelper.getData();
        adapter = new MyAdapter(this, R.layout.newl , arr);
        l1.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "you have chosen: "+arr.get(position).getName(),Toast.LENGTH_SHORT).show();
                openItemactivity();

            }
        });



    }
    public void openItemactivity(){
        Intent intent = new Intent(this, ItemAct.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this,MainActivity.class);

        startActivity(intent);

    }




}
