package com.arraylists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MoreLists {

    public static void main(String[] args) {

        String[] items = {"apples", "bananas", "milk", "eggs"};

        List<String> list = List.of(items);
        System.out.println(list);

        //System.out.println(list.getClass().getName());
        //list.add("yogurt");

        ArrayList<String> groceries = new ArrayList<>(list);
        groceries.add("yogurt");

        System.out.println(groceries);

        ArrayList<String> nextList = new ArrayList<>(
                List.of("pickles", "mustard", "cheese"));
        System.out.println(nextList);

        groceries.addAll(nextList);
        System.out.println(groceries);

        if(groceries.contains("mustard")) {
            System.out.println("groceries list contains mustard");
        }

        groceries.add("bananas");
        System.out.println("first time bananas is on the list = " + groceries.indexOf("bananas"));
        System.out.println("last time bananas is on the list = " + groceries.lastIndexOf("bananas"));

        System.out.println(groceries);
        groceries.remove(1);
        System.out.println(groceries);
        groceries.remove("cheese");
        System.out.println(groceries);

        groceries.removeAll(List.of("milk","pickles"));
        System.out.println(groceries);

        groceries.retainAll(List.of("apples","eggs","yogurt","mustard"));
        System.out.println(groceries);

        groceries.clear();
        System.out.println(groceries);
        System.out.println("isEmpty = " + groceries.isEmpty());

        groceries.addAll(List.of("apples","yogurt","eggs","mustard","bananas","pizza","chicken"));
        System.out.println(groceries);
        groceries.sort(Comparator.naturalOrder());
        System.out.println(groceries);

        groceries.sort(Comparator.reverseOrder());
        System.out.println(groceries);

        var groceryArray = groceries.toArray(new String[groceries.size()]);
        System.out.println(Arrays.toString(groceryArray));
    }
}
