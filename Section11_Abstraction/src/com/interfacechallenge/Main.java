package com.interfacechallenge;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Mappable> mappables = new ArrayList<>();

        mappables.add(new Building("Consulate-General of Japan", UsageType.GOVERNMENT));
        mappables.add(new Building("House of Blues Chicago", UsageType.ENTERTAINMENT));
        mappables.add(new Building("Soldier Field", UsageType.SPORTS));
        mappables.add(new Building("The Art Institute of Chicago", UsageType.EDUCATIONAL));
        mappables.add(new Building("Northwestern Memorial Hospital", UsageType.MEDICAL));

        mappables.add(new UtilityLine("College St", UtilityType.FIBER_OPTIC));
        mappables.add(new UtilityLine("Olympic Blvd", UtilityType.WATER));

        for (var m : mappables) {
            Mappable.mapIt(m);
        }
    }
}
