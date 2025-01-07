package com.minelementchallenge;

import java.util.Arrays;
import java.util.Scanner;

public class MinimumElementChallenge {

    public static void main(String[] args) {

    }

    private static int readInteger() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    private static int[] readElements(int num) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter " + num + " numbers, one at a time:");
        int[] intArray = new int[num];

        for(int i=0; i<num; i++) {
            intArray[i] = sc.nextInt();
        }

        return intArray;
    }

    private static int findMin(int[] array) {
        Arrays.sort(array);
        return array[0];
    }
}
