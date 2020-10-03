package com.e_catalogue;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    ListView l1;
    ArrayList<String> cities;
    citiesadapter adapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooselocation);
        databaseHelper = new DatabaseHelper(this);
        l1 = findViewById(R.id.lsv);
        cities = new ArrayList<>();
        cities.add("Larnaca");
        cities.add("Nicosia");
        cities.add("Ayia Napa");
        cities.add("Paphos");
        cities.add("Limassol");
        adapter = new citiesadapter(this, cities, R.layout.sc);
        l1.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "you have chosen: "+cities.get(position),Toast.LENGTH_SHORT).show();
                openItemactivity();

            }
        });
        toolbar = findViewById(R.id.toolbar);

        //toolbar.setTitle("Choose Location");


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        }

        DrawerLayout drawer = findViewById(R.id.drawer);
        NavigationView nav = (NavigationView) findViewById(R.id.navview);
        ActionBarDrawerToggle drawertoggle = new ActionBarDrawerToggle(this, drawer,toolbar,R.string.drawer_open, R.string.drawer_close);
        //drawertoggle.syncState();
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return true;
            }
        });









    }
    //intent method to start a new activity
    public void openItemactivity() {
        Intent intent = new Intent(this, ActivityRes.class);
        startActivity(intent);
    }

}
