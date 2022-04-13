package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //can use putExtra(key, value) to access value from other activity
    //key name has to be unique
    //getIntent in second class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void orderingDonuts(View view) {
        Intent intent = new Intent(this, OrderingDonutsActivity.class);
        startActivity(intent);
    }

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