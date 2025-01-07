package com.arraysproject;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random myRandom = new Random();
        int[] myIntArray = new int[10];
        for(int i=0; i<myIntArray.length; i++) {
            myIntArray[i] = myRandom.nextInt(100);
        }
        for(int i : myIntArray) {
            System.out.print(i + " // ");
        }

        System.out.println("\n");

        double[] myDoubleArray = new double[10];
        for(int d=0; d<myDoubleArray.length; d++) {
            myDoubleArray[d] = myRandom.nextDouble(50.0);
        }

        DecimalFormat df = new DecimalFormat("0.00");

        for(double dub : myDoubleArray) {
            System.out.print(df.format(dub) + " // ");
        }

        System.out.println("\n");

        int[] firstTen = {1,2,3,4,5,6,7,8,9,10};
        System.out.println("first = " + firstTen[0]);
        int arrayLength = firstTen.length;
        System.out.println("length of array = " + arrayLength);

        System.out.println("\n");

        int[] newArray;
        newArray = new int[5];
        for(int i=0; i<newArray.length; i++) {
            newArray[i] = newArray.length - i;
            System.out.print(newArray[i] + " // ");
        }

        System.out.println("\n");

        System.out.println(Arrays.toString(newArray));
        Object objectVariable = newArray; //arrays can be assigned to an Object type variable
        if(objectVariable instanceof int[]) {
            System.out.println("objectVariable is really an array of type int.");
        }

        System.out.println("\n");

        Object[] objectArray = new Object[3];
        objectArray[0] = "Hello";
        objectArray[1] = new StringBuilder("World");
        objectArray[2] = newArray;

        for(Object obj : objectArray) {
            if(obj instanceof int[]) {
                System.out.print(Arrays.toString((int[])obj) + " // ");
            } else {
                System.out.print(obj + " // ");
            }
        }
    }
}
