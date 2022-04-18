package com.example.rucafe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class YourOrderActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private ArrayAdapter<MenuItem> adapter;
    private TextView salesTax;
    private TextView subtotal;
    private TextView total;
    protected static final DecimalFormat df = new DecimalFormat("###,##0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.your_order);
        listView = findViewById(R.id.your_order_list);
        listView.setOnItemClickListener(this);
        adapter = new ArrayAdapter<MenuItem>(this, android.R.layout.simple_list_item_1, MainActivity.yourOrder.getOrders());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        subtotal = findViewById(R.id.orders_subtotal);
        total = findViewById(R.id.orders_total);
        salesTax = findViewById(R.id.orders_salestax);
        displayOrderSubtotal();
    }

    @Override
    protected void onStart() {
        super.onStart();
        listView = findViewById(R.id.your_order_list);
        listView.setOnItemClickListener(this);
        //list not showing up!!
        adapter = new ArrayAdapter<MenuItem>(this, android.R.layout.simple_list_item_1, MainActivity.yourOrder.getOrders());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        subtotal = findViewById(R.id.orders_subtotal);
        total = findViewById(R.id.orders_total);
        salesTax = findViewById(R.id.orders_salestax);
        displayOrderSubtotal();
    }

    private void displayOrderSubtotal() {
        double subTotal = MainActivity.yourOrder.getPrice();
        subtotal.setText(String.valueOf(df.format(subTotal)));

        double salestax = (6.625 / 100) * subTotal;
        salesTax.setText(String.valueOf(df.format(salestax)));

        double t = subTotal + salestax;
        total.setText(String.valueOf(df.format(t)));
    }

    public void placeOrder(View v) {
        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
        alert.setTitle("Place Order");
        //handle the "YES" click
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(v.getContext(),
                        "Order Placed.", Toast.LENGTH_LONG).show();
                MainActivity.storeOrders.add(MainActivity.yourOrder);
                MainActivity.orderNum++;
                MainActivity.yourOrder = new Order(new ArrayList<>(), MainActivity.orderNum);
                subtotal.setText(String.valueOf("0.00"));
                total.setText(String.valueOf("0.00"));
                salesTax.setText(String.valueOf("0.00"));
                adapter.notifyDataSetChanged();

            }
            //handle the "NO" click
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(v.getContext(),
                        "Order Not Placed.", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder alert = new AlertDialog.Builder(adapterView.getContext());
        alert.setTitle("Delete Item from Order");
        //handle the "YES" click
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //remove the order that was selected
                MenuItem item = MainActivity.yourOrder.getOrders().get(i);
                MainActivity.yourOrder.remove(item);//idk if this is right either
                adapter.notifyDataSetChanged();
                displayOrderSubtotal();
                Toast.makeText(adapterView.getContext(),
                        "Item deleted from order.", Toast.LENGTH_LONG).show();
            }
            //handle the "NO" click
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(adapterView.getContext(),
                        "Item not deleted from order.", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();

    }

}
