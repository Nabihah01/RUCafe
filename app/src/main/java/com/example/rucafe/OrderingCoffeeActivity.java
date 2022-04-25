package com.example.rucafe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This class processes the GUI from the ordering_coffee.xml in order to
 * allow the user to add and customize coffee order
 *
 * @author Nabihah, Maryam
 **/

public class OrderingCoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{
    private Spinner spinner;
    private String [] sizes = {"Short", "Tall", "Grande", "Venti"};
    private ArrayAdapter<String> adapter;
    public String size = "";
    private TextView coffeeTotal;
    private TextView coffeeQuantity;
    private int quantity = 1;
    private static final double defaultTotal =  1.69;
    private static final int defaultQuantity = 1;
    private static final int zeroQuantity = 0;
    ArrayList<String> addIns = new ArrayList<>();
    protected static final DecimalFormat df = new DecimalFormat("###,##0.00");

    /**
     * overrides onCreate method and initializes adapters
     * @param savedInstanceState instance of Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordering_coffee);

        spinner = findViewById(R.id.size_spinner);
        spinner.setOnItemSelectedListener(this);
        adapter = new ArrayAdapter<String>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, sizes);
        spinner.setAdapter(adapter); //dynamically set the adapter that associates with the list of String.

        coffeeTotal = findViewById(R.id.coffee_total);
        coffeeTotal.setText(String.valueOf(defaultTotal));
        coffeeQuantity = findViewById(R.id.coffee_quantity);
        coffeeQuantity.setText(String.valueOf(quantity));

        Button add = findViewById(R.id.add_coffee);
        Button remove = findViewById(R.id.remove_coffee);
        add.setOnClickListener(this);
        remove.setOnClickListener(this);
    }

    /**
     * Handles the button clicks for add and minus for coffee quantity
     * @param v instance of View
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_coffee:
                quantity++;
                coffeeQuantity.setText(String.valueOf(quantity));
                Coffee coffeeOrder = new Coffee(size, addIns, quantity);
                double price = coffeeOrder.itemPrice();
                coffeeTotal.setText(String.valueOf(df.format(price)));
                break;
            case R.id.remove_coffee:
                if(quantity == zeroQuantity) {
                    Toast.makeText(v.getContext(),
                            "Cannot reduce quantity below zero", Toast.LENGTH_LONG).show();
                    return;
                }
                quantity--;
                coffeeQuantity.setText(String.valueOf(quantity));
                coffeeOrder = new Coffee(size, addIns, quantity);
                price = coffeeOrder.itemPrice();
                coffeeTotal.setText(String.valueOf(df.format(price)));
                break;
        }
    }

    /**
     * method for handling spinner selection
     * @param adapterView instance of AdapterView
     * @param view instance of view
     * @param i int
     * @param l long
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        size = adapterView.getSelectedItem().toString();
        Coffee coffeeOrder = new Coffee(size, addIns, quantity);
        double price = coffeeOrder.itemPrice();
        coffeeTotal.setText(String.valueOf(df.format(price)));
    }

    /**
     * event handler for when nothing is selected in spinner
     * @param adapterView
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /**
     * handles when milk checkbox is clicked
     * @param v instance of View
     */
    public void milkClicked(View v) {
        CheckBox checkBox = (CheckBox) v;
        if(checkBox.isChecked()){
            addIns.add("milk");
        }
        else{
            if(addIns.contains("milk")) {
                addIns.remove("milk");
            }
        }
        Coffee coffee = new Coffee(size, addIns, quantity);
        double price = coffee.itemPrice();
        coffeeTotal.setText(String.valueOf(df.format(price)));
    }

    /**
     * handles when cream checkbox is clicked
     * @param v instance of View
     */
    public void creamClicked(View v) {
        CheckBox checkBox = (CheckBox) v;
        if(checkBox.isChecked()){
            addIns.add("cream");
        }
        else{
            if(addIns.contains("cream")) {
                addIns.remove("cream");
            }
        }
        Coffee coffee = new Coffee(size, addIns, quantity);
        double price = coffee.itemPrice();
        coffeeTotal.setText(String.valueOf(df.format(price)));
    }

    /**
     * handles when whipped cream checkbox is clicked
     * @param v instance of View
     */
    public void whippedClicked(View v) {
        CheckBox checkBox = (CheckBox) v;
        if(checkBox.isChecked()){
            addIns.add("whipped cream");
        }
        else{
            if(addIns.contains("whipped cream")) {
                addIns.remove("whipped cream");
            }
        }
        Coffee coffee = new Coffee(size, addIns, quantity);
        double price = coffee.itemPrice();
        coffeeTotal.setText(String.valueOf(df.format(price)));
    }

    /**
     * handles when syrup checkbox is clicked
     * @param v instance of View
     */
    public void syrupClicked(View v) {
        CheckBox checkBox = (CheckBox) v;
        if(checkBox.isChecked()){
            addIns.add("syrup");
        }
        else{
            if(addIns.contains("syrup")) {
                addIns.remove("syrup");
            }
        }
        Coffee coffee = new Coffee(size, addIns, quantity);
        double price = coffee.itemPrice();
        coffeeTotal.setText(String.valueOf(df.format(price)));
    }

    /**
     * handles when caramel checkbox is clicked
     * @param v instance of View
     */
    public void caramelClicked(View v) {
        CheckBox checkBox = (CheckBox) v;
        if(checkBox.isChecked()){
            addIns.add("caramel");
        }
        else{
            if(addIns.contains("caramel")) {
                addIns.remove("caramel");
            }
        }
        Coffee coffee = new Coffee(size, addIns, quantity);
        double price = coffee.itemPrice();
        coffeeTotal.setText(String.valueOf(df.format(price)));
    }

    /**
     * handles when add to order button is clicked
     * @param v instance of View
     */
    public void addToOrderCoffee(View v){
        if(quantity == zeroQuantity){
            Toast.makeText(v.getContext(), "Quantity cannot be zero", Toast.LENGTH_LONG).show();
            return;
        }
        Coffee coffeeOrder = new Coffee(size, (ArrayList<String>) addIns.clone(), quantity);

        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
        alert.setTitle("Add To Order");
        alert.setMessage(coffeeOrder.toString());
        //handle the "YES" click
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            /**
             * event handler for when user clicks yes button in alert dialog
             * @param dialog DialogInterface
             * @param which int
             */
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.yourOrder.add(coffeeOrder);
                Toast.makeText(v.getContext(),
                        coffeeOrder.toString()+ " added.", Toast.LENGTH_LONG).show();
            }
            //handle the "NO" click
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            /**
             * event handler for when user clicks no button in alert dialog
             * @param dialog DialogInterface
             * @param which int
             */
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(v.getContext(),
                        coffeeOrder.toString() + " not added.", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();

        CheckBox whipped = findViewById(R.id.whipped_checkbox);
        CheckBox cream = findViewById(R.id.cream_checkbox);
        CheckBox caramel = findViewById(R.id.caramel_checkbox);
        CheckBox syrup = findViewById(R.id.syrup_checkbox);
        CheckBox milk = findViewById(R.id.milk_checkbox);
        whipped.setChecked(false);
        cream.setChecked(false);
        caramel.setChecked(false);
        syrup.setChecked(false);
        milk.setChecked(false);
        spinner.setAdapter(adapter);
        quantity = defaultQuantity;
        coffeeTotal.setText(String.valueOf(defaultTotal));
        coffeeQuantity.setText(String.valueOf(quantity));
        addIns.clear();

    }
}
