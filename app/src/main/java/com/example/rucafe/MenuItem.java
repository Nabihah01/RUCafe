package com.example.rucafe;

/**
 * This is the superclass of all menu items, such as donuts and coffee.
 * Any class defined for a menu item extends this class
 *  @author Nabihah, Maryam
 */
public abstract class MenuItem {
    /**
     * abstract method returns price of object
     * @return double price
     */
    public abstract double itemPrice();
}
