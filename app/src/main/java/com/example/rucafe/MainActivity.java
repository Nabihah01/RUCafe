package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //can use putExtra(key, value) to access value from other activity
    //key name has to be unique
    //getIntent in second class
    public static int orderNum = 1;
    public static Order yourOrder =new Order(new ArrayList<>(), orderNum);
    public static StoreOrders storeOrders = new StoreOrders();
    //private double price = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        yourOrder = new Order(new ArrayList<>(), orderNum);
//        storeOrders = new StoreOrders();

        Button donutsButton = findViewById(R.id.orderDonuts);

        donutsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OrderingDonutsActivity.class);
                startActivity(intent);
            }
        });
    }

//    public void orderingDonuts(View view) {
//        Intent intent = new Intent(this, OrderingDonutsActivity.class);
//        startActivity(intent);
//    }

    public void orderingCoffee(View view) {
        Intent intent = new Intent(this, OrderingCoffeeActivity.class);
        startActivity(intent);
    }

    public void viewYourOrder(View view) {
        Intent intent = new Intent(this, YourOrderActivity.class);
        startActivity(intent);
    }

    public void viewStoreOrders(View view) {
        Intent intent = new Intent(this, StoreOrdersActivity.class);
        startActivity(intent);
    }
}