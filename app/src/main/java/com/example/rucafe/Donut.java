package com.example.rucafe;

import java.util.ArrayList;

/**
 * This class extends MenuItem and represents a donut object,
 * has three attributes- type, quantity, and flavor
 * @author Nabihah, Maryam
 */
public class Donut extends MenuItem {
    private String donutType;
    private int donutQuantity;
    private String donutFlavor;
    private int donutImage;

    private static final double YEAST = 1.59;
    private static final double CAKE = 1.79;
    private static final double HOLE = 0.39;

    /**
     * Constructor for donut
     * @param donutType type of donut
     * @param donutFlavor flavor of donut
     * @param donutQuantity quantity of donut selected
     */

    public Donut(String donutType, String donutFlavor, int donutQuantity){
        this.donutType = donutType;
        this.donutQuantity = donutQuantity;
        this.donutFlavor = donutFlavor;
    }

    public Donut(String donutType, String donutFlavor, int donutQuantity, int donutImage){
        this.donutType = donutType;
        this.donutQuantity = donutQuantity;
        this.donutFlavor = donutFlavor;
        this.donutImage = donutImage;
    }

    /**
     * overrides itemPrice method in MenuItem and calculates
     * total for donut.
     * @return double, price of donut
     */
    @Override
    public double itemPrice(){
        if(donutType.equals("Yeast")){
            return YEAST * donutQuantity;
        } else if (donutType.equals("Cake")) {
            return CAKE * donutQuantity;
        }
        else {
            return HOLE * donutQuantity;
        }
    }

    public String getDonutTypeandFlavor() {
        return donutFlavor + " " + donutType;
    }

    public int getImage() {
        return donutImage;
    }

    /**
     * overrides toString method and returns string form of
     * donut object
     * @return string
     */
    @Override
    public String toString(){
        if(donutType.equals("Donut Holes")) {
            return this.donutFlavor + " " + this.donutType + " (" + this.donutQuantity + ")";
        } else {
            return this.donutFlavor + " " + this.donutType + " donut " + "(" + this.donutQuantity + ")";
        }
    }
}
