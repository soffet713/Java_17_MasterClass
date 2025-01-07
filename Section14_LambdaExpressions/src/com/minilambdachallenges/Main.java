package com.minilambdachallenges;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Main {

    public static void main(String[] args) {

        //Code from challenge slide
        //Challenge: write the following anonymous class code as a lambda expression
        Consumer<String> printWords = new Consumer<String>() {

            @Override
            public void accept(String sentence) {
                String[] parts = sentence.split(" ");
                for(String part : parts) {
                    System.out.println(part);
                }
            }
        };

        String myString = "I am testing the printTheParts method for Java 17 Masterclass.";

        Consumer<String> printTheParts = (sentence) -> { String[] parts = sentence.split(" ");
            for(String part : parts) {
                System.out.println(part);
            }
        };

        printTheParts.accept(myString);
        System.out.println("-".repeat(15));
        processString(myString,printTheParts);
        System.out.println("-".repeat(15));

        Consumer<String> printWordsForEach = (sentence) -> { String[] parts = sentence.split(" ");
            Arrays.asList(parts).forEach(s -> System.out.println(s));
        };
        printWordsForEach.accept(myString);
        System.out.println("-".repeat(15));

        Consumer<String> printWordsConcise = (sentence) -> {
            Arrays.asList(sentence.split(" ")).forEach(s -> System.out.println(s));
        };
        printWordsForEach.accept(myString);

        UnaryOperator<String> everySecondChar = source -> {
            StringBuilder returnVal = new StringBuilder();
            for(int i=0; i < source.length(); i++) {
                if(i % 2 == 1) {
                    returnVal.append(source.charAt(i));
                }
            }
            return returnVal.toString();
        };

        System.out.println("-".repeat(15));
        System.out.println(everySecondChar.apply(myString));

        Function<String, String> everySecondCharacter = source -> {
            StringBuilder returnVal = new StringBuilder();
            for(int i=0; i < source.length(); i++) {
                if(i % 2 == 1) {
                    returnVal.append(source.charAt(i));
                }
            }
            return returnVal.toString();
        };

        System.out.println("-".repeat(15));
        System.out.println(everySecondCharacter.apply(myString));

        String miniChallenge3 = "1234567890";
        printValues(everySecondChar,miniChallenge3);

        System.out.println("-".repeat(15));
        System.out.println(everySecondCharacterMethod(everySecondCharacter,miniChallenge3));


        Supplier<String> iLoveJava = () -> "I Love Java!";
        System.out.println(iLoveJava.get());

        var supplierResult = iLoveJava.get();
        System.out.println(supplierResult);
    }

    public static <T> void processString(T t1, Consumer<T> consumer) {
        consumer.accept(t1);
    }

    public static String everySecondCharacterMethod(Function<String, String> func, String source) {
        return func.apply(source);
    }

    public static <T> void printValues(UnaryOperator<T> function, T t) {
        System.out.println("-".repeat(35));
        System.out.println(function.apply(t));
    }
}
