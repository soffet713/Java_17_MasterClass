package com.burger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Meal {

    private double base = 5.0;
    private Burger burger;
    private Item drink;
    private Item side;
    private double conversionRate;
    private char currencySymbol;

    public Meal() {
        this(1, '$');
    }

    public Meal(double conversionRate, char currencySymbol) {
        this.conversionRate = conversionRate;
        this.currencySymbol = currencySymbol;
        burger = new Burger("regular");
        drink = new Item("coke", "drink", 1.5);
        //System.out.println(drink.name);
        side = new Item("fries", "side", 2.0);
    }

    public double getTotal() {
        double total = burger.getPrice() + drink.price + side.price;
        return Item.getPrice(total, conversionRate);
    }

    @Override
    public String toString() {
        return "%s%n%s%n%s%n%26s%c%.2f".formatted(burger,drink,side,"Total Due: ",currencySymbol,getTotal());
    }

    public void addToppings(String... chosenToppings){
        burger.addToppings(chosenToppings);
    }

    private class Item {

        private String name;
        private String type;
        private double price;

        public Item(String name, String type) {
            this(name, type, type.equals("burger") ? base : 0);
        }

        public Item(String name, String type, double price) {
            this.name = name;
            this.type = type;
            this.price = price;
        }

        @Override
        public String toString() {
            return "%10s%15s %c%.2f".formatted(type,name,currencySymbol,getPrice(price, conversionRate));
        }

        private static double getPrice(double price, double rate) {
            return price * rate;
        }
    }

    private class Burger extends Item {

        private enum Topping {
            AVOCADO, BACON, CHEESE, KETCHUP, MAYO, MUSTARD, TOMATO, PICKLES, SRIRACHA, JALAPENOS ;

            private double getPrice() {
               return switch(this) {
                    case AVOCADO,BACON -> 1.5;
                    case CHEESE,PICKLES -> 1.0;
                    case JALAPENOS,SRIRACHA,TOMATO -> 0.5;
                    default -> 0;
                };
            }
        }

        private List<Item> toppings = new ArrayList<>();

        public Burger(String name) {
            super(name, "burger", 5.0);
        }

        public double getPrice() {
            double total = super.price;
            for(Item topping : toppings) {
                total += topping.price;
            }
            return total;
        }

        public void addToppings(String... chosenToppings) {
            Arrays.sort(chosenToppings);
            for(String t : chosenToppings) {
                try {
                    Topping topping = Topping.valueOf(t.toUpperCase());
                    toppings.add(new Item(topping.name(), "TOPPING", topping.getPrice()));
                } catch (IllegalArgumentException ie) {
                    System.out.println("No topping found for " + t);
                }
            }
        }

        @Override
        public String toString() {
            StringBuilder itemized = new StringBuilder(super.toString());
            for(Item topping : toppings) {
                itemized.append("\n");
                itemized.append(topping);
            }
            return itemized.toString();
        }
    }
}
