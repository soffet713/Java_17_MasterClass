package com.methodreferencechallenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class Main {

    private static Random random = new Random();

    private record Person(String first) {

        public String last(String s) {
            return first + " " + s.substring(0, s.indexOf(" "));
        }
    }

    public static void main(String[] args) {

        String[] firstNames = {"JoHn","DaVid","MatThEw","PaUl","SaRah","EmIlY","BriTtnEy","MaRtIn","CoNnOr","SAm",
                "MicHelLe","AbiGaIl","JeSsIca","MaRk","FreDdIe","LaUra","AlEx","ScOtT","MeGaN","NaTasHa","BoB","AnNa",
                "HaNnAh","AvA","EmMe","EvE","OtTo"};

        List<String> nameList = new ArrayList<>(List.of(firstNames));

        UnaryOperator<String> toUpper = String::toUpperCase;
        BinaryOperator<String> append = String::concat;

        Person doug = new Person("Doug");

        List<UnaryOperator<String>> unaryOperators = new ArrayList<>(List.of(
                toUpper,
                s -> append.apply(s, " %s.".formatted(getRandomChar('A', 'P'))),
                s -> append.apply(s, " " + getReversedName(s.split(" ")[0])),
                Main::getReversedName,
                String::new,
                String::valueOf,
                doug::last
        ));

        applyUnaryOperatorChanges(nameList,unaryOperators);

    }

    public static void applyChanges(String[] names, List<UnaryOperator<String>> myFunctions) {
        List<String> nameList = new ArrayList<>(List.of(names));
        for(var function : myFunctions) {
            nameList.replaceAll(s -> s.transform(function));
            nameList.forEach(System.out::println);
        }
    }

    public static void applyUnaryOperatorChanges(List<String> nameList, List<UnaryOperator<String>> myFunctions) {
        //List<String> nameList = new ArrayList<>(List.of(names));
        for(var function : myFunctions) {
            nameList.replaceAll(s -> s.transform(function));
            nameList.forEach(System.out::println);
        }
    }

    public static char getRandomChar(char startChar, char endChar) {
        return (char) random.nextInt((int) startChar, (int)endChar+1);
    }

    private static String getReversedName(String firstName) {
        return new StringBuilder(firstName).reverse().toString().toUpperCase();
    }
}
