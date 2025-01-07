package com.pirategamechallenge.pirate;

import java.util.*;

public final class Pirate extends Combatant {

    private final List<Town> townsVisited = new LinkedList<Town>();
    private List<Loot> loot;
    private List<Combatant> opponents;
    private List<Feature> features;
    //private Weapon currentWeapon;

    public Pirate(String name) {
        super(name, Map.of("level",0,"townIndex",0));
        visitTown();
    }

    /* Instance Initializer Example
    {
        gameData = new HashMap<>(Map.of(
                "health", 100,
                "score", 0,
                "level", 0,
                "townIndex", 0
        ));
        visitTown();
    }


    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    int value(String name) {
        return gameData.get(name);
    }

    private void setValue(String name, int value) {
        gameData.put(name, value);
    }

    private void adjustValue(String name, int adj) {
        gameData.compute(name, (k, v) -> v += adj);
    }

    private void adjustHealth(int adj) {
        int health = value("health");
        health += adj;
        health = (health < 0) ? 0 : (Math.min(health, 100));
        setValue("health", health);
    }
*/
    boolean useWeapon() {
        int count = opponents.size();
        if(count>0) {
            int opponentIndex = count - 1;
            if(count>1) {
                opponentIndex = new Random().nextInt(count);
            }
            Combatant combatant = opponents.get(opponentIndex);
            if(super.useWeapon(combatant)) {
                opponents.remove(opponentIndex);
            } else {
                return combatant.useWeapon(this);
            }
        }
        return false;
    }

    boolean visitTown() {
        List<Town> levelTowns = PirateGame.getTowns(value("level"));
        if(levelTowns==null) return true;
        Town town = levelTowns.get(value("townIndex"));
        if(town != null) {
            townsVisited.add(town);
            loot = town.loot();
            opponents = town.opponents();
            features = town.features();
            return false;
        }
        return true;
    }

    private boolean visitNextTown() {
        int townIndex = value("townIndex");
        List<Town> towns = PirateGame.getTowns(value("level"));
        if(towns==null) { return true; }
        if(townIndex >= (towns.size()-1)) {
            System.out.println("Leveling Up! Bonus: 500 points!");
            adjustValue("score", 500);
            adjustValue("level", 1);
            setValue("townIndex", 0);
        } else {
            System.out.println("Sailing to next town! Bonus: 50 points!");
            adjustValue("townIndex", 1);
            adjustValue("score", 50);
        }
        return visitTown();
    }

    boolean isExperienced() {
        return (features != null && !features.isEmpty());
    }

    boolean hasOpponents() {
        return (opponents != null && !opponents.isEmpty());
    }

    boolean findLoot() {
        if(!loot.isEmpty()) {
            Loot item = loot.remove(0);
            System.out.println("Found " + item + "!");
            adjustValue("score", item.getWorth());
            System.out.println(name() + "'s score is now: " + value("score"));
        } if (loot.isEmpty()) {
            return visitNextTown();
        }
        return false;
    }

    boolean getTownInfo() {
        if(!townsVisited.isEmpty()) {
            System.out.println(townsVisited.get(townsVisited.size()-1).information());
        }
        return false;
    }

    boolean featureInteraction() {
        if(!features.isEmpty()) {
            Feature item = features.remove(0);
            System.out.println("Ran into " + item + "!");
            adjustHealth(item.getHealthPoints());
            System.out.println(name() + "'s health is now " + value("health"));
        }
        return (value("health") <= 0);
    }

    public String information() {
        var current = ((LinkedList<Town>) townsVisited).getLast();
        String[] simpleNames = new String[townsVisited.size()];
        String weaponNote = (super.getCurrentWeapon()==null) ? "" : "%nCurrentWeapon = %s".formatted(super.getCurrentWeapon());
        Arrays.setAll(simpleNames, i -> townsVisited.get(i).name());
        return "---> " + current + "\nPirate " + super.information() + weaponNote + "\n\ttownsVisited = "
                + Arrays.toString(simpleNames);
    }
}
