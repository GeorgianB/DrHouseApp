package ro.baratheon.doctorhouse;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import ro.baratheon.doctorhouse.adapter.Api;
import ro.baratheon.doctorhouse.entity.Product;

public class MainActivity extends AppCompatActivity {

    ArrayList<Product> dataModels;
    ListView listView;
    public static CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_listing);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        listView = (ListView) findViewById(R.id.list);
        Api jsonAdapter = new Api("http://api.ziwind.com/app_dev.php/v1/product");
        dataModels = jsonAdapter.getProducts();
        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.fab);
        adapter = new CustomAdapter(dataModels, getApplicationContext());
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, DoctorHouseAr.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
        listView.setAdapter(adapter);
    }
}
