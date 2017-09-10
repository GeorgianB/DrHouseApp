package com.google.ar.core.examples.java.helloar.adapter;

import com.google.ar.core.examples.java.helloar.entity.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Api {
    private String endPoint;

    public Api(String endPoint) {
        this.endPoint = endPoint;
    }

    public ArrayList<Product> getProducts() {
        try {
            URL url = new URL(this.endPoint);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            ArrayList<Product> list = new Gson().fromJson(reader, new TypeToken<ArrayList<Product>>() {
            }.getType());
            return list;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return new ArrayList<Product>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<Product>();
        }
    }
}
