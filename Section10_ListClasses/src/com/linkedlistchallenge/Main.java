package com.linkedlistchallenge;

import java.util.LinkedList;
import java.util.Scanner;

record Town(String name, int distance) {
    @Override
    public String toString() {
        return String.format("%s (%d km)", name, distance);
    }
}

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        LinkedList<Town> towns = new LinkedList<>();
        addTown(towns, new Town("Sydney", 0));
        addTown(towns, new Town("Adelaide", 1374));
        addTown(towns, new Town("Alice Springs", 2771));
        addTown(towns, new Town("Brisbane", 917));
        addTown(towns, new Town("Darwin", 3972));
        addTown(towns, new Town("Melbourne", 877));
        addTown(towns, new Town("Perth", 3923));

        System.out.println(towns);

        var iterator = towns.listIterator();
        boolean quit = false;
        boolean forward = true;

        printOptions();

        while(!quit) {
            if(!iterator.hasPrevious()) { forward=true; }
            if(!iterator.hasNext()) { forward=false; }
            System.out.print("Enter Value: ");
            String menuOption = sc.nextLine().toUpperCase().substring(0,1);
            Town currTown = towns.getFirst();
            switch(menuOption) {
                case "F" :
                    if(!forward) {
                        forward = true;
                        if(iterator.hasNext()) {
                            iterator.next();    //adjust position forward
                        }
                    }
                    if(iterator.hasNext()) {
                        currTown = iterator.next();
                        System.out.println("Next location: " + currTown.name() + " | Distance: " +
                                currTown.distance());
                    } else {
                        System.out.println("You have reached your final destination: " + towns.getLast().name() +
                                " | Distance: " + towns.getLast().distance());
                    }
                    break;
                case "B" :
                    if(forward) {
                        forward = false;
                        if(iterator.hasPrevious()) {
                            iterator.previous();    //adjust position backward
                        }
                    }
                    if(iterator.hasPrevious()) {
                        System.out.println(iterator.previous());
                    } else {
                        System.out.println("You are currently at the starting point of the itinerary: " +
                            towns.getFirst().name());
                    }
                    break;
                case "L" : listPlaces(towns);
                    break;
                case "M" : printOptions();
                    break;
                case "Q" : System.out.println("Quitting...");
                            quit = true;
                    break;
                default :
                    System.out.println();
                    System.out.print("Invalid menu option. ");
                    break;
            }
        }
    }

    private static void addTown(LinkedList<Town> list, Town town) {
        if(list.contains(town)) {
            System.out.println("Found duplicate: " + town);
            return;
        }

        for(Town t : list) {
            if(t.name().equalsIgnoreCase(town.name()) && t.distance()==town.distance()) {
                System.out.println("Found duplicate: " + town);
                return;
            }
        }

        int matchedIndex = 0;
        for(var listTown : list) {
            if(town.distance() < listTown.distance()) {
                list.add(matchedIndex,town);
                return;
            }
            matchedIndex++;
        }

        list.add(town);
    }

    private static void listPlaces(LinkedList<Town> list) {
        var iterator = list.listIterator(1);
        Town previousTown = list.getFirst();
        System.out.println("Trip will begin in " + previousTown.name());
        while(iterator.hasNext()) {
            Town currTown = iterator.next();
            System.out.println("--> From: " + previousTown.name() + " to " + currTown.name() +
                    " | Distance: " + currTown.distance());
            previousTown = currTown;
        }
        System.out.println("Trip ends in " + list.getLast().name());
    }

    private static void printOptions() {

        String textBlock = """
                Available Actions (select word or letter)
                (F)orward
                (B)ackward
                (L)ist Places
                (M)enu
                (Q)uit""";
        System.out.println(textBlock);
    }
}
