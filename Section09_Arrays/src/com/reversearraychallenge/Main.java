package com.reversearraychallenge;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int[] intArray = new int[]{1,3,4,52,34,2,57};

        System.out.println(Arrays.toString(intArray));

        int[] copyReverse = reverseCopy(intArray);

        System.out.println(Arrays.toString(copyReverse));

        reverse(intArray);

        System.out.println(Arrays.toString(intArray));
    }

    private static void reverse(int[] array) {
        int temp;
        for(int i=1; i<=array.length/2; i++) {
            temp = array[i-1];
            array[i-1] = array[array.length-i];
            array[array.length-i] = temp;
        }

        //Solution from course
        /*
        int maxIndex = array.length - 1;
        int halfLength = array.length / 2;

        for(int i=0; i<halfLength; i++) {
            int temp = array[i];
            array[i] = array[maxIndex-i];
            array[maxIndex-i] = temp;
            System.out.println("--> " + Arrays.toString(array));
        }
         */
    }

    private static int[] reverseCopy (int[] array) {
        int[] reversedArray = new int[array.length];
        int maxIndex = array.length-1;
        for(int value : array) {
            reversedArray[maxIndex--] = value;
        }

        return reversedArray;
    }
}
