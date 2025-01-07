package com.collectionsoverview;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {

        Collection<String> list = new HashSet<>();

        String[] names = {"Harry","Ron","Hermione","Neville","Draco"};
        list.addAll(Arrays.asList(names));
        System.out.println(list);

        list.add("Fred");
        list.addAll(Arrays.asList("George","Ginny","Luna","Sirius"));
        System.out.println(list);
        System.out.println("Ginny is in the list? " + list.contains("Ginny"));

        list.removeIf(s -> s.charAt(0) == 'G');
        System.out.println(list);
        System.out.println("Ginny is in the list? " + list.contains("Ginny"));
    }
}
