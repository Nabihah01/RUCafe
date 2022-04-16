package com.example.rucafe;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrderingCoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
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
    public void addCoffee(View v){
        quantity++;
        coffeeQuantity.setText(quantity);
        Coffee coffeeOrder = new Coffee(size, addIns, quantity);
        double price = coffeeOrder.itemPrice();
        coffeeTotal.setText(String.valueOf(df.format(price)));

    }

    public void removeCoffee(View v){
        quantity--;
        coffeeQuantity.setText(quantity);
        Coffee coffeeOrder = new Coffee(size, addIns, quantity);
        double price = coffeeOrder.itemPrice();
        coffeeTotal.setText(String.valueOf(df.format(price)));

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
        MainActivity.yourOrder.add(coffeeOrder);

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
