package com.varargs;

public class Main {

    public static void main(String... args) {

        String helloAgain = "Hello World again...it's nice to see you again old friend";
        System.out.println(helloAgain);

        String[] splitString = helloAgain.split(" ");
        printText(splitString);

        System.out.println("_".repeat(20));
        printText("Hello");

        System.out.println("_".repeat(20));
        printText("Hello","World","again");

        System.out.println("_".repeat(20));
        printText();

        String[] sArray = {"first", "second", "third", "fourth", "fifth"};
        System.out.println(String.join(",",sArray));
    }

    public static void printText(String... textList) {
        for(String t : textList) {
            System.out.println(t);
        }
    }
}
