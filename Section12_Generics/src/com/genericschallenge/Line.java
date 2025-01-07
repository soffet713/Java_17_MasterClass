package com.genericschallenge;

import java.util.Arrays;

public abstract class Line implements Mappable {

    private String name;
    private double[][] locations;

    public Line(String name, String... locations) {
        this.locations = new double[locations.length][];
        int i = 0;
        for(var location : locations) {
            this.locations[i++] = Mappable.stringToXYCoordinates(location);
        }
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void render() {
        System.out.println("Render " + this + " as POINT (" + locations() + ")");
    }

    private String locations() {
        return Arrays.deepToString(locations);
    }
}
