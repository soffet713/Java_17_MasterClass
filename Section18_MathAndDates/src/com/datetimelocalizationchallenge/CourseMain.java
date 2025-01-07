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

public class CourseMain {

    private record CourseEmployee(String name, Locale locale, ZoneId zone) {

        public CourseEmployee(String name, String locale, String zone) {
            this(name, Locale.forLanguageTag(locale), ZoneId.of(zone));
        }

        public CourseEmployee(String name, Locale locale, String zone) {
            this(name, locale, ZoneId.of(zone));
        }

        String getDateInfo(ZonedDateTime date, DateTimeFormatter dtf) {
            return "%s [%s] : %s".formatted(name, zone, date.format(dtf.localizedBy(locale)));
        }
    }

    public static void main(String[] args) {

        CourseEmployee jane = new CourseEmployee("Jane", Locale.US, "America/New_York");
        CourseEmployee joe = new CourseEmployee("Joe", "en-AU", "Australia/Sydney");

        ZoneRules janesRules = jane.zone.getRules();
        ZoneRules joesRules = joe.zone.getRules();
        System.out.println(jane + " | " + janesRules);
        System.out.println(joe + " | " + joesRules);

        ZonedDateTime janeNow = ZonedDateTime.now(jane.zone);
        ZonedDateTime joeNow = ZonedDateTime.of(janeNow.toLocalDateTime(), joe.zone());
        long hoursBetween = Duration.between(janeNow, joeNow).toHours();
        long minutesBetween = Duration.between(janeNow, joeNow).toMinutes();
        System.out.println("Jane is " + Math.abs(hoursBetween) + " hours " + Math.abs(minutesBetween) + " minutes " +
                ((hoursBetween < 0) ? "behind" : "ahead of") + " Joe.");

        System.out.println("Is Joe in Daylight Savings? -> " + joesRules.isDaylightSavings(joeNow.toInstant())
                + " " + joesRules.getDaylightSavings(joeNow.toInstant()) + ": " +
                joeNow.format(ofPattern("zzzz z")));

        System.out.println("Is Jane in Daylight Savings? -> " + janesRules.isDaylightSavings(janeNow.toInstant())
                + " " + janesRules.getDaylightSavings(janeNow.toInstant()) + ": " +
                janeNow.format(ofPattern("zzzz z")));

        int days = 10;
        var map = schedule(jane, joe, days);
        DateTimeFormatter dtf = ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.SHORT);

        for (LocalDate ldt : map.keySet()) {
            System.out.println(ldt.format(ofLocalizedDate(FormatStyle.FULL)));
            for (ZonedDateTime zdt : map.get(ldt)) {
                System.out.println("\t" + jane.getDateInfo(zdt, dtf) + " <---> " +
                        joe.getDateInfo(zdt.withZoneSameInstant(joe.zone()), dtf));
            }
        }
    }

    private static Map<LocalDate, List<ZonedDateTime>> schedule(CourseEmployee first, CourseEmployee second, int days) {

        Predicate<ZonedDateTime> rules = zdt ->
                zdt.getDayOfWeek() != DayOfWeek.SATURDAY
                && zdt.getDayOfWeek() != DayOfWeek.SUNDAY
                && zdt.getHour() >= 7 && zdt.getHour() < 21;

        LocalDate startingDate = LocalDate.now().plusDays(2);

        return startingDate.datesUntil(startingDate.plusDays(days + 1))
                .map(dt -> dt.atStartOfDay(first.zone()))
                .flatMap(dt -> IntStream.range(0, 24).mapToObj(dt::withHour))
                .filter(rules)
                .map(dtz -> dtz.withZoneSameInstant(second.zone()))
                .filter(rules)
                .collect(Collectors.groupingBy(ZonedDateTime::toLocalDate,
                        TreeMap::new, Collectors.toList()));
    }
}
