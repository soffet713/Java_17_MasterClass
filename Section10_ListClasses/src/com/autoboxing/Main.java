package com.autoboxing;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Integer boxedInt = Integer.valueOf(15);     //preferred but unnecessary
        Integer deprecatedBoxing = new Integer(15);     //deprecated since JDK 9

        int unboxedInt = boxedInt.intValue();       //unnecessary

        // Automatic
        Integer autoBoxed = 15;
        int autoUnboxed = autoBoxed;
        System.out.println(autoBoxed.getClass().getName());
        System.out.println(autoUnboxed);

        //System.out.println(getDoubleObject());
        //System.out.println(getLiteralDoublePrimitive());

        double resultUnboxed = getDoubleObject();
        Double resultBoxed = getLiteralDoublePrimitive();

        System.out.println(resultUnboxed);
        System.out.println(resultBoxed);

        Integer[] wrapperArray = new Integer[5];
        wrapperArray[0] = 50;
        System.out.println(Arrays.toString(wrapperArray));

        System.out.println(wrapperArray[0].getClass().getName());

        Character[] characterArray = {'a','b','c','d','e'};
        System.out.println(Arrays.toString(characterArray));

        var ourList = getList(1,2,3,4,5,6,7);
        System.out.println(ourList);
    }

    private static ArrayList<Integer> getList(Integer... varargs) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i : varargs) {
            list.add(i);
        }
        return list;
    }

    private static int returnAnInt(Integer i) {
        return i;
    }

    private static Integer returnAnInteger(int i) {
        return i;
    }

    private static Double getDoubleObject() {

        return Double.valueOf(100.00);
    }

    private static double getLiteralDoublePrimitive() {
        return 100.00;
    }
}
