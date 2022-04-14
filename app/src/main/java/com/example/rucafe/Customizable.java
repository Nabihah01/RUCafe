package com.example.project4;

/**
 *  Java interface
 *  @author Nabihah, Maryam
 */
public interface Customizable {
    /**
     * adds an object
     * @param obj
     * @return true if object was successfully added
     */
    boolean add(Object obj);
    /**
     * removed an object
     * @param obj
     * @return true if object was successfully removed
     */
    boolean remove(Object obj);
}
