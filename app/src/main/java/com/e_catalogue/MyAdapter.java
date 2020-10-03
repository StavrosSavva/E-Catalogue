package com.e_catalogue;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context object;
    ArrayList<Restaurants> resarray;
    private int layout;

    public MyAdapter(Context context, int layout, ArrayList<Restaurants> resarray){
        this.object = context;
        this.resarray = resarray;
        this.layout = layout;
    }

    @Override
    public Object getItem(int position) {
        return resarray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class Viewholder{
        ImageView img;
        TextView txtname, txtcity;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

            View row = convertView;
            Viewholder holder = new Viewholder();
            if (row == null){
                LayoutInflater inflater  = (LayoutInflater)object.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(layout, null);
                holder.txtname =(TextView)row.findViewById(R.id.textView);
                holder.txtcity = (TextView)row.findViewById(R.id.textView2);
                holder.img = (ImageView)row.findViewById(R.id.imageView);
                row.setTag(holder);

            }else{
                holder =  (Viewholder) row.getTag();
            }

            Restaurants restaurant = resarray.get(position);
            holder.txtname.setText(restaurant.getName());
            holder.txtcity.setText("Street: " + restaurant.getVenue());
            byte[] fimg =  restaurant.getLogo();
            if(fimg != null){
                 Bitmap bitmap = BitmapFactory.decodeByteArray(fimg, 0, fimg.length);
                 holder.img.setImageBitmap(bitmap);}



        return row;
    }

    @Override
    public int getCount() {
        return this.resarray.size();
    }

}
