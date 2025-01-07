package com.regexminichallenges;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        String helloWorld = "Hello, World!";
        boolean match = helloWorld.matches("Hello, World!");
        System.out.println(match);

        String challenge2 = "[A-Z].*\\.";

        for (String s : List.of("The bike is red.",
                "I am a new student.",
                "hello world.",
                "How are you?")) {
            boolean matched = s.matches(challenge2);
            System.out.println(matched + ": " + s);
        }

        String regex = "^[A-Z][a-z\\s]*+[.]$";

        for (String s : List.of("Winter is coming.",
                "May the force be with you.",
                "hello puppet.",
                "I'll be back!")) {
            System.out.println(s.matches(regex) + " -> " + s);
        }

        System.out.println("-".repeat(75));

        String regex2 = "^[A-Z]\\p{all}+[.?!]$";
        for (String s : List.of("Winter is coming.",
                "May the Force be with you.",
                "hello puppet.",
                "Houston, we have a problem.",
                "Life is like a box of chocolates. You never know what you're gonna get.",
                "I'll be back.",
                "Magic Mirror on the wall, who is the fairest one of all?",
                "You can't handle the truth!")) {
            System.out.println(s.matches(regex2) + " -> " + s);
        }
    }
}
