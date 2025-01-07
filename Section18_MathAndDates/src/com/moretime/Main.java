package com.moretime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

public class Main {

    public static void main(String[] args) {

        System.setProperty("user.timezone", "America/Los_Angeles");
        System.out.println(ZoneId.systemDefault());

        System.out.println("Number of TimeZones = " + ZoneId.getAvailableZoneIds().size());

        ZoneId.getAvailableZoneIds().stream()
                .filter(s -> s.startsWith("US"))
                .sorted()
                .map(ZoneId::of)
                .forEach(z -> System.out.println(z.getId() + ": " + z.getRules()));


        Set<String> jdk8Zones = ZoneId.getAvailableZoneIds();
        String[] alternate = TimeZone.getAvailableIDs();
        Set<String> oldway = new HashSet<>(Set.of(alternate));

        //jdk8Zones.removeAll(oldway);
        //System.out.println(jdk8Zones);
        oldway.removeAll(jdk8Zones);
        System.out.println(oldway);

        ZoneId cat = ZoneId.of("CAT", ZoneId.SHORT_IDS);
        System.out.println(cat);

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

        Instant instantNow = Instant.now();
        System.out.println(instantNow);

        for(ZoneId z : List.of(
                ZoneId.of("Asia/Tokyo"),
                ZoneId.of("Europe/Dublin"),
                ZoneId.of("America/Chicago"))) {
            DateTimeFormatter zoneFormat = DateTimeFormatter.ofPattern("z:zzzz");
            System.out.println(z);
            System.out.println("\t" + instantNow.atZone(z).format(zoneFormat));
            System.out.println("\t" + z.getRules().getDaylightSavings(instantNow));
            System.out.println("\t" + z.getRules().isDaylightSavings(instantNow));
        }

        Instant dobInstant = Instant.parse("2020-01-01T08:01:00Z");
        LocalDateTime dob = LocalDateTime.ofInstant(dobInstant, ZoneId.systemDefault());
        System.out.println("Your kid's birthdate, LA Time = " +
                dob.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

        ZonedDateTime dobTokyo = ZonedDateTime.ofInstant(dobInstant, ZoneId.of("Asia/Tokyo"));
        System.out.println("Your kid's birthdate, Tokyo Time = " +
                dobTokyo.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

        ZonedDateTime dobHere = dobTokyo.withZoneSameInstant(ZoneId.systemDefault());
        System.out.println("Your kid's birthdate, Here Time = " +
                dobHere.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

        ZonedDateTime firstOfMonth = ZonedDateTime.now()
                .with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.printf("First of Next Month = %tD %n", firstOfMonth);

        Period timePast = Period.between(LocalDate.EPOCH, dob.toLocalDate());
        System.out.println(timePast);

        Duration timeSince = Duration.between(Instant.EPOCH, dob.toInstant(ZoneOffset.UTC));
        System.out.println(timeSince);

        LocalDateTime dob2 = dob.plusYears(2).plusMonths(4).plusDays(4).plusHours(7).plusMinutes(14)
                .plusSeconds(37);
        System.out.println("Your 2nd kid's birthdate, Here Time = " +
                dob2.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

        Period timePast2 = Period.between(LocalDate.EPOCH, dob2.toLocalDate());
        System.out.println(timePast2);

        Duration timeSince2 = Duration.between(Instant.EPOCH, dob2.toInstant(ZoneOffset.UTC));
        System.out.println(timeSince2);

        for(ChronoUnit u : ChronoUnit.values()) {
            if(u.isSupportedBy(LocalDate.EPOCH)) {
                long val = u.between(LocalDate.EPOCH, dob2.toLocalDate());
                System.out.println(u + " past = " + val);
            } else {
                System.out.println("-- Not supported: " + u);
            }
        }

        System.out.println("-".repeat(50));

        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC);

        for(ChronoUnit u : ChronoUnit.values()) {
            if(u.isSupportedBy(ldt)) {
                long val = u.between(ldt, dob2);
                System.out.println(u + " past = " + val);
            } else {
                System.out.println("-- Not supported: " + u);
            }
        }
    }
}
