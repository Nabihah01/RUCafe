package com.example.rucafe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This class processes the GUI from the your_order.xml to
 * allow the user to view their order and cancel selected items from it.
 *
 * @author Nabihah, Maryam
 **/
public class YourOrderActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private ArrayAdapter<MenuItem> adapter;
    private TextView salesTax;
    private TextView subtotal;
    private TextView total;
    protected static final DecimalFormat df = new DecimalFormat("###,##0.00");
    private static final String zero_total = "0.00";
    private static final double sales_tax = 6.625 / 100;

    /**
     * overrides onCreate method and initializes listview to display user's order
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.your_order);
        listView = findViewById(R.id.your_order_list);
        listView.setOnItemClickListener(this);
        adapter = new ArrayAdapter<MenuItem>(this, android.R.layout.simple_list_item_1,
                MainActivity.yourOrder.getOrders());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        subtotal = findViewById(R.id.orders_subtotal);
        total = findViewById(R.id.orders_total);
        salesTax = findViewById(R.id.orders_salestax);
        displayOrderSubtotal();
    }

    /**
     * overrides onStart method to initialize listview to display user's order
     */
    @Override
    protected void onStart() {
        super.onStart();
        listView = findViewById(R.id.your_order_list);
        listView.setOnItemClickListener(this);

        adapter = new ArrayAdapter<MenuItem>(this, android.R.layout.simple_list_item_1,
                MainActivity.yourOrder.getOrders());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        subtotal = findViewById(R.id.orders_subtotal);
        total = findViewById(R.id.orders_total);
        salesTax = findViewById(R.id.orders_salestax);
        displayOrderSubtotal();
    }

    /**
     * helper method to calculate subtotal, salestax, and total to display on screen.
     */
    private void displayOrderSubtotal() {
        double subTotal = MainActivity.yourOrder.getPrice();
        subtotal.setText(String.valueOf(df.format(subTotal)));

        double salestax = sales_tax * subTotal;
        salesTax.setText(String.valueOf(df.format(salestax)));

        double t = subTotal + salestax;
        total.setText(String.valueOf(df.format(t)));
    }

    /**
     * handles place order button click and adds order to store orders
     * @param v instance of View
     */
    public void placeOrder(View v) {
        if(MainActivity.yourOrder.getOrders().isEmpty()){
            Toast.makeText(v.getContext(), "No orders to place.", Toast.LENGTH_LONG).show();
            return;
        }
        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
        alert.setTitle("Place Order");
        //handle the "YES" click
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            /**
             * event handler for when user clicks yes button in alert dialog
             * @param dialog DialogInterface
             * @param which int
             */
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(v.getContext(),
                        "Order Placed.", Toast.LENGTH_LONG).show();
                Order order = new Order
                        ((ArrayList<MenuItem>) MainActivity.yourOrder.getOrders().clone(),
                                MainActivity.orderNum);
                MainActivity.storeOrders.add(order);
                MainActivity.orderNum++;
                MainActivity.yourOrder.getOrders().clear();
                MainActivity.yourOrder = new Order(new ArrayList<MenuItem>(), MainActivity.orderNum);
                subtotal.setText(String.valueOf(zero_total));
                total.setText(String.valueOf(zero_total));
                salesTax.setText(String.valueOf(zero_total));
                adapter.notifyDataSetChanged();

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
                        "Order Not Placed.", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    /**
     * handles selecting item from listview and deletes from order.
     * @param adapterView instance of AdapterView
     * @param view instance of View
     * @param i int
     * @param l long
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder alert = new AlertDialog.Builder(adapterView.getContext());
        alert.setTitle("Delete from Order");
        alert.setMessage("Delete Selected Item from Order?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            /**
             * event handler for when user clicks yes button in alert dialog
             * @param dialog DialogInterface
             * @param which int
             */
            public void onClick(DialogInterface dialog, int which) {
                //remove the order that was selected
                MenuItem item = MainActivity.yourOrder.getOrders().get(i);
                MainActivity.yourOrder.remove(item);
                adapter.notifyDataSetChanged();
                displayOrderSubtotal();
                Toast.makeText(adapterView.getContext(),
                        "Item deleted from order.", Toast.LENGTH_LONG).show();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            /**
             * event handler for when user clicks no button in alert dialog
             * @param dialog DialogInterface
             * @param which int
             */
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(adapterView.getContext(),
                        "Item not deleted from order.", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

}
