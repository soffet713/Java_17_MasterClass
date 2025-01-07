package com.genericschallenge;

enum AreaMeasurements {ACRES,KILOMETERS_SQUARED,SQUARE_MILES;}

public class Park extends Point {

    private String country;
    private String yearBuilt;
    private String size;
    private String areaMeasurement;

    public Park(String name, String type, String location,
                String country, String yearEstablished, String size, String areaMeasurement) {
        super(name, type, location);
        this.country = country;
        this.yearBuilt = yearEstablished;
        this.size = size;
        this.areaMeasurement = areaMeasurement;
    }

    public String getAreaMeasurement() {
        return areaMeasurement;
    }

    public String getSize(String areaMeasurement) {
        return size + switch (areaMeasurement) {
            case "mi^2" -> AreaMeasurements.SQUARE_MILES;
            case "km^2" -> AreaMeasurements.KILOMETERS_SQUARED;
            case "acres" -> AreaMeasurements.ACRES;
            default -> "square space";
        };
    }

    @Override
    public String toString() {
        return super.name() + " | Type: " + super.type() + " | Area: " + getSize(areaMeasurement);
    }
}
