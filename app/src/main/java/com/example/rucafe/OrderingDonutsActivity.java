package com.example.rucafe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderingDonutsActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Donut> donuts = new ArrayList<>();

    /**
     * Get the references of all instances of Views defined in the layout file, set up the list of
     * items to be display in the RecyclerView.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordering_donuts);

        // Lookup the recyclerview in activity layout
        RecyclerView donutsRV = (RecyclerView) findViewById(R.id.donutsRecyclerView);

        // Initialize donuts
        setUpMenuItems();

        // Set layout manager to position the items
        donutsRV.setLayoutManager(new LinearLayoutManager(this));

        // Create adapter passing in the sample user data
        DonutsAdapter adapter = new DonutsAdapter(this, donuts);
        // Attach the adapter to the recyclerview to populate items
        donutsRV.setAdapter(adapter);

        Button placeOrder = findViewById(R.id.place_order);
        placeOrder.setOnClickListener(this);

    }

    /**
     * Helper method to set up the data (the Model of the MVC).
     */
    private void setUpMenuItems() {
        donuts.add(new Donut("Donut Hole", "Chocolate", 1, R.drawable.choc_donut_holes));
        donuts.add(new Donut("Donut Hole", "Glazed", 1, R.drawable.glazed_donut_holes));
        donuts.add(new Donut("Donut Hole", "Jelly", 1, R.drawable.jelly_donut_holes));
        donuts.add(new Donut("Donut Hole", "Blueberry Glazed", 1, R.drawable.blueberry_donut_holes));

        donuts.add(new Donut("Yeast", "Oreo", 1, R.drawable.oreo_yeast_donut));
        donuts.add(new Donut("Yeast", "Strawberry Frosted", 1, R.drawable.strawberry_yeast_donut));
        donuts.add(new Donut("Yeast", "Vanilla Frosted", 1, R.drawable.vanilla_yeast_donut));
        donuts.add(new Donut("Yeast", "Chocolate Creme", 1, R.drawable.choc_creme_donut));

        donuts.add(new Donut("Cake", "Powdered", 1, R.drawable.powdered_cake_donut));
        donuts.add(new Donut("Cake", "Old-Fashioned", 1, R.drawable.old_fashioned_cake_donut));
        donuts.add(new Donut("Cake", "Cinnamon Sugar", 1, R.drawable.cinnamon_sugar_cake_donut));
        donuts.add(new Donut("Cake", "Lemon", 1, R.drawable.lemon_cake_donut));
    }

    @Override
    public void onClick(View view) {
//        Donut donutOrder = new Donut();
        AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
        alert.setTitle("Add To Order");
        //handle the "YES" click
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
//                MainActivity.yourOrder.add(donutOrder);
                Toast.makeText(view.getContext(),
                        " order added.", Toast.LENGTH_LONG).show();
            }
            //handle the "NO" click
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(view.getContext(),
                        " order not added.", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
        //reset everything
    }
}
