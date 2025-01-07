package com.mathrandomproject;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        /*
        int maxMinusFive = Integer.MAX_VALUE - 5;
        for(int j=0, id=maxMinusFive; j<10; id = Math.incrementExact(id), j++) {
            System.out.printf("Assigning id %,d%n",id);
        }
         */
        String separator = "-".repeat(75);
        System.out.println(Math.abs(-50));
        System.out.println(Math.abs(Integer.MIN_VALUE));
        //System.out.println(Math.absExact(Integer.MIN_VALUE));
        System.out.println(Math.abs((long) Integer.MIN_VALUE));

        System.out.println("Max = " + Math.max(10, -10));
        System.out.println("Min = " + Math.min(10.0000002, 10.001f));

        System.out.println("Round 10.2 down = " + Math.round(10.2));
        System.out.println("Round 10.6 up = " + Math.round(10.6));
        System.out.println("Round 10.5 ? = " + Math.round(10.5));

        System.out.println("Round 10.8 down using floor = " + Math.floor(10.8));
        System.out.println("Round 10.2 up using ceiling = " + Math.ceil(10.2));

        System.out.println("Square root of 100 = " + Math.sqrt(100));
        System.out.println("Math.pow(n, p) is n to the p power - 2 to the 3rd power = " + Math.pow(2,3));
        System.out.println("10 to the 5th power (10*10*10*10*10) = " + Math.pow(10,5));
        System.out.println(separator);

        for(int i=0; i<10; i++) {
            System.out.print((int) Math.ceil(Math.random() * 6) + " ");
        }
        System.out.println();
        System.out.println(separator);

        for(int x=0; x<10; x++) {
            System.out.printf("%1$d = %1$c%n", (int) (Math.random() * 26) + 65);
        }
        System.out.println(separator);
        Random r = new Random();
        for(int x=0; x<10; x++) {
            System.out.printf("%1$d = %1$c%n", r.nextInt(65,91));
        }

        System.out.println(separator);
        for(int x=0; x<10; x++) {
            System.out.printf("%1$d = %1$c%n", r.nextInt((int) 'A',(int) 'Z' + 1));
        }

        System.out.println(separator);
        for(int x=0; x<10; x++) {
            System.out.printf("%1$d%n", r.nextInt(-10,11));
        }

        System.out.println(separator);
        r.ints()
                .limit(10)
                .forEach(System.out::println);

        System.out.println(separator);
        r.ints(0,10)
                .limit(10)
                .forEach(System.out::println);

        System.out.println(separator);
        r.ints(10, 0,10)
                .forEach(System.out::println);

        System.out.println(separator);
        r.ints(5)
                .forEach(System.out::println);

        long nanoTime = System.nanoTime();
        Random pseudoRandom = new Random(nanoTime);
        System.out.println(separator);
        pseudoRandom.ints(10, 0,10)
                .forEach(i -> System.out.print(i + " "));

        Random notReallyRandom = new Random(nanoTime);
        System.out.println("\n" + separator);
        notReallyRandom.ints(10, 0,10)
                .forEach(i -> System.out.print(i + " "));
    }
}
