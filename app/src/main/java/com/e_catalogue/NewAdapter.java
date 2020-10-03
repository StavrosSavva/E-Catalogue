package com.e_catalogue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.MyViewHolder> {
    private ArrayList<String> mDataset;
    Context context;
     public int flag = 0;
    ArrayList<Items> newarr;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;

        public MyViewHolder(TextView v) {
            super(v);
            textView = v.findViewById(R.id.textCat);


        }
    }




    // Provide a suitable constructor (depends on the kind of dataset)
    public NewAdapter(ArrayList<String> myDataset, Context context) {
        mDataset = myDataset;
        this.context = context;
    }

    public void setNewarr(ArrayList<Items> newarr) {
        this.newarr = newarr;
    }

    public ArrayList<Items> getNewarr() {
        if (newarr == null){
            DatabaseHelper database = new DatabaseHelper(context);
            return  newarr  = database.getDataItems("asd123"); //array with all items

        }
        return newarr;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public NewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_cat, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(mDataset.get(position));
        /** holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "you have chosen: "+ mDataset.get(position) ,Toast.LENGTH_SHORT).show();
                flag = position;
                String type = mDataset.get(position);
                DatabaseHelper database = new DatabaseHelper(context);
                ArrayList<Items> arr  = database.getDataItems("asd123"); //array with all items
                setNewarr( getcategorized(arr,type));
                notifyDataSetChanged();


            }
        }); */
        if(flag == position){
            holder.textView.setBackgroundResource(R.drawable.roundedgray);




        }else{

            holder.textView.setBackgroundResource(R.drawable.roundedcorners);
        }



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


    @Override
    public long getItemId(int position) {
        return position;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public Context getContext() {
        return context;
    }
}
