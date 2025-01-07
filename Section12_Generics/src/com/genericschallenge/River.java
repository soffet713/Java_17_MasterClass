package com.genericschallenge;

enum Measurements {KILOMETERS,MILES;}

public class River extends Line {

    private String country;
    private double length;
    private String measurementType;

    public River(String name, String country, double length, String measurementType, String... locations) {
        super(name,locations);
        this.country = country;
        this.length = length;
        this.measurementType = measurementType;
    }

    public String getMeasurementType() {
        return switch (measurementType) {
            case "km" -> Measurements.KILOMETERS.toString();
            case "mi" -> Measurements.MILES.toString();
            default -> "long";
        };
    }

    @Override
    public String toString() {
        return super.name() + " River - " + length + " " + getMeasurementType() + " | Location: " + country;
    }
}
