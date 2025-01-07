package com.datetimelocalizationchallenge;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.zone.ZoneRules;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.time.format.DateTimeFormatter.*;

public class Main {

    public static void main(String[] args) {

        Employee ryan = new Employee("Ryan", Locale.US, "America/New_York");
        Employee hugh = new Employee("Hugh", "en-AU", "Australia/Sydney");

        ZoneRules hughRules = hugh.zone().getRules();
        ZoneRules ryanRules = ryan.zone().getRules();
        System.out.println(ryan + " | " + ryanRules);
        System.out.println(hugh + " | " + hughRules);

        ZonedDateTime ryanNow = ZonedDateTime.now(ryan.zone());
        ZonedDateTime hughNow = ZonedDateTime.of(ryanNow.toLocalDateTime(), hugh.zone());
        long hoursBetween = Duration.between(hughNow, ryanNow).toHours();
        long minutesBetween = Duration.between(hughNow, ryanNow).toMinutes();
        System.out.println("Hugh is " + Math.abs(hoursBetween) + " hours " + Math.abs(minutesBetween) + " minutes " +
                ((hoursBetween < 0) ? "behind" : "ahead") + " of Ryan.");

        System.out.println("-".repeat(75));
        System.out.println("Hugh in Daylight Savings? " + hughRules.isDaylightSavings(hughNow.toInstant()) + " " +
                hughRules.getDaylightSavings(hughNow.toInstant()) + ": " + hughNow.format(ofPattern("zzzz z")));

        System.out.println("Ryan in Daylight Savings? " + ryanRules.isDaylightSavings(ryanNow.toInstant()) + " " +
                ryanRules.getDaylightSavings(ryanNow.toInstant()) + ": " + ryanNow.format(ofPattern("zzzz z")));

        int days = 10;
        var map = schedule(hugh, ryan, days);
        DateTimeFormatter dtf = ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.SHORT);

        for(LocalDate ldt : map.keySet()) {
            System.out.println(ldt.format(ofLocalizedDate(FormatStyle.FULL)));
            for(ZonedDateTime zdt : map.get(ldt))
            {
                System.out.println("\t" + ryan.getDateInfo(zdt, dtf) + " <---> " +
                        hugh.getDateInfo(zdt.withZoneSameInstant(hugh.zone()), dtf));
            }
        }
    }

    private static Map<LocalDate, List<ZonedDateTime>> schedule(Employee first, Employee second, int days) {
        Predicate<ZonedDateTime> rules = zdt -> zdt.getDayOfWeek() != DayOfWeek.SATURDAY
                && zdt.getDayOfWeek() != DayOfWeek.SUNDAY && zdt.getHour() >= 7 && zdt.getHour() < 21;

        LocalDate startingDate = LocalDate.now().plusDays(2);

        return startingDate.datesUntil(startingDate.plusDays(days+1))
                .map(dt -> dt.atStartOfDay(first.zone()))
                .flatMap(dt -> IntStream.range(0, 24).mapToObj(dt::withHour))
                .filter(rules)
                .map(dtz -> dtz.withZoneSameInstant(second.zone()))
                .filter(rules)
                .collect(Collectors.groupingBy(ZonedDateTime::toLocalDate, TreeMap::new, Collectors.toList()));
    }
}
