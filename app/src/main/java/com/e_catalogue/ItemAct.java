package com.e_catalogue;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class ItemAct extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    ListView l1; // for the items
    Dialog popup;
    ImageView closepopup;
    androidx.appcompat.widget.SearchView mysearch;
    private ArrayList<Items> arr;
    private ArrayList<Items> basket;
    private ArrayList<Items> items;
    ArrayList<Items> new_arr;
    listAdapter adapter;

    private RecyclerView recyclerView;
    private NewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    ImageButton button;
    TextView name ;
    TextView desc ;
    NumberPicker quantity ;
    EditText comment ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemsfull);
        databaseHelper = new DatabaseHelper(this);
        l1 = findViewById(R.id.ListView);
        basket = new ArrayList<>();
        basket = getIntent().getParcelableArrayListExtra("basket2");
        if(basket != null){
            Log.d("the basket is", basket.toString());
        }else{
            basket = new ArrayList<>();
            Log.d("the basket is", "empty");
        }




        databaseHelper = new DatabaseHelper(this);
        items  = databaseHelper.getDataItems("asd123"); //array with all items


        final ArrayList<String> menues = findcategories(items);
        recyclerView = findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new NewAdapter(menues, this);
        recyclerView.setAdapter(mAdapter);
        DatabaseHelper database = new DatabaseHelper(this);
        arr  = database.getDataItems("asd123"); //array with all items
        new_arr = arr;
        ItemClickSupport.addTo(recyclerView)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Log.d("it have been clicked","finally");
                        String type = menues.get(position);
                        new_arr = getcategorized(arr,type);
                        Log.d("it have been clicked",new_arr.toString());
                        adapter = new listAdapter(getBaseContext(), R.layout.single_item, new_arr);
                        l1.setAdapter(adapter);

                        mAdapter.flag = position;
                        adapter.notifyDataSetChanged();
                        mAdapter.notifyDataSetChanged();
                    }
                });


        adapter = new listAdapter(this, R.layout.single_item, new_arr);
        l1.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mAdapter.notifyDataSetChanged();


        button = (ImageButton)findViewById(R.id.imageButton3); //basket it

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                openItemactivity(basket);

            }
        });

        //code for the popup
        popup = new Dialog(this);
        l1.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showpopup(position);

            }
        });

        final TextView menu = findViewById(R.id.textMenu);
        mysearch = findViewById(R.id.searchv);
        mysearch.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("i have clicked it ","ffs");
                menu.setVisibility(View.INVISIBLE);
            }

        });
        mysearch.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                menu.setVisibility(View.VISIBLE);
                return false;
            }
        });

        mysearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                new_arr = onsearch(arr, query);
                adapter = new listAdapter(getBaseContext(), R.layout.single_item, new_arr);
                l1.setAdapter(adapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                new_arr = onsearch(arr, newText);
                adapter = new listAdapter(getBaseContext(), R.layout.single_item, new_arr);
                l1.setAdapter(adapter);
                return false;
            }
        });



    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void showpopup(final int id) {
        popup.setContentView(R.layout.popup);
        name = (TextView)popup.findViewById(R.id.popname);
        desc = (TextView) popup.findViewById(R.id.popdesc);
        quantity = (NumberPicker) popup.findViewById(R.id.popnumpic);
        comment = (EditText) popup.findViewById(R.id.poptxtview);

        String price = "<font color='#787878' size='5' >" +(Double.toString(new_arr.get(id).getPrice())+'€' +"</font>");
        name.setText(Html.fromHtml(new_arr.get((int)id).getName()+ "  " + price) );
        desc.setText(new_arr.get((int)id).getDescription());
        quantity.setMinValue(1);
        quantity.setMaxValue(20);
        Button btnadd = popup.findViewById(R.id.popbtnadd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemid = new_arr.get(id).getId();
                String name = new_arr.get(id).getName();
                String description = new_arr.get(id).getDescription();
                String venue = new_arr.get(id).getVenue_id();
                String type = new_arr.get(id).getType();
                double price = new_arr.get(id).getPrice();
                String SecType = new_arr.get(id).getSecType();
                Items newitem = new Items(itemid,name,description,venue,null,type,SecType,price);

                String comments = comment.getText().toString();
                int quantities = quantity.getValue();
                newitem.setComment(comments);
                newitem.setQuantity(quantities);

                basket.add(newitem);
                popup.dismiss();
            }
        });
        closepopup = (ImageView) popup.findViewById(R.id.closepop);
        closepopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                popup.dismiss();
            }
        });
        Objects.requireNonNull(popup.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popup.show();

    }



    public void openItemactivity(ArrayList<Items> arr){
        Intent intent = new Intent(this, ActivityBasket.class);
        intent.putParcelableArrayListExtra("basket",arr);
        startActivity(intent);

    }

    public static  ArrayList<String> findcategories(ArrayList<Items> items){
        ArrayList<String> cats = new ArrayList<>();
        cats.add("All");
        String type1, type2;
        for(int i = 0; i < items.size(); i++){
           type1 = items.get(i).getType();
           type2 = items.get(i).getSecType();
           if(!cats.contains(type1)){
               cats.add(type1);
           }
           if(!cats.contains(type2)){
               cats.add(type2);
           }
        }

        return cats;
    }

    private ArrayList<Items> getcategorized(ArrayList<Items> arr, String type) {
        ArrayList<Items> cats = new ArrayList<>();
        if( type == "All"){
            return arr;
        }

        for(int i = 0; i < arr.size(); i++){

            if((arr.get(i).getType().compareTo(type) == 0 ) || (arr.get(i).getSecType().compareTo(type) == 0)){

                cats.add(arr.get(i));
            }
        }

        return  cats;
    }

    private ArrayList<Items> onsearch(ArrayList<Items> arr, String string){
        ArrayList<Items> items = new ArrayList<>();
        String name , type , type2, description;
        //item given in the search box
        String item = string.replaceAll("\\s+","").toLowerCase();
        /*Loop through all the items */
        for(int i = 0; i < arr.size(); i++){
            //removes spaces and converts all text to lowercase
            name = arr.get(i).getName().replaceAll("\\s+","").toLowerCase();
            type = arr.get(i).getType().replaceAll("\\s+","").toLowerCase();
            type2 = arr.get(i).getSecType().replaceAll("\\s+","").toLowerCase();
            description = arr.get(i).getDescription().replaceAll("\\s+","").toLowerCase();
            //checks if it matches with the item given
            if(name.contains(item) || type.contains(item) || type2.contains(item) || description.contains(item)){
                items.add(arr.get(i));
            }

        }

        return items;
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this,ActivityRes.class);
        startActivity(intent);
    }






}