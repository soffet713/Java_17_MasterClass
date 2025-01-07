package com.arraylistchallenge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<String> groceryList = new ArrayList<>();
        boolean shutDown = false;
        while(!shutDown) {
            printOptions();
            switch(Integer.parseInt(sc.nextLine())) {
                case 2 -> removeItems(groceryList);
                case 1 -> addItems(groceryList);
                case 0 -> shutDown = true;
                default -> System.out.println("Please enter a valid number. 0, 1, or 2.");
            }
        }
        System.out.println("Shutting Down...");
        System.out.println("Final List: " + groceryList);
    }

    private static void printList(ArrayList<String> list) {
        list.sort(Comparator.naturalOrder());
        System.out.print("Current List: ");
        System.out.println(list);
    }

    private static void addItems(ArrayList<String> list) {
        System.out.println("Please input item(s) to add to list [separate items by comma]");
        String[] itemArray = sc.nextLine().split(",");
        for (String s : itemArray) {
            if (list.contains(s.trim())) {
                continue;
            }
            list.add(s.trim());
        }
        printList(list);
    }

    private static void removeItems(ArrayList<String> list) {
        System.out.println("Please input item(s) to remove from list [separate items by comma]");
        String[] itemArray = sc.nextLine().split(",");
        for(String item : itemArray) {
            list.remove(item.trim());
        }
        printList(list);
    }

    private static void printOptions() {

        String textBlock = """
                Available Options:
                0 - Shutdown
                1 - Add item(s) to list (comma delimited list)
                2 - Remove item(s) from list (comma delimited list)
                Enter a number for which action you want to do:""";
        System.out.print(textBlock + " ");
    }
}
