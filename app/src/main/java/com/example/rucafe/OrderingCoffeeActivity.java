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

public class OrderingCoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{
    private Spinner spinner;
    private String [] sizes = {"Short", "Tall", "Grande", "Venti"};
    private ArrayAdapter<String> adapter;
    public String size = "";
    private TextView coffeeTotal;
    private TextView coffeeQuantity;
    private int quantity = 1;
    ArrayList<String> addIns = new ArrayList<>();
    protected static final DecimalFormat df = new DecimalFormat("###,##0.00");

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
        coffeeTotal.setText(String.valueOf(1.69));
        coffeeQuantity = findViewById(R.id.coffee_quantity);
        coffeeQuantity.setText(String.valueOf(quantity));
        Button add = findViewById(R.id.add_coffee);
        Button remove = findViewById(R.id.remove_coffee);
        add.setOnClickListener(this);
        remove.setOnClickListener(this);

    }

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
                quantity--;
                coffeeQuantity.setText(String.valueOf(quantity));
                coffeeOrder = new Coffee(size, addIns, quantity);
                price = coffeeOrder.itemPrice();
                coffeeTotal.setText(String.valueOf(df.format(price)));
                break;
        }
    }
    /** for spinner
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //useful methods: getSelectedItemPosition() getSelectedItem() getSelectedItemId() getSelectedView()
        size = adapterView.getSelectedItem().toString();
        Coffee coffeeOrder = new Coffee(size, addIns, quantity);
        double price = coffeeOrder.itemPrice();
        coffeeTotal.setText(String.valueOf(df.format(price)));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {//can leave empty

    }

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
    public void addToOrderCoffee(View v){
        Coffee coffeeOrder = new Coffee(size, (ArrayList<String>) addIns.clone(), quantity);
        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
        alert.setTitle("Add To Order");
        alert.setMessage(coffeeOrder.toString());
        //handle the "YES" click
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.yourOrder.add(coffeeOrder);
                Toast.makeText(v.getContext(),
                        coffeeOrder.toString()+ " added.", Toast.LENGTH_LONG).show();
            }
            //handle the "NO" click
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
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
        quantity = 1;
        coffeeTotal.setText(String.valueOf(1.69));
        coffeeQuantity.setText(String.valueOf(1));
        addIns.clear();

    }
}
