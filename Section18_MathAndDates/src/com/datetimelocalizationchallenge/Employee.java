package com.datetimelocalizationchallenge;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public record Employee(String name, Locale locale, ZoneId zone) {

    public Employee(String name, String locale, String zone) {
        this(name, Locale.forLanguageTag(locale), ZoneId.of(zone));
    }

    public Employee(String name, Locale locale, String zone) {
        this(name, locale, ZoneId.of(zone));
    }

    @Override
    public ZoneId zone() {
        return zone;
    }

    String getDateInfo(ZonedDateTime zdt, DateTimeFormatter dtf) {
        return "%s [%s] : %s".formatted(name, zone, zdt.format(dtf.localizedBy(locale)));
    }
}
