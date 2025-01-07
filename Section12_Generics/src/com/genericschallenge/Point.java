package com.genericschallenge;

import java.util.Arrays;

public abstract class Point implements Mappable {

    private double[] location = new double[2];
    private String name;
    private String type;

    public Point(String name, String type, String location) {
        this.location = Mappable.stringToXYCoordinates(location);
        this.name = name;
        this.type = type;
    }

    @Override
    public String name() {
        return name;
    }

    public String type() {
        return type;
    }

    @Override
    public void render() {
        System.out.println("Render " + this + " as POINT (" + location() + ")");
    }

    private String location() {
        return Arrays.toString(location);
    }
}
