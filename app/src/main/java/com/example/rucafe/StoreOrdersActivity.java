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

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class StoreOrdersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView listView;
    private ArrayAdapter<MenuItem> list;
    private Spinner spinner;
    private ArrayAdapter<Integer> orderNums;
    private TextView orderTotal;
    protected static final DecimalFormat df = new DecimalFormat("###,##0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_orders);
        spinner = findViewById(R.id.order_numbers);
        spinner.setOnItemSelectedListener(this);//WHY ERORR?????
        for(int i = 0; i < MainActivity.storeOrders.getStoreOrdersArray().size(); i++){
            orderNums.add(MainActivity.storeOrders.getStoreOrdersArray().get(i).getOrderNumber());
        }
        orderNums = new ArrayAdapter<Integer>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, (List<Integer>) orderNums);
        spinner.setAdapter(orderNums);
        orderNums.notifyDataSetChanged();
        listView = findViewById(R.id.store_orders_list);
        listView.setOnItemClickListener(this);
        int currOrder = Integer.parseInt(spinner.getSelectedItem().toString());
        double total = 0;
        ArrayList<MenuItem> order = new ArrayList<>();
        for(int i = 0 ; i < MainActivity.storeOrders.getStoreOrdersArray().size(); i++){
            if(MainActivity.storeOrders.getStoreOrdersArray().get(i).getOrderNumber() == currOrder){
                order =  MainActivity.storeOrders.getStoreOrdersArray().get(i).getOrders();
                total = MainActivity.storeOrders.getStoreOrdersArray().get(i).getTotal();
            }
        }
        list = new ArrayAdapter<MenuItem>(this, android.R.layout.simple_list_item_1, order);
        list.notifyDataSetChanged();
        orderTotal.setText(String.valueOf(total));
    }

    public void cancelOrder(View v) {
        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
        alert.setTitle("Order");
        alert.setMessage("Delete Order?");
        //handle the "YES" click
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(v.getContext(),
                        "Order Placed.", Toast.LENGTH_LONG).show();
                for(int i = 0; i < MainActivity.storeOrders.getStoreOrdersArray().size(); i++){
                    int num =  Integer.parseInt(spinner.getSelectedItem().toString());
                    if(MainActivity.storeOrders.getStoreOrdersArray().get(i).getOrderNumber() == num){
                        MainActivity.storeOrders.remove(MainActivity.storeOrders.getStoreOrdersArray().get(i));
                        orderNums.remove(num);
                    }
                }
                list.notifyDataSetChanged();
                orderNums.notifyDataSetChanged();

            }
            //handle the "NO" click
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(v.getContext(),
                        "Order Not Deleted.", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
