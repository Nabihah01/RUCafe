package com.example.project4;

import java.util.ArrayList;

/**
 * This class extends MenuItem and represents a coffee object.
 * Has three attributes - cupsize, addins, and quantity
 *  @author Nabihah, Maryam
 */
public class Coffee extends MenuItem implements Customizable{
    private String cupSize;
    private ArrayList<String> addIns;
    private int quantity;
    private static final double ADDIN_PRICE = 0.30;
    private static final double SHORT = 1.69;
    private static final double TALL = 2.09;
    private static final double GRANDE = 2.49;
    private static final double VENTI = 2.89;

    /**
     * Constructor for coffee object
     * @param cupSize size of coffee
     * @param addIns list of addIns
     * @param quantity how many cups of coffee
     */
    public Coffee(String cupSize, ArrayList<String> addIns, int quantity){
        this.cupSize = cupSize;
        this.addIns = addIns;
        this.quantity = quantity;
    }

    /**
     * overrides itemPrice method, and calculates price for
     * coffee
     * @return double, price of coffee object
     */
    @Override
    public double itemPrice() {
        double price = 0;
        int addIn = addIns.size();
        price += addIn * ADDIN_PRICE;
        switch (cupSize) {
            case "Short":
                price += SHORT;
                break;
            case "Tall":
                price += TALL;
                break;
            case "Grande":
                price += GRANDE;
                break;
            case "Venti":
                price += VENTI;
                break;
        }
        return price * quantity;
    }

    /**
     * overrides method, adds addIn to addIns list
     * @param obj to be added
     * @return boolean
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof String){
            String addIn = (String) obj;
            addIns.add(addIn);
            return true;
        }
        return false;
    }

    /**
     * overrides method, removes addIn from addIn list
     * @param obj to be removed
     * @return boolean
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof String){
            String addIn = (String) obj;
            addIns.remove(addIn);
            return true;
        }
        return false;
    }

    /**
     * overrides toString method and returns string form of
     * coffee object
     * @return string
     */
    @Override
    public String toString(){
        if(!addIns.isEmpty()){
            return this.cupSize + " coffee with " + String.join(", ", this.addIns) +
                    " (" + this.quantity + ")";
        }
        else {
            return this.cupSize + " black coffee " + "(" + this.quantity + ")";
        }

    }
}
