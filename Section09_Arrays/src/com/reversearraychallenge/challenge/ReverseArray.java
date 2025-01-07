package com.reversearraychallenge.challenge;

import java.util.Arrays;

public class ReverseArray {

    private static void reverse(int[] array) {
        int temp;
        System.out.print("Array = " + Arrays.toString(array));
        for(int i=1; i<=array.length/2; i++) {
            temp = array[i-1];
            array[i-1] = array[array.length-i];
            array[array.length-i] = temp;
        }
        System.out.println("Reversed array = " + Arrays.toString(array));
    }
}
