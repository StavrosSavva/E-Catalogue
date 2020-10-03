package com.e_catalogue;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class ActivityBasket extends AppCompatActivity {
    ArrayList<Items> arr ;
    ListView list;
    BasketAdapter adapter;
    TextView totalv;
    Button btn;
    private static DecimalFormat df = new DecimalFormat("0.00");




    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        list = findViewById(R.id.basketlist);
        arr = getIntent().getParcelableArrayListExtra("basket");
        adapter = new BasketAdapter(this,arr,R.layout.single_basket);
        list.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        totalv = findViewById(R.id.textView8);
        btn = findViewById(R.id.submitorder_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saythanks();
            }
        });


        double total = 0;
        for(int i = 0; i < arr.size(); i++){
            total +=(arr.get(i).getPrice()* (double)arr.get(i).getQuantity());

        }
        String totals = df.format(total);
        totalv.setText("Total : " + totals + "€");




    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this,ItemAct.class);
        intent.putParcelableArrayListExtra("basket2",arr);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        double total = 0;
        for(int i = 0; i < arr.size(); i++){
            total +=(arr.get(i).getPrice()* (double)arr.get(i).getQuantity());

        }

        totalv.setText("Total : " + Double.toString(total) + "€");
        return super.onTouchEvent(event);
    }

    public void saythanks(){
        Intent intent = new Intent(this,ActivitySplashTy.class);
        intent.putParcelableArrayListExtra("basket2",arr);
        startActivity(intent);
        finish();

    }



}
