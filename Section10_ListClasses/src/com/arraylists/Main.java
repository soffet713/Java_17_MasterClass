package com.arraylists;

import java.util.ArrayList;
import java.util.Arrays;

record GroceryItem(String name, String type, int count) {

    public GroceryItem(String name) {
        this(name, "DAIRY",1);
    }
}

public class Main {

    public static void main(String[] args) {

        GroceryItem[] groceryArray = new GroceryItem[3];
        groceryArray[0] = new GroceryItem("milk");
        groceryArray[1] = new GroceryItem("apples", "PRODUCE", 6);
        groceryArray[2] = new GroceryItem("oranges", "PRODUCE", 5);

        System.out.println(Arrays.toString(groceryArray));

        ArrayList<GroceryItem> groceries = new ArrayList<>();
        groceries.add(new GroceryItem("Butter"));
        groceries.add(new GroceryItem("Yogurt", "PRODUCE",12));
        groceries.add(new GroceryItem("milk"));
        groceries.add(0, new GroceryItem("apples", "PRODUCE", 6));
        groceries.add(new GroceryItem("oranges", "PRODUCE", 5));

        for (GroceryItem grocery : groceries) {
            System.out.println(grocery);
        }
    }
}
