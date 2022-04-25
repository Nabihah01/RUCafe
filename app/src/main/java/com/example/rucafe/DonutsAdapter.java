package com.example.rucafe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class DonutsAdapter extends RecyclerView.Adapter<DonutsAdapter.ItemsHolder> {
    private Context context; //need the context to inflate the layout
    //items hold all of our donuts
    private ArrayList<Donut> items; //need the data binding to each row of RecyclerView

    public DonutsAdapter(Context context, ArrayList<Donut> items) {
        this.context = context;
        this.items = items;
    }

    /**
     * This method will inflate the row layout for the items in the RecyclerView
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the row layout for the items
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view, parent, false);

        return new ItemsHolder(view, this);
    }

    /**
     * Assign data values for each row according to their "position" (index) when the item becomes
     * visible on the screen.
     * @param holder the instance of ItemsHolder
     * @param position the index of the item in the list of items
     */
    @Override
    public void onBindViewHolder(@NonNull ItemsHolder holder, int position) {
        //assign values for each row
        holder.donut_flavor.setText(items.get(position).getDonutTypeandFlavor());
        holder.donut_price.setText(String.valueOf(items.get(position).oneItemPrice()));
        holder.donut_quantity.setText(String.valueOf(items.get(position).getDonutQuantity()));
        holder.donut_image.setImageResource(items.get(position).getImage());
    }

    /**
     * Get the number of items in the ArrayList.
     * @return the number of items in the list.
     */
    @Override
    public int getItemCount() {
        return items.size(); //number of MenuItem in the array list.
    }

    /**
     * Get the views from the row layout file, similar to the onCreate() method.
     */
    public static class ItemsHolder extends RecyclerView.ViewHolder {
        DonutsAdapter donutsAdapter;
        private TextView donut_flavor, donut_price, donut_quantity;
        private ImageView donut_image;
        private Button btn_add, btn_minus;
        private ConstraintLayout parentLayout; //this is the row layout

        public ItemsHolder(@NonNull View itemView, DonutsAdapter donutsAdapter) {
            super(itemView);
            this.donutsAdapter = donutsAdapter;
            donut_flavor = itemView.findViewById(R.id.donut_flavor);
            donut_price = itemView.findViewById(R.id.donut_price);
            donut_quantity = itemView.findViewById(R.id.donut_quantity);
            donut_image = itemView.findViewById(R.id.donut_image);
            btn_add = itemView.findViewById(R.id.btn_add);
            btn_minus = itemView.findViewById(R.id.btn_minus);
            parentLayout = itemView.findViewById(R.id.rowLayout);
            setMinusButtonOnClick(itemView);
            setAddButtonOnClick(itemView); //register the onClicklistener for the button on each row.
        }

        /**
         * Set the onClickListener for the button on each row.
         * @param itemView
         */
        private void setAddButtonOnClick(@NonNull View itemView) {
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HashMap<String, Integer> donutsOrdered = OrderingDonutsActivity.getDonutsOrdered();

                    int quantity = Integer.parseInt(donut_quantity.getText().toString());
                    donut_quantity.setText(String.valueOf(quantity + 1));
                    donutsOrdered.put(String.valueOf(donut_flavor.getText()), quantity + 1);

                    int position = getAdapterPosition();
                    donutsAdapter.items.get(position).setDonutQuantity(quantity + 1);
                }
            });
        }

        private void setMinusButtonOnClick(@NonNull View itemView) {
            btn_minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HashMap<String, Integer> donutsOrdered = OrderingDonutsActivity.getDonutsOrdered();
                    int quantity = Integer.parseInt(donut_quantity.getText().toString());
                    if(quantity == 0) {
                        Toast.makeText(itemView.getContext(), "Cannot reduce quantity below zero", Toast.LENGTH_LONG).show();
                    } else {
                        donut_quantity.setText(String.valueOf(quantity - 1));
                        donutsOrdered.put(String.valueOf(donut_flavor.getText()), quantity - 1);

                        int position = getAdapterPosition();
                        donutsAdapter.items.get(position).setDonutQuantity(quantity - 1);
                    }
                }
            });
        }

    }

}
