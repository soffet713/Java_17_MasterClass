package com.pirategamechallenge.pirate;

import java.util.*;

public record Town(String name, String island, int level, List<Loot> loot, List<Feature> features,
                   List<Combatant> opponents) {
    static String[] names = {"Joe","Sam","Jake","Tom","Marty","Scott","John","Adam","Chris","Shawn","Drake",
            "Caleb","Max","Martin","Kevin","Craig","Devin","Jordan","Tanner","Zack","Rich","Andrew","Alex","Tyler"};
    static List<Weapon> weapons = new ArrayList<>(List.of(Weapon.values()));
    static Random random = new Random();

    public Town {
        loot = randomReduced(new ArrayList<>(EnumSet.allOf(Loot.class)), level + 2);
        features = randomReduced(new ArrayList<>(EnumSet.allOf(Feature.class)), level + 3);
        opponents = new ArrayList<>();
        if(level == 0) {
            opponents.add(new Islander(names[random.nextInt(names.length-1)], Weapon.KNIFE));
        } else if (level == 1) {
            opponents.add(new Islander(names[random.nextInt(names.length-1)],
                    weapons.get(random.nextInt((weapons.size()/2)-1))));
            opponents.add(new Islander(names[random.nextInt(names.length-1)],
                    weapons.get(random.nextInt((weapons.size()/2)-1))));
        } else {
            opponents.add(new Islander(names[random.nextInt(names.length-1)],
                    weapons.get(random.nextInt(weapons.size()-1))));
            opponents.add(new Islander(names[random.nextInt(names.length-1)],
                    weapons.get(random.nextInt((weapons.size()/2)-1))));
            opponents.add(new Islander(names[random.nextInt(names.length-1)],
                    weapons.get(random.nextInt((weapons.size()/2)-1))));
        }
    }

    public Town(String name, String island, int level) {
        this(name, island, level, List.of(), List.of(), List.of());
    }

    private <T> List<T> randomReduced(List<T> list, int size) {
        Collections.shuffle(list);
        return list.subList(0, size);
    }

    @Override
    public String toString() {
        return name + ", " + island;
    }

    public String information() {
        return "Town: %s\n\tloot = %s\n\tremedies = %s\n\topponents = %s".formatted(this,loot,features,opponents);
    }

    public List<Loot> loot() {
        return (loot==null) ? null : new ArrayList<>(loot);
    }

    public List<Combatant> opponents() {
        return (opponents==null) ? null : new ArrayList<>(opponents);
    }

    public List<Feature> features() {
        return (features==null) ? null : new ArrayList<>(features);
    }
}
