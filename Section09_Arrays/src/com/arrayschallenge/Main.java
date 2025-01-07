package com.arrayschallenge;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Integer[] myIntArray = getRandomIntegerArray(15);

        System.out.println(Arrays.toString(myIntArray));

        Arrays.sort(myIntArray, Collections.reverseOrder());

        System.out.println(Arrays.toString(myIntArray));
        System.out.print("\n");
        System.out.println("-".repeat(75));
        System.out.print("\n");
        int[] secondIntArray = getRandomIntArray(10);

        System.out.println(Arrays.toString(secondIntArray));

        int[] thirdArray = sortIntegers(secondIntArray);

        System.out.println(Arrays.toString(thirdArray));

    }

    public static int[] getRandomIntArray(int len) {
        Random myRandom = new Random();
        int[] ints = new int[len];

        for(int i=0; i<len; i++) {
            ints[i] = myRandom.nextInt(50);
        }

        return ints;
    }

    public static Integer[] getRandomIntegerArray(int len) {
        Random myRandom = new Random();
        Integer[] integers = new Integer[len];

        for(int i=0; i<len; i++) {
            integers[i] = myRandom.nextInt(50);
        }

        return integers;
    }

    public static int[] sortIntegers(int[] array) {

        int[] sortedArray = Arrays.copyOf(array, array.length);
        boolean flag = true;
        int temp;
        while(flag) {
            flag = false;
            for(int i=0;i<sortedArray.length-1;i++) {
                if(sortedArray[i]<sortedArray[i+1]) {
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
