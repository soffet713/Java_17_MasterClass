package com.linkedlists;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {

    public static void main(String[] args) {

        LinkedList<String> placesToVisit = new LinkedList<>();

        placesToVisit.add("Sydney");
        placesToVisit.add(0,"Canberra");
        System.out.println(placesToVisit);
        placesToVisit.add("Osaka");
        placesToVisit.add("Chiba");
        placesToVisit.add("Tokyo");
        placesToVisit.add("Nagoya");
        placesToVisit.add("Narashino");
        System.out.println(placesToVisit);
        System.out.println("-".repeat(60));
        placesToVisit.sort(Comparator.naturalOrder());
        System.out.println(placesToVisit);
        System.out.println("-".repeat(60));

        addMoreElements(placesToVisit);

        System.out.println(placesToVisit);
        System.out.println("-".repeat(115));
        placesToVisit.sort(Comparator.naturalOrder());
        System.out.println(placesToVisit);
        System.out.println("-".repeat(115));
        removeElements(placesToVisit);
        System.out.println("-".repeat(35));
        System.out.println(placesToVisit);
        pushElementsBack(placesToVisit);
        System.out.println(placesToVisit);

        gettingElements(placesToVisit,4);

        //printItinerary(placesToVisit);
        //printItinerary2(placesToVisit);
        //printItinerary3(placesToVisit);
        testIterator(placesToVisit);
    }

    private static void addMoreElements(LinkedList<String> list) {
        list.addFirst("Vancouver");
        list.addLast("Santorini");
        //Queue methods
        list.offer("Melbourne");
        list.offerFirst("Dublin");
        list.offerLast("Rome");
        //Stack Methods
        list.push("Chicago");
    }

    private static void removeElements(LinkedList<String> list) {
        list.remove(5);
        list.remove("Narashino");

        System.out.println(list);
        System.out.println("-".repeat(97));
        String s1 = list.remove(); //removes first element in list
        System.out.println(s1 + " was removed from list.");
        System.out.println("-".repeat(35));
        String s2 = list.removeFirst(); //removes first element in list
        System.out.println(s2 + " was removed from list.");
        System.out.println("-".repeat(30));
        String s3 = list.removeLast(); //removes last element in list
        System.out.println(s3 + " was removed from list.");
        System.out.println("-".repeat(30));
        // Queue/Deque poll methods
        String p1 = list.poll(); //removes the first element in list
        System.out.println(p1 + " was removed from list");
        System.out.println("-".repeat(30));
        String p2 = list.pollFirst(); //removes the first element in list
        System.out.println(p2 + " was removed from list");
        System.out.println("-".repeat(30));
        String p3 = list.pollLast(); //removes the last element in list
        System.out.println(p3 + " was removed from list");
        System.out.println("-".repeat(30));
        System.out.println("Removing Melbourne...");
        System.out.println("-".repeat(30));
        list.remove("Melbourne");
        String p4 = list.pop(); //removes the first element
        System.out.println("-".repeat(30));
        System.out.println(p4 + " was removed from list");
        System.out.println("Removing Sydney...");
        System.out.println("-".repeat(30));
        list.remove("Sydney");
    }

    private static void pushElementsBack(LinkedList<String> list) {
        System.out.println("Pushing Dublin...");
        System.out.println("-".repeat(30));
        list.push("Dublin");
        System.out.println("Pushing Chiba...");
        System.out.println("-".repeat(30));
        list.push("Chiba");
        System.out.println("Pushing Vancouver...");
        System.out.println("-".repeat(30));
        list.push("Vancouver");
        System.out.println("Pushing Chicago...");
        System.out.println("-".repeat(30));
        list.push("Chicago");
    }

    private static void gettingElements(LinkedList<String> list, int x) {

        System.out.println("Retrieved Element = " + list.get(x));
        System.out.println("First Element = " + list.getFirst());
        System.out.println("Last Element = " + list.getLast());

        System.out.println("Chicago is at position: " + list.indexOf("Chicago"));
        System.out.println("Rome is at position: " + list.lastIndexOf("Rome"));

        //Queue retrieval method
        System.out.println("Element from element() = " + list.element()); //returns the first element in list

        //Stack retrieval methods
        System.out.println("Element from peek() = " + list.peek()); //returns first element in list
        System.out.println("Element from peekFirst() = " + list.peekFirst());   //returns first element in list
        System.out.println("Element from peekLast() = " + list.peekLast()); //returns last element in list
    }

    public static void printItinerary(LinkedList<String> list) {

        System.out.println("Trip starts in " + list.getFirst());
        for(int i=1; i<list.size(); i++) {
            System.out.println("--> From: " + list.get(i-1) + " to " + list.get(i));
        }
        System.out.println("Trip ends in " + list.getLast());
    }

    public static void printItinerary2(LinkedList<String> list) {

        System.out.println("Trip starts in " + list.getFirst());
        String previousTown = list.getFirst();
        for(String town : list) {
            System.out.println("--> From: " + previousTown + " to " + town);
            previousTown = town;
        }
        System.out.println("Trip ends in " + list.getLast());
    }

    public static void printItinerary3(LinkedList<String> list) {

        System.out.println("Trip starts in " + list.getFirst());
        String previousTown = list.getFirst();
        ListIterator<String> iterator = list.listIterator(1);
        while(iterator.hasNext()) {
            var town = iterator.next();
            System.out.println("--> From: " + previousTown + " to " + town);
            previousTown = town;
        }
        System.out.println("Trip ends in " + list.getLast());
    }

    private static void testIterator(LinkedList<String> list) {

        var iterator = list.listIterator();
        while(iterator.hasNext()) {
            //System.out.println(iterator.next());
            if(iterator.next().equals("Vancouver")) {
                iterator.add("Osaka");
            }
        }
        while(iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }
        System.out.println(list);

        var iterator2 = list.listIterator(3);
        System.out.println(iterator2.previous());
    }
}
