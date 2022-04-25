package com.example.rucafe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderingDonutsActivity extends AppCompatActivity implements View.OnClickListener {

    public static ArrayList<Donut> donuts = new ArrayList<>();
    DonutsAdapter adapter;
    static HashMap<String, Integer> donutsOrdered = new HashMap<>();

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
        // DonutsAdapter adapter = new DonutsAdapter(this, donuts);
        adapter = new DonutsAdapter(this, donuts);
        // Attach the adapter to the recyclerview to populate items
        donutsRV.setAdapter(adapter);

        Button placeOrder = findViewById(R.id.place_order);
        placeOrder.setOnClickListener(this);

    }

    /**
     * Helper method to set up the data (the Model of the MVC).
     */
    private void setUpMenuItems() {
        donuts.add(new Donut("Donut Hole", "Chocolate", 0, R.drawable.choc_donut_holes));
        donuts.add(new Donut("Donut Hole", "Glazed", 0, R.drawable.glazed_donut_holes));
        donuts.add(new Donut("Donut Hole", "Jelly", 0, R.drawable.jelly_donut_holes));
        donuts.add(new Donut("Donut Hole", "Blueberry-Glazed", 0, R.drawable.blueberry_donut_holes));

        donuts.add(new Donut("Yeast", "Oreo", 0, R.drawable.oreo_yeast_donut));
        donuts.add(new Donut("Yeast", "Strawberry-Frosted", 0, R.drawable.strawberry_yeast_donut));
        donuts.add(new Donut("Yeast", "Vanilla-Frosted", 0, R.drawable.vanilla_yeast_donut));
        donuts.add(new Donut("Yeast", "Chocolate-Creme", 0, R.drawable.choc_creme_donut));

        donuts.add(new Donut("Cake", "Powdered", 0, R.drawable.powdered_cake_donut));
        donuts.add(new Donut("Cake", "Old-Fashioned", 0, R.drawable.old_fashioned_cake_donut));
        donuts.add(new Donut("Cake", "Cinnamon-Sugar", 0, R.drawable.cinnamon_sugar_cake_donut));
        donuts.add(new Donut("Cake", "Lemon", 0, R.drawable.lemon_cake_donut));
    }

    private void resetMenuItems() {
        for(int i = 0; i < donuts.size(); i++) {
            donuts.get(i).setDonutQuantity(0);
        }
    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
        alert.setTitle("Add To Order");
        //handle the "YES" click
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                addToOrder();
                //reset everything
                donutsOrdered.clear();
                resetMenuItems();
                adapter.notifyDataSetChanged();
                //IDK WHAT TO DOOOO, IT KEEPS POPULATING TWICE

//                donuts.clear();
//                //adapter.notifyDataSetChanged();
//                adapter = new DonutsAdapter(view.getContext(), donuts);
//                donutsRV.setAdapter(adapter);
//
//                startActivity(getIntent());
//                finish();
//                overridePendingTransition(0, 0);

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
    }

    private void addToOrder() {
        HashMap<String, Integer> donutsOrder = (HashMap<String, Integer>) donutsOrdered.clone();
        for(Map.Entry<String, Integer> entry: donutsOrder.entrySet()) {
            if(entry.getValue() == 0)
                continue;
            else {
                String split[] = entry.getKey().split(" "); //splits string into flavor and type
                Donut donut;
                if(split[split.length - 1].equals("Hole")) {
                    donut = new Donut("Donut Holes", split[0], entry.getValue());
                } else {
                    donut = new Donut(split[split.length-1], split[0], entry.getValue());
                }
                MainActivity.yourOrder.add(donut);
            }
        }
    }

    public static HashMap<String, Integer> getDonutsOrdered() {
        return donutsOrdered;
    }

}
