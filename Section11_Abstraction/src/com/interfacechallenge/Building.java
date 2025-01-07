package com.interfacechallenge;

enum UsageType { EDUCATIONAL, ENTERTAINMENT, GOVERNMENT, MEDICAL, RESIDENTIAL, SPORTS }

public class Building implements Mappable{

    private String name;
    private UsageType usage;

    public Building(String name, UsageType usage) {
        this.name = name;
        this.usage = usage;
    }

    @Override
    public String getLabel() {
        return name + " (" + usage + ")";
    }

    @Override
    public GeometryType getGeometryType() {
        return GeometryType.POINT;
    }

    @Override
    public String getIconType() {
        return switch (usage) {
            case EDUCATIONAL -> Color.PURPLE + " " + PointMarker.DIAMOND;
            case ENTERTAINMENT -> Color.GREEN + " " + PointMarker.TRIANGLE;
            case GOVERNMENT -> Color.YELLOW + " " + PointMarker.STAR;
            case MEDICAL -> Color.RED + " " + PointMarker.CROSS;
            case RESIDENTIAL -> Color.BLUE + " " + PointMarker.SQUARE;
            case SPORTS -> Color.ORANGE + " " + PointMarker.PUSH_PIN;
            default -> Color.BLACK + " " + PointMarker.CIRCLE;
        };
    }

    @Override
    public String toJSON() {
        return Mappable.super.toJSON() + """
                , "name": "%s", "usage": "%s" """.formatted(name, usage);
    }
}
