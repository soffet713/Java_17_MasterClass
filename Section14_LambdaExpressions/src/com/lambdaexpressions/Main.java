package com.lambdaexpressions;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>(List.of("alpha","bravo","charlie","delta"));

        for(String s : list) {
            System.out.println(s);
        }

        System.out.println("-".repeat(30));
        list.forEach(s -> System.out.println(s));

        String prefix = "nato";
        System.out.println("-".repeat(30));
        list.forEach((var myString) -> {
            char first = myString.charAt(0);
            System.out.println(prefix + " " + myString + " means " + first);
        });

        int result = calculator((a, b) -> a + b, 5, 2);

        var result2 = calculator((a,b) -> a/b, 10.0, 2.5);

        var result3 = calculator((a,b) -> a.toUpperCase() + " " + b.toUpperCase(), "Jason",
                "Momoa");
    }

    //Adding T here creates this as a Generic method. The T says it returns a generic Type
    //We want to use this calculator method to do different calculations on different types of data
    //The parameters are function of type Operation (our new interface), and two values of generic type T
    public static <T> T calculator(Operation<T> function, T value1, T value2) {

        T result = function.operate(value1, value2);
        System.out.println("Result of operation: " + result);
        return result;
    }
}
