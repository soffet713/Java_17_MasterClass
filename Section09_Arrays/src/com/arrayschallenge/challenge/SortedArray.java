package com.arrayschallenge.challenge;

import java.util.Arrays;
import java.util.Scanner;

public class SortedArray {

    public static void main(String[] args) {

        int[] testArray = getIntegers(10);

        printArray(testArray);

        int[] sortedArray = sortIntegers(testArray);

        printArray(sortedArray);
    }

    public static int[] getIntegers(int len) {
        Scanner sc = new Scanner(System.in);
        int[] intArray = new int[len];
        System.out.println("Enter " + len + " numbers");

        for(int i=0; i<intArray.length; i++) {
            System.out.println("Please enter number " + (i + 1) + ":");
            try {
                intArray[i] = sc.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("That is not a number. Please enter a number a number:");
                i--;
            }
        }
        return intArray;
    }

    public static void printArray(int[] array) {
        for(int i = 0; i<array.length; i++) {
            System.out.println("Element " + i + " contents " + array[i]);
        }
    }

    public static int[] sortIntegers(int[] array) {

        int[] sortedArray = Arrays.copyOf(array, array.length);
        boolean flag = true;
        int temp;
        while(flag) {
            flag = false;
            for(int i = 0; i < sortedArray.length-1; i++) {
                if(sortedArray[i] < sortedArray[i+1]) {
                    temp = sortedArray[i];
                    sortedArray[i] = sortedArray[i+1];
                    sortedArray[i+1] = temp;
                    flag = true;
                }
            }
        }
        return sortedArray;
    }
}
