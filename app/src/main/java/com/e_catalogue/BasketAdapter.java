package com.e_catalogue;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class BasketAdapter extends BaseAdapter {
    ArrayList<Items> arr;
    Context context;
    private int layout;

    TextView name ;
    TextView desc ;
    NumberPicker quantity ;
    EditText comment ;
    Dialog popup;
    private static DecimalFormat df = new DecimalFormat("0.00");

    public BasketAdapter(Context context, ArrayList<Items> bstitems ,int layout){
        this.arr = bstitems;
        this.context = context;
        this.layout = layout;
    }
    @Override
    public int getCount() {
        return this.arr.size();
    }

    @Override
    public Object getItem(int position) {
        return this.arr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Viewholder holder = new Viewholder();
        if(row == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);
            holder.txtname = (TextView)row.findViewById(R.id.txt_basketname);
            holder.txtprice = (TextView)row.findViewById(R.id.txt_basketprice);
            holder.txtcomment = (TextView)row.findViewById(R.id.commentbaskt);
            holder.edit = (ImageButton)row.findViewById(R.id.edit);
            holder.delete = (ImageButton)row.findViewById(R.id.delete);


            row.setTag(holder);
        }else {
            holder = (Viewholder) row.getTag();
        }
        Items item = this.arr.get(position);
        String no = Integer.toString(item.getQuantity());
        holder.txtname.setText(no + "X " +item.getName());
        String pricestring = df.format(item.getPrice() * item.getQuantity()) + "€";
        holder.txtprice.setText(pricestring);
        holder.txtcomment.setText(item.getComment());
        byte[] fimg =  item.getPhoto();

        if(fimg != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(fimg, 0, fimg.length);
            holder.img.setImageBitmap(bitmap);}


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arr.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                popup = new Dialog(context);
                showpopup(position);

            }
        });
        return row;
    }
    public static  class Viewholder{
        ImageView img;
        TextView txtname, txtprice,txtcomment;
        ImageButton edit, delete;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void showpopup(final int id) {
        popup.setContentView(R.layout.popup);
        name = (TextView)popup.findViewById(R.id.popname);
        desc = (TextView) popup.findViewById(R.id.popdesc);
        quantity = (NumberPicker) popup.findViewById(R.id.popnumpic);
        comment = (EditText) popup.findViewById(R.id.poptxtview);


        quantity.setMinValue(1);
        quantity.setMaxValue(20);
        quantity.setValue(arr.get(id).getQuantity());
        String price = "<font color='#787878' size='5' >" +(df.format(arr.get(id).getPrice()*(double)quantity.getValue())+'€' +"</font>");
        name.setText(Html.fromHtml(arr.get((int)id).getName()+ "  " + price) );
        desc.setText(arr.get((int)id).getDescription());
        Button btnupdate = popup.findViewById(R.id.popbtnadd);
        btnupdate.setText("Update");
        quantity.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                String price = "<font color='#787878' size='5' >" +(df.format(arr.get(id).getPrice()*(double)newVal)+'€' +"</font>");
                name.setText(Html.fromHtml(arr.get((int)id).getName()+ "  " + price ));
            }
        });
        comment.setText(arr.get(id).getComment());

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemid = arr.get(id).getId();
                String name = arr.get(id).getName();
                String description = arr.get(id).getDescription();
                String venue = arr.get(id).getVenue_id();
                String type = arr.get(id).getType();
                double price = arr.get(id).getPrice();
                String SecType = arr.get(id).getSecType();
                Items newitem = new Items(itemid,name,description,venue,null,type,SecType,price);

                String comments = comment.getText().toString();
                int quantities = quantity.getValue();
                newitem.setComment(comments);
                newitem.setQuantity(quantities);

                arr.set(id, newitem);
                notifyDataSetChanged();
                popup.dismiss();
            }
        });
        ImageView closepopup = (ImageView) popup.findViewById(R.id.closepop);
        closepopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                popup.dismiss();
            }
        });
        Objects.requireNonNull(popup.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popup.show();

    }
    public ArrayList<Items> getArr(){
        return this.arr;
    }





}
