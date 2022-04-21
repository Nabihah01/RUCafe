package com.example.rucafe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

//check for when no orders are placed

public class StoreOrdersActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    private ListView listView;
    private ArrayAdapter<MenuItem> listAdapter;
    private Spinner spinner;
    private ArrayAdapter<Integer> adapter;
    private ArrayList<Integer> orderNums;
    private TextView orderTotal;
    protected static final DecimalFormat df = new DecimalFormat("###,##0.00");
    private final double salesTax = (6.625 / 100);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_orders);

        spinner = findViewById(R.id.order_numbers);
        spinner.setOnItemSelectedListener(this);
        orderNums = new ArrayList<>();
        for(int i = 0; i < MainActivity.storeOrders.getStoreOrdersArray().size(); i++){
            orderNums.add(MainActivity.storeOrders.getStoreOrdersArray().get(i).getOrderNumber());
        }
        adapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, orderNums);
        spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView = findViewById(R.id.store_orders_list);
        listView.setOnItemClickListener(this);

        orderTotal = findViewById(R.id.store_orders_total);

        update();

    }

    public void cancelOrder(View v) {
        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
        alert.setTitle("Order");
        alert.setMessage("Cancel Order?");
        //handle the "YES" click
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(v.getContext(),
                        "Order Cancelled.", Toast.LENGTH_LONG).show();
                int numToRemove =  findIndex(Integer.parseInt(spinner.getSelectedItem().toString()));
                MainActivity.storeOrders.remove(MainActivity.storeOrders.getStoreOrdersArray().get(numToRemove));
                orderNums.remove(numToRemove);
                update();
            }
            //handle the "NO" click
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(v.getContext(),
                        "Order Not Cancelled.", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
    private void update(){
        ArrayList<MenuItem> order = new ArrayList<>();
        double total = 0.00;
        if(!orderNums.isEmpty()) {
            int currOrder = findIndex(orderNums.get(0));
            order = MainActivity.storeOrders.getStoreOrdersArray().get(currOrder).getOrders();
            total = calculateTotal(MainActivity.storeOrders.getStoreOrdersArray().get(currOrder).getPrice());
        }
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, order);
        listView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
        adapter.notifyDataSetChanged();
        orderTotal.setText(String.valueOf(df.format(total)));

    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int currOrder =  Integer.parseInt(spinner.getSelectedItem().toString());
        int idx = findIndex(currOrder);
        ArrayList<MenuItem> order =  MainActivity.storeOrders.getStoreOrdersArray().get(idx).getOrders();
        double total = calculateTotal(MainActivity.storeOrders.getStoreOrdersArray().get(idx).getPrice());
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, order);
        listView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
        orderTotal.setText(String.valueOf(df.format(total)));

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {  //can leave empty

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //clicking an item in the listview
    }

    private int findIndex(int currOrder){
        for(int i = 0 ; i < MainActivity.storeOrders.getStoreOrdersArray().size(); i++){
            if(MainActivity.storeOrders.getStoreOrdersArray().get(i).getOrderNumber() == currOrder) {
                return i;
            }
        }
        return -1;
    }

    private double calculateTotal(double price){
        return price*salesTax + price;
    }
}
