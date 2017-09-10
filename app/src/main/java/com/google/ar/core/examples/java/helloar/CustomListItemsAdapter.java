package com.google.ar.core.examples.java.helloar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ar.core.examples.java.helloar.entity.Product;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class CustomListItemsAdapter extends BaseAdapter{
    Context context;
    ArrayList<Product> productsList;
    private static LayoutInflater inflater=null;
    private int selectedListItem = 0;
    public CustomListItemsAdapter(HelloArActivity mainActivity, ArrayList<Product> products) {
        productsList = products;
        // TODO Auto-generated constructor stub
        context=mainActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return productsList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.items_list, null);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);
        holder.img.setImageBitmap(this.getBitmapFromURL(productsList.get(position).getImage()));

        if(position == selectedListItem) {
            rowView.setBackgroundColor(Color.parseColor("#005eb8"));
        } else {
            rowView.setBackgroundColor(0x333333);
        }

        rowView.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(true);
                selectedListItem = position;
                ((HelloArActivity)context).selectItem(position);

            }
        });

        return rowView;
    }

    private Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher);
        }
    }
}