package com.example.rucafe;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderingDonutsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordering_donuts);

        // Lookup the recyclerview in activity layout
        RecyclerView donutsRV = (RecyclerView) findViewById(R.id.donutsRecyclerView);

        // Initialize donuts
        ArrayList<Donut> items = Donut.createDonutsList();

        //Context context = getApplicationContext();

        // Create adapter passing in the sample user data
        DonutsAdapter adapter = new DonutsAdapter(this, items);
        // Attach the adapter to the recyclerview to populate items
        donutsRV.setAdapter(adapter);
        // Set layout manager to position the items
        donutsRV.setLayoutManager(new LinearLayoutManager(this));
    }
}
