package com.todaduc.bakingapp.entities;

/**
 * Created by ddjankou on 8/24/2017.
 */

public class Ingredient {
    private String quantity;
    private String measure;
    private String description;

    public Ingredient(String quantity, String measure, String description) {
        this.quantity = quantity;
        this.measure = measure;
        this.description = description;
    }

}
