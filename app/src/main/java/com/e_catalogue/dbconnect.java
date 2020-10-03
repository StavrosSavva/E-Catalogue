package com.e_catalogue;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimerTask;

// Packages to import

public class dbconnect extends TimerTask {
    private String AvenueID = "1";
    private ArrayList<Restaurants1> allRestaurants;
    private ArrayList<Items1> itemsOfRestaurant;
    @Override
    public void run() {

    }

    public dbconnect() throws IOException {
        //table=parTable;
    }

    public dbconnect(String VenueID){
        AvenueID=VenueID;
    }



    public ArrayList<Restaurants1> getRestaurants() throws IOException {
        URL url = new URL("https://ordercy.a2hosted.com/orderCY/getRestaurants.php");
        URLConnection con = url.openConnection();
        URLConnection con1 = url.openConnection();
        InputStream in = con.getInputStream();
        String encoding = con.getContentEncoding();
        encoding = encoding == null ? "UTF-8" : encoding;
        String body = IOUtils.toString(in, encoding);
        //System.out.println(body);
        Gson googleJson = new Gson();
        Restaurants1[] javaArrayListFromGSON = googleJson.fromJson(body, Restaurants1[].class);
        allRestaurants = new ArrayList<>(Arrays.asList(javaArrayListFromGSON));
        //System.out.println(allRestaurants.get(0).Name);

        return allRestaurants;

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public ArrayList<Items1> getItems(String venueID) throws IOException {
        String urlString = "https://ordercy.a2hosted.com/orderCY/getItems.php?venueID=" + URLEncoder.encode(venueID, String.valueOf(StandardCharsets.UTF_8));
        URL url = new URL(urlString);
        URLConnection con = url.openConnection();
        InputStream in = con.getInputStream();
        String encoding = con.getContentEncoding();
        encoding = encoding == null ? "UTF-8" : encoding;
        String body = IOUtils.toString(in, encoding);
        Gson googleJson = new Gson();
        Items1[] itemsjson = googleJson.fromJson(body, Items1[].class);
        itemsOfRestaurant = new ArrayList<>(Arrays.asList(itemsjson));

        return itemsOfRestaurant;

    }
}
