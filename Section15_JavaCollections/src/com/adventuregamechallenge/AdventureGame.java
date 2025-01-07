package com.adventuregamechallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdventureGame {

    private static final String GAME_LOCATIONS = """
        road,at the end of the road,N:forest,S:valley,E:well house,W: hill
        hill,on top of hill with a view in all directions,N:forest, E:road
        well house,inside a well house for a small spring,W:road,N:lake,S:stream
        valley,in a forest valley beside a tumbling stream,N:road,E:stream,W:hill
        forest,at the edge of a thick dark forest,S:road,E:lake
        lake,by an alpine lake surrounded by wildflowers,S:well house,W:forest
        stream,near a stream with a rocky bed,N:well house,W:valley
        """;

    private enum Compass {N, S, E, W;

        private static final String[] directions = {"North", "South", "East", "West"};

        public String getString() {
            return directions[this.ordinal()];
        }
    }

    private record Location(String description, Map<Compass, String> nextPlaces) {}

    private String lastPlace;
    private Map<String, Location> adventureMap = new HashMap<>();

    public AdventureGame() {
        this(null);
    }

    public AdventureGame(String gameLocations) {
        loadLocations(GAME_LOCATIONS);
        if(gameLocations!=null) {
            loadLocations(gameLocations);
        }
    }

    private void loadLocations(String locationData) {
        for(String s : locationData.split("\\R")) {
            String[] dataParts = s.split(",",3);
            Arrays.asList(dataParts).replaceAll(String::trim);
            Map<Compass, String> nextPlaces = loadDirections(dataParts[2]);
            Location location = new Location(dataParts[1], nextPlaces);
            adventureMap.put(dataParts[0], location);
        }
    }

    private Map<Compass, String> loadDirections(String nextPlaces) {

        Map<Compass, String> directions = new HashMap<>();
        List<String> nextSteps = Arrays.asList(nextPlaces.split(","));

        nextSteps.replaceAll(String::trim);
        for(String nextPlace : nextSteps) {
            String[] placeData = nextPlace.split(":");
            Compass compass = ("NSEW".contains(placeData[0].trim())) ?
                    Compass.valueOf(placeData[0].trim()) : null;
            String destination = placeData[1].trim();
            directions.put(compass, destination);
        }
        return directions;
    }

    private void visit(Location location) {
        System.out.printf("*** You're Currently %s *** %n", location.description);
        System.out.println("\tFrom here, you can see:");

        location.nextPlaces.forEach((k,v) -> { System.out.printf("\t• A %s to the %s (%S) %n", v, k.getString(), k); });
        System.out.print("Select your next Compass Direction (or Q to Quit)");
    }

    public void move(String direction) {

        var nextPlaces = adventureMap.get(lastPlace).nextPlaces;
        String nextPlace = null;
        if("NSEW".contains(direction)) {
            nextPlace = nextPlaces.get(Compass.valueOf(direction));
            if(nextPlace!=null) { play(nextPlace); }
            else {
                System.out.println("!!! Invalid direction, please try again!!!");
            }
        }
    }

    public void play(String location) {
        if(adventureMap.containsKey(location)) {
            Location next = adventureMap.get(location);
            lastPlace = location;
            visit(next);
        } else {
            System.out.println(location + " is not a valid location.");
        }
    }
}
