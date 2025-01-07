package com.arrayreferences;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        //myIntArray is a reference, or in other words, a reference to the array of ints
        //Arrays are reference types - this means it references an address to the object in memory,
        //but not the object itself. Stated differently, the variable myIntArray holds a reference or an address
        //of an int array in memory. And we use the reference to access and control the object in memory.
        int[] myIntArray = new int[5];
        int[] anotherArray = myIntArray;

        System.out.println("myIntArray = " + Arrays.toString(myIntArray));
        System.out.println("anotherArray = " + Arrays.toString(anotherArray));

        anotherArray[0] = 1;
        modifyArray(myIntArray);

        System.out.println("after change, myIntArray = " + Arrays.toString(myIntArray));
        System.out.println("after change, anotherArray = " + Arrays.toString(anotherArray));
    }

    private static void modifyArray(int[] array) {

        //array[1] = 2;
        for(int i=1; i<array.length; i++) {
            array[i] = i+1;
        }
    }
}
