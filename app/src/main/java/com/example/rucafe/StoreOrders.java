package com.example.project4;

import java.util.ArrayList;

/**
 * An instance of this class keeps a list of all orders placed
 * @author Nabihah, Maryam
 */
public class StoreOrders implements Customizable {
    private ArrayList<Order> storeOrders;

    /**
     * constructor for class
     */
    public StoreOrders(){
        this.storeOrders = new ArrayList<Order>();
    }

    /**
     * getter method for storeOrders
     * @return storeOrders
     */
    public ArrayList<Order> getStoreOrdersArray() {
        return storeOrders;
    }

    /**
     * overrides add method, adds order object to storeOrders list
     * @param obj object to be added
     * @return boolean
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Order){
            Order order = (Order) obj;
            storeOrders.add(order);
            return true;
        }
        return false;
    }

    /**
     * overrides remove method, removes order object from storeOrders list
     * @param obj object to be removed
     * @return boolean
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Order){
            Order order = (Order) obj;
            storeOrders.remove(order);
            return true;
        }
        return false;
    }

}
