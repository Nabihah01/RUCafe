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

/**
 * This class processes the GUI from the ordering_donuts.xml in order to
 * allow the user to add donut orders.
 *
 * @author Nabihah, Maryam
 **/
public class OrderingDonutsActivity extends AppCompatActivity implements View.OnClickListener {

    public static ArrayList<Donut> donuts = new ArrayList<>();
    DonutsAdapter adapter;
    static HashMap<String, Integer> donutsOrdered = new HashMap<>();
    private static final int zeroQuantity = 0;

    /**
     * Get the references of all instances of Views defined in the layout file, set up the list of
     * items to be display in the RecyclerView.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordering_donuts);
        RecyclerView donutsRV = (RecyclerView) findViewById(R.id.donutsRecyclerView);
        if(donuts.isEmpty()) {
            setUpMenuItems();
        }
        donutsRV.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DonutsAdapter(this, donuts);
        donutsRV.setAdapter(adapter);

        Button placeOrder = findViewById(R.id.place_order);
        placeOrder.setOnClickListener(this);

    }

    /**
     * overrides onResume method
     */
    @Override
    protected void onResume() {
        super.onResume();
        donuts.clear();
        setUpMenuItems();
        adapter.notifyDataSetChanged();
    }

    /**
     * Helper method to set up the data (the Model of the MVC).
     */
    private void setUpMenuItems() {
        donuts.add(new Donut("Donut Hole", "Chocolate", zeroQuantity, R.drawable.choc_donut_holes));
        donuts.add(new Donut("Donut Hole", "Glazed", zeroQuantity, R.drawable.glazed_donut_holes));
        donuts.add(new Donut("Donut Hole", "Jelly", zeroQuantity, R.drawable.jelly_donut_holes));
        donuts.add(new Donut("Donut Hole", "Blueberry-Glazed", zeroQuantity, R.drawable.blueberry_donut_holes));

        donuts.add(new Donut("Yeast", "Oreo", zeroQuantity, R.drawable.oreo_yeast_donut));
        donuts.add(new Donut("Yeast", "Strawberry-Frosted", zeroQuantity, R.drawable.strawberry_yeast_donut));
        donuts.add(new Donut("Yeast", "Vanilla-Frosted", zeroQuantity, R.drawable.vanilla_yeast_donut));
        donuts.add(new Donut("Yeast", "Chocolate-Creme", zeroQuantity, R.drawable.choc_creme_donut));

        donuts.add(new Donut("Cake", "Powdered", zeroQuantity, R.drawable.powdered_cake_donut));
        donuts.add(new Donut("Cake", "Old-Fashioned", zeroQuantity, R.drawable.old_fashioned_cake_donut));
        donuts.add(new Donut("Cake", "Cinnamon-Sugar", zeroQuantity, R.drawable.cinnamon_sugar_cake_donut));
        donuts.add(new Donut("Cake", "Lemon", zeroQuantity, R.drawable.lemon_cake_donut));
    }

    /**
     * Helper method to reset all donut quantities to zero.
     */
    private void resetMenuItems() {
        for(int i = 0; i < donuts.size(); i++) {
            donuts.get(i).setDonutQuantity(0);
        }
    }

    /**
     * handles the add to order button click
     * @param view instance of View
     */
    @Override
    public void onClick(View view) {
        if(donutsOrdered.isEmpty()){
            Toast.makeText(view.getContext(),
                    "Please select quantity for donuts", Toast.LENGTH_LONG).show();
            return;
        }
        AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
        alert.setTitle("Add To Order");
        alert.setMessage("Add Donut(s) to Order?");

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            /**
             * event handler for when user clicks yes button in alert dialog
             * @param dialog DialogInterface
             * @param which int
             */
            public void onClick(DialogInterface dialog, int which) {
                addToOrder();
                donutsOrdered.clear();
                resetMenuItems();
                adapter.notifyDataSetChanged();

                Toast.makeText(view.getContext(),
                        "Donut Order added.", Toast.LENGTH_LONG).show();
            }

        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            /**
             * event handler for when user clicks no button in alert dialog
             * @param dialog DialogInterface
             * @param which int
             */
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(view.getContext(),
                        "Donut Order not added.", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
    /**
     * helper method to add donut order to user orders.
     */
    private void addToOrder() {
        HashMap<String, Integer> donutsOrder = (HashMap<String, Integer>) donutsOrdered.clone();
        for(Map.Entry<String, Integer> entry: donutsOrder.entrySet()) {
            if(entry.getValue() != 0){
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

    /**
     * getter method for getDonutsOrdered HashMap
     * @return HashMap of donutsOrdered
     */
    public static HashMap<String, Integer> getDonutsOrdered() {
        return donutsOrdered;
    }

}
