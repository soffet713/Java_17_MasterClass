package com.regularexpressions;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String helloWorld = "%s %s".formatted("Hello", "World");
        String helloWorld2 = String.format("%s %s", "Hello", "World");
        System.out.println("Using string's formatted method: " + helloWorld);
        System.out.println("Using String.format: " + helloWorld2);

        String helloWorld3 = Main.format("%s %s", "Hello", "World");
        System.out.println("Using Main.format: " + helloWorld3);

        String testString = "Anyone can Learn abc's, 123's, and any regular expression";
        String replacement = "(-)";

        String[] patterns = {
                "[a-zA-Z]*$",
                "^[a-zA-Z]{3}",
                "[aA]ny\\b"
        };

        for (String pattern : patterns) {
            String output = testString.replaceFirst(pattern, replacement);
            System.out.println("Pattern: " + pattern + " => " + output);
        }

        // Song of the Witches in Macbeth, a Play by Shakespeare
        String paragraph = """
                Double, double toil and trouble;
                Fire burn and caldron bubble.
                Fillet of a fenny snake,
                In the caldron boil and bake
                Eye of newt and toe of frog,
                Wool of bat and tongue of dog,
                Adder's fork and blind-worm's sting,
                Lizard's leg and howlet's wing,
                For a charm of powerful trouble,
                Like a hell-broth boil and bubble.
                """;

        String[] lines = paragraph.split("\\R");
        System.out.println("This paragraph has " + lines.length + " lines");
        String[] words = paragraph.split("\\s");
        System.out.println("This paragraph has " + words.length + " words");
        System.out.println(paragraph.replaceAll("[a-zA-Z]+ble","[GRUB]"));

        Scanner sc = new Scanner(paragraph);
        System.out.println(sc.delimiter());
        sc.useDelimiter("\\R");

        /*
        while(sc.hasNext()) {
            String element = sc.next();
            System.out.println(element);
        }
        */
        //sc.tokens()
        //        .map(s -> Arrays.stream(s.split("\\s+")).count())
        //        .forEach(System.out::println);

        //sc.tokens()
        //        .map(s -> s.replaceAll("\\p{Punct}", ""))
        //        .flatMap(s -> Arrays.stream(s.split("\\s+")))
        //        .filter(s -> s.matches("[a-zA-Z]+ble"))
        //        .forEach(System.out::println);

        System.out.println(sc.findInLine("[a-zA-Z]+ble"));
        System.out.println(sc.findInLine("[a-zA-Z]+ble"));
        sc.close();
    }

    private static String format(String regexp, String... args) {

        int index = 0;
        /* instead of just using "contains" for a string literal, we can use matches and a regular expression - below
        while (regexp.contains("%s")) {
            regexp = regexp.replaceFirst("%s", args[index++]);
        }
        return regexp;
         */
        while (regexp.matches(".*%s.*")) {
            regexp = regexp.replaceFirst("%s", args[index++]);
        }
        return regexp;
    }
}
