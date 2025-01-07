package com.interfacechallenge;

enum GeometryType { POINT, LINE, POLYGON }
enum PointMarker {CIRCLE, CROSS, DIAMOND, PUSH_PIN, STAR, SQUARE, TRIANGLE}
enum Color { BLACK,BLUE,RED,GREEN,ORANGE,PURPLE,YELLOW }
enum LineMarker { DASHED, DOTTED, SOLID }

public interface Mappable {

    String JSON_PROPERTY = """
            "properties":{%s}
            """;


    String getLabel();
    GeometryType getGeometryType();
    String getIconType();

    default String toJSON() {
        return String.format("""
                "type": "%s", "label": "%s", "marker": "%s"
                """, this.getLabel(), this.getGeometryType(), this.getIconType());
    }

    static void mapIt(Mappable mappable) {
        System.out.println(JSON_PROPERTY.formatted(mappable.toJSON()));
    }
}
