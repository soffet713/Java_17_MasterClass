package com.streamsourcechallenge;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        int seed = 1;
        var firstStream = Stream.iterate(1, n -> n + 1)
                .limit(15)
                .map(i -> (i<10) ? "B0" + i : "B" + i);

        seed += 15;
        var secondStream = Stream.iterate(seed, n -> n <= 30, n -> n + 1)
                .map(i -> "I" + i);

        seed += 15;
        var thirdStream = IntStream.range(seed,46)
                .mapToObj(i -> "N" + i);

        seed += 15;
        var fourthStream = IntStream.rangeClosed(seed, 60)
                .mapToObj(i -> "G" + i);

        seed += 15;
        int rSeed = seed;
        int newSeed = seed;
        String[] oLabels = new String[15];
        Arrays.setAll(oLabels, i -> "O" + (newSeed + i));
        var fifthStream = Arrays.stream(oLabels);

        var biStream = Stream.concat(firstStream,secondStream);
        var ngStream = Stream.concat(thirdStream,fourthStream);
        var bingStream = Stream.concat(biStream, ngStream);
        var bingoStream = Stream.concat(bingStream, fifthStream);

        bingoStream.forEach(System.out::println);

        System.out.println("-".repeat(75));
        Stream.generate(() -> new Random().nextInt(rSeed, rSeed + 15))
                .distinct()
                .limit(15)
                .map(i -> "O" + i)
                .sorted()
                .forEach(System.out::println);

        System.out.println("-".repeat(75));

        Map<Character, int[]> myMap = new LinkedHashMap<>();
        int bingoIndex = 1;
        for(char c : "BINGO".toCharArray()) {
            int[] numbers = new int[15];
            int labelNo = bingoIndex;
            Arrays.setAll(numbers, i -> i + labelNo);
            myMap.put(c, numbers);
            bingoIndex += 15;
        }
        myMap.entrySet()
                .stream()
                .map(e -> e.getKey() + "" + e.getValue()[0] + " - " + e.getKey() + e.getValue()[e.getValue().length-1])
                .forEach(System.out::println);
    }
}
