package com.binaryoperator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

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

        var coords = createDoubleArray();
        DecimalFormat df = new DecimalFormat("##.####");

        coords.forEach(s -> System.out.printf("[%8.4f, %8.4f ]%n", s[0],s[1]));

        BiConsumer<Double,Double> p1 = (lat, lng) -> System.out.printf("[lat:%.3f lon:%.3f]%n", lat, lng);
        var firstPoint = coords.get(0);
        processPoint(firstPoint[0],firstPoint[1],p1);
        System.out.println();
        System.out.println("-".repeat(45));
        coords.forEach(s -> processPoint(s[0], s[1], p1));

        list.removeIf(s -> s.equalsIgnoreCase("bravo"));
        list.forEach(s -> System.out.println(s));

        list.addAll(List.of("echo","easy","earnest"));
        list.forEach(s -> System.out.println(s));

        System.out.println();
        System.out.println("-".repeat(45));

        list.removeIf(s -> s.startsWith("ea"));
        list.forEach(s -> System.out.println(s));

        list.replaceAll(s -> s.charAt(0) + " - " + s.toUpperCase());
        System.out.println();
        System.out.println("-".repeat(45));
        list.forEach(s -> System.out.println(s));

        String[] emptyStrings = new String[10];
        System.out.println(Arrays.toString(emptyStrings));
        Arrays.fill(emptyStrings,"");
        System.out.println(Arrays.toString(emptyStrings));

        Arrays.setAll(emptyStrings, (i) -> "" + (i + 1) + ". ");
        System.out.println(Arrays.toString(emptyStrings));

        Arrays.setAll(emptyStrings, (i) -> "" + (i + 1) + ". "
            + switch(i) {
                    case 0 -> "one";
                    case 1 -> "two";
                    case 2 -> "three";
                    case 3 -> "four";
                    case 4 -> "five";
                    case 5 -> "six";
                    case 6 -> "seven";
                    case 7 -> "eight";
                    case 8 -> "nine";
                    case 9 -> "ten";
                    default -> "";
                }
        );
        System.out.println(Arrays.toString(emptyStrings));

        String[] names = {"David","Matthew","John","Paul","Sarah","Ann","Emily","Nicole","Arthur","Samantha","Peter"};
        String[] randomList = randomlySelectedValues(21,names,() -> new Random().nextInt(0,names.length));
        System.out.println(Arrays.toString(randomList));

        List<double[]> randomDoubles = randomlySelectedDoubles(7);
        randomDoubles.forEach(s -> System.out.printf("[%8.4f, %8.4f ]%n", s[0],s[1]));
    }

    //Adding T here creates this as a Generic method. The T says it returns a generic Type
    //We want to use this calculator method to do different calculations on different types of data
    //The parameters are function of type BinaryOperator (our new interface), and two values of generic type T
    public static <T> T calculator(BinaryOperator<T> function, T value1, T value2) {

        T result = function.apply(value1, value2);
        System.out.println("Result of operation: " + result);
        return result;
    }

    public static List<double[]> randomlySelectedDoubles(int count) {
        List<double[]> selectedValues = new ArrayList<>(count);
        Random random = new Random();
        for(int i=0; i<count; i++) {
            selectedValues.add(new double[]{random.nextDouble(0.000,50.000),
                    random.nextDouble(-100.000,-0.001)});
        }
        return selectedValues;
    }

    public static String[] randomlySelectedValues(int count, String[] values, Supplier<Integer> s) {
        String[] selectedValues = new String[count];
        for(int i=0; i < count; i++) {
            selectedValues[i] = values[s.get()];
        }
        return selectedValues;
    }

    public static List<double[]> createDoubleArray() {
        Random random = new Random();

        return Arrays.asList(
                new double[]{random.nextDouble(0.000,50.000),random.nextDouble(-100.000,-0.001)},
                new double[]{random.nextDouble(0.000,50.000),random.nextDouble(-100.000,-0.001)},
                new double[]{random.nextDouble(0.000,50.000),random.nextDouble(-100.000,-0.001)},
                new double[]{random.nextDouble(0.000,50.000),random.nextDouble(-100.000,-0.001)},
                new double[]{random.nextDouble(0.000,50.000),random.nextDouble(-100.000,-0.001)},
                new double[]{random.nextDouble(0.000,50.000),random.nextDouble(-100.000,-0.001)},
                new double[]{random.nextDouble(0.000,50.000),random.nextDouble(-100.000,-0.001)}
        );
    }

    public static <T> void processPoint(T t1, T t2, BiConsumer<T,T> consumer) {
        consumer.accept(t1, t2);
    }
}
