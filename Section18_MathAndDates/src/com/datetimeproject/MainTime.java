package com.datetimeproject;

import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class MainTime {

    public static void main(String[] args) {

        LocalTime time = LocalTime.now();
        System.out.println(time);

        LocalTime sevenAM = LocalTime.of(7, 0);
        System.out.println(sevenAM);

        LocalTime sevenThirty = LocalTime.of(7, 30, 15);
        System.out.println(sevenThirty);
        System.out.println(sevenAM.get(ChronoField.AMPM_OF_DAY));
        System.out.println(sevenThirty.get(ChronoField.AMPM_OF_DAY));
        System.out.println(sevenAM.get(ChronoField.HOUR_OF_AMPM));
        System.out.println(sevenThirty.get(ChronoField.HOUR_OF_AMPM));

        System.out.println("-".repeat(45));

        LocalTime sevenPM = LocalTime.parse("19:00");
        LocalTime sevenThirtyPM = LocalTime.parse("19:30:15.1000");
        System.out.println(sevenPM);
        System.out.println(sevenThirtyPM);
        System.out.println(sevenPM.get(ChronoField.AMPM_OF_DAY));
        System.out.println(sevenThirtyPM.get(ChronoField.AMPM_OF_DAY));
        System.out.println(sevenPM.get(ChronoField.HOUR_OF_AMPM));
        System.out.println(sevenThirtyPM.get(ChronoField.HOUR_OF_AMPM));

        System.out.println(sevenThirtyPM.plus(24, ChronoUnit.HOURS));

        System.out.println(sevenPM.range(ChronoField.HOUR_OF_DAY));
        System.out.println(sevenPM.range(ChronoField.MINUTE_OF_HOUR));
        System.out.println(sevenPM.range(ChronoField.MINUTE_OF_DAY));
        System.out.println(sevenPM.range(ChronoField.SECOND_OF_MINUTE));
        System.out.println(sevenPM.range(ChronoField.SECOND_OF_DAY));
    }
}
