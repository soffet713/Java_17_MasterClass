package com.burger;

public class Store {

    public static void main(String[] args) {

        Meal regularMeal = new Meal();
        regularMeal.addToppings("Mustard", "Jalapenos", "Cheese", "Sriracha");
        System.out.println(regularMeal);

        System.out.println("-".repeat(50));

        Meal USRegularMeal = new Meal(0.68,'$');
        System.out.println(USRegularMeal);

        System.out.println("-".repeat(50));

        Meal JPRegularMeal = new Meal((.68*160),'¥');
        System.out.println(JPRegularMeal);
    }
}
