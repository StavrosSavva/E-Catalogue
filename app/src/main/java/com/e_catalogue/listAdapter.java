package com.e_catalogue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class listAdapter extends BaseAdapter {
    Context object;
    ArrayList<Items> itemsarr;
    private int layout;
    ArrayList<Items> basket;

    public listAdapter(Context context, int layout, ArrayList<Items>arr){
        this.object =context;
        this.itemsarr = arr;
        this.layout = layout;
        basket = new ArrayList<>();
    }

    @Override
    public Object getItem(int position) {
        return itemsarr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class Viewholder{
        ImageView img;
        TextView txtname, txtprice, txtdes;
        Button btn;

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int i = position;
        View row = convertView;
        listAdapter.Viewholder holder = new listAdapter.Viewholder();

        if (row == null){
            LayoutInflater inflater  = (LayoutInflater)object.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
            holder.txtname =(TextView)row.findViewById(R.id.textView4);
            holder.txtprice = (TextView)row.findViewById(R.id.textView5);
            holder.txtdes = (TextView)row.findViewById(R.id.description);
           /** holder.img = (ImageView)row.findViewById(R.id.imageView2);
            holder.btn = (Button)row.findViewById(R.id.button); */
            row.setTag(holder);

        }else{
            holder =  (listAdapter.Viewholder) row.getTag();
        }

        Items item = itemsarr.get(position);
        holder.txtname.setText(item.getName());
        String str = (String)Double.toString(item.getPrice()) + "€";
        holder.txtprice.setText(str);
        holder.txtdes.setText(item.getDescription());
       /* byte[] fimg =  item.getPhoto();
        if(fimg != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(fimg, 0, fimg.length);
            holder.img.setImageBitmap(bitmap);} */

       /** holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(object, "You added the following item to basket: "+itemsarr.get(i).getName(),Toast.LENGTH_SHORT).show();
                basket.add(itemsarr.get(i));
            }


        }); */

        return row;
    }

    @Override
    public int getCount() {
        return this.itemsarr.size();
    }

    public ArrayList<Items> getBasket(){
        return basket;
    }
    public int returnzero(){
        return 0;
    }
}
