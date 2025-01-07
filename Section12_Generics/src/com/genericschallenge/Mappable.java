package com.genericschallenge;

public interface Mappable {

    String name();

    static double[] stringToXYCoordinates(String location) {

        var splitLocation = location.split(",");
        double xCoordinate = Double.valueOf(splitLocation[0]);
        double yCoordinate = Double.valueOf(splitLocation[1]);
        return new double[]{xCoordinate,yCoordinate};
    }

    void render();
}
