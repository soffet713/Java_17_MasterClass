package com.abstractchallenge;

public class Main {

    public static void main(String[] args) {
        Store homemakers = new Store("Homemakers");

        homemakers.products.add(new ArtObject("Oil Painting", 1350,
                "Impressionistic work by ABF painted in 2010"));

        homemakers.products.add(new ArtObject("Sculpture", 2000,
                "Bronze work by JKF, produced in 1950"));

        homemakers.products.add(new FurnitureObject("Desk", 500.0,
                "Mahogany Desk"));

        homemakers.products.add(new FurnitureObject("Shelf", 400.0, "Mahogany Bookshelf"));

        homemakers.products.add(new FurnitureObject("Lamp", 200.0,
                "Tiffany Lamp with Hummingbirds"));

        homemakers.products.add(new ElectronicsObject("Television", 3000.0,
                "77-inch QD-OLED Television"));

        homemakers.products.add(new ElectronicsObject("AppleTV", 250.0,
                "AppleTV 4K HD"));

        homemakers.products.add(new ElectronicsObject("Laptop", 2800.0,
                "16-inch Macbook Pro M3 Pro 1 TB SSD"));

        homemakers.listProducts();



        System.out.println("-".repeat(30));
        System.out.println("\nOrder1");
        homemakers.createOrder(0,2,1);
        homemakers.addItemToOrder(0,3,2);
        homemakers.orders.get(0).printOrder();

        System.out.println("-".repeat(30));
        System.out.println("\nOrder2");
        homemakers.createOrder(1,5,1);
        homemakers.addItemToOrder(1,6,3);
        homemakers.addItemToOrder(1,7,1);
        homemakers.orders.get(1).printOrder();
    }
}
