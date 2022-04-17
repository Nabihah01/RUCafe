package com.example.rucafe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class YourOrderActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.your_order);
        listView = findViewById(R.id.your_order_list);
        listView.setOnItemClickListener(this);
        //list not showing up!!
        ArrayAdapter<MenuItem> list = new ArrayAdapter<MenuItem>(this, android.R.layout.simple_list_item_1, MainActivity.yourOrder.getOrders());
        listView.setAdapter(list);
    }

    public void placeOrder(View v) {
        MainActivity.orderNum++;
        MainActivity.storeOrders.add(MainActivity.yourOrder.getOrders());
        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
        alert.setTitle("Place Order");
        //handle the "YES" click
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(v.getContext(),
                        "Order Placed.", Toast.LENGTH_LONG).show();
            }
            //handle the "NO" click
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(v.getContext(),
                        "Order Placed.", Toast.LENGTH_LONG).show();
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
                listView.removeViewAt(i); //idk if this is right
                MainActivity.yourOrder.remove(i);//idk if this is right either
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
