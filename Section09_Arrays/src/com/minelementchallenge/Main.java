package com.minelementchallenge;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int[] newArray = readIntegers();

        System.out.println(Arrays.toString(newArray));

        int minValue = findMin(newArray);

        System.out.println("Minimum value in newArray = " + minValue);
    }

    private static int[] readIntegers() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input a set of numbers separated by commas:");
        String inputString = sc.nextLine();
        String[] splitArray = inputString.split(",");

        int[] intArray = new int[splitArray.length];

        for(int i=0; i<intArray.length; i++) {
            try {
                intArray[i] = Integer.parseInt(splitArray[i].trim());
            } catch (NumberFormatException e) {
                System.out.println("Values must all be integer values, separated by commas.");
                break;
            }
        }

        return intArray;
    }

    private static int findMin(int[] array) {
        Arrays.sort(array);
        System.out.println("-".repeat(75));
        System.out.println("Sorted array = " + Arrays.toString(array));

        //int min = Integer.MAX_VALUE;
        //for(int value : array) {
        //    if(value<min) { min = value; }
        //}

        //return min;
        return array[0];
    }
}
