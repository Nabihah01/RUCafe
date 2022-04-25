package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import java.util.ArrayList;

/**
 * This class processes the GUI from activity_main_menu.xml and directs
 *  user to respective donuts, coffee, their orders, store orders windows.
 *
 * @author Nabihah, Maryam
 **/
public class MainActivity extends AppCompatActivity {

    public static int orderNum = 1;
    public static Order yourOrder = new Order(new ArrayList<>(), orderNum);
    public static StoreOrders storeOrders = new StoreOrders();

    /**
     * overrides onCreate method
     * @param savedInstanceState instance of Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton donutsButton = findViewById(R.id.orderDonuts);

        donutsButton.setOnClickListener(new View.OnClickListener() {
            /**
             * handles orderDonuts button click, opens donuts window
             * @param v instance of View
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OrderingDonutsActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Handles the orderCoffee button click, opens coffee window
     * @param view instance of View
     */
    public void orderingCoffee(View view) {
        Intent intent = new Intent(this, OrderingCoffeeActivity.class);
        startActivity(intent);
    }

    /**
     * Handles the viewYourOrder button click, opens your orders window
     * @param view instance of View
     */
    public void viewYourOrder(View view) {
        Intent intent = new Intent(this, YourOrderActivity.class);
        startActivity(intent);
    }

    /**
     * Handles the viewStoreOrder button click, opens store orders window
     * @param view instance of View
     */
    public void viewStoreOrders(View view) {
        Intent intent = new Intent(this, StoreOrdersActivity.class);
        startActivity(intent);
    }
}