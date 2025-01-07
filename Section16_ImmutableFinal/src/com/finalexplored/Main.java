package com.finalexplored;

import com.finalexplored.consumer.specific.ChildClass;
import com.finalexplored.external.util.Logger;
import com.finalexplored.generic.BaseClass;

public class Main {

    public static String separator = "-".repeat(75);

    public static void main(String[] args) {

        BaseClass parent = new BaseClass();
        ChildClass child = new ChildClass();
        BaseClass childReferredToAsBase = new ChildClass();

        parent.recommendedMethod();
        System.out.println(separator);
        child.recommendedMethod();
        System.out.println(separator);
        childReferredToAsBase.recommendedMethod();
        System.out.println(separator);

        parent.recommendedStatic();
        System.out.println(separator);
        child.recommendedStatic();
        System.out.println(separator);
        childReferredToAsBase.recommendedStatic();
        System.out.println(separator);

        System.out.println(separator);
        BaseClass.recommendedStatic();
        ChildClass.recommendedStatic();

        String xArgument = "This is all I've got to say about Section ";
        StringBuilder zArgument = new StringBuilder("Only saying this: Section ");
        doXYZ(xArgument, 16, zArgument);
        System.out.println("After the method, xArgument: " + xArgument);
        System.out.println("After the method, zArgument: " + zArgument);

        StringBuilder tracker = new StringBuilder("Step 1 is abc");
        Logger.logToConsole(tracker.toString());
        tracker.append(", Step 2 is xyz.");
        Logger.logToConsole(tracker.toString());
        System.out.println("After logging, tracker = " + tracker);
    }

    private static void doXYZ(String x, int y, StringBuilder z) {
        final String c = x + y;
        System.out.println("c = " + c);
        z.append(y);
    }
}
