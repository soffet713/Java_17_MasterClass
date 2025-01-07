package com.enumproject;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        DayOfTheWeek weekDay = DayOfTheWeek.TUES;
        System.out.println(weekDay);

        System.out.printf("Name is %s, Ordinal Value = %d%n", weekDay.name(), weekDay.ordinal());

        for(int i=0; i<10; i++) {
            weekDay = getRandomDay();

            System.out.printf("Random Name is %s, Ordinal Value = %d%n", weekDay.name(), weekDay.ordinal());
            System.out.println("-".repeat(45));
            switchDayOfWeek(weekDay);
            System.out.println("-".repeat(20));

            if(weekDay==DayOfTheWeek.FRI) {
                System.out.println("        Time to celebrate, it's FRIYAY!!! \\(^o^)/");
                System.out.println("-".repeat(50));
            }
            if(weekDay==DayOfTheWeek.WED) {
                System.out.println("        Can someone say it's HUMP DAY?!?!");
                System.out.println("-".repeat(50));
            }
            if(weekDay==DayOfTheWeek.MON) {
                System.out.println("        Give it up for Victory Monday! (^人^)");
                System.out.println("-".repeat(50));
            }
        }

        for(Topping topping : Topping.values()) {
            System.out.println(topping.name() + " : $" + topping.getPrice());
        }
    }

    public static void switchDayOfWeek(DayOfTheWeek weekDay) {

        int weekDayInteger = weekDay.ordinal() + 1;
        switch(weekDay) {
            case WED -> System.out.println("Wednesday is Day " + weekDayInteger);
            case SAT -> System.out.println("Saturday is Day " + weekDayInteger);
            default -> System.out.println(weekDay.name().charAt(0) +
                    weekDay.name().substring(1).toLowerCase() + "day is Day " + weekDayInteger);
        }
    }

    public static DayOfTheWeek getRandomDay() {

        int randomInteger = new Random().nextInt(7);
        var allDays = DayOfTheWeek.values();

        return allDays[randomInteger];
    }
}
