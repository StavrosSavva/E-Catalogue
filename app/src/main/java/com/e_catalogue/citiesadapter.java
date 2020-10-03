package com.e_catalogue;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class citiesadapter extends BaseAdapter {
    Context object;
    ArrayList<String> cities;
    private int layout;

    public citiesadapter(Context context, ArrayList<String> cities, int layout) {
        this.object = context;
        this.cities = cities;
        this.layout = layout;
    }

    @Override
    public Object getItem(int position) {
        return cities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class citiesHolder{
        TextView city;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        citiesadapter.citiesHolder holder = new citiesadapter.citiesHolder();
        if (row == null){
            LayoutInflater inflater  = (LayoutInflater)object.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
            holder.city =(TextView) row.findViewById(R.id.btnc);

            row.setTag(holder);

        }else{
            holder =  (citiesadapter.citiesHolder) row.getTag();
        }

        String city = cities.get(position);
        holder.city.setText(city);

        TextPaint paint = holder.city.getPaint();
        float width = paint.measureText((String)holder.city.getText());

        Shader textShader = new LinearGradient(0, 0, width, holder.city.getTextSize(),
                new int[]{
                        Color.parseColor("#6ed5ed"),
                        Color.parseColor("#00a55c"),

                }, null, Shader.TileMode.CLAMP);
        holder.city.getPaint().setShader(textShader);



        return row;

    }

    @Override
    public int getCount() {
        return cities.size();
    }
}
