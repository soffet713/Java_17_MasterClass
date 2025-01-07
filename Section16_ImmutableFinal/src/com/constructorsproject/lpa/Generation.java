package com.constructorsproject.lpa;

import java.time.LocalDate;

public enum Generation {
    ALPHA {
        {
            System.out.println("-- SPECIAL FOR " + this + " --");
        }
    },
    GEN_Z(1997,2012),
    MILLENNIAL(1981, 1996),
    GEN_X(1965,1980),
    BABY_BOOMER(1946,1964),
    SILENT_GENERATION(1928,1945),
    GREATEST_GENERATION(1901,1927);

    private final int startYear;
    private final int endYear;

    Generation() {
        this(2013, LocalDate.now().getYear());
    }

    Generation(int startYear, int endYear){
        this.startYear = startYear;
        this.endYear = endYear;
        System.out.println(this);
    }

    @Override
    public String toString() {
        return this.name() + " " + startYear + " - " + endYear;
    }
}
