package com.interfacecodingexercise;

import java.util.ArrayList;
import java.util.List;

public class Monster implements ISaveable {

    private String name;
    private int hitPoints;
    private int strength;

    public Monster(String name, int hitPoints, int strength) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getStrength() {
        return strength;
    }

    @Override
    public List<String> write() {
        String name = getName();
        String hitPoints = "" + getHitPoints();
        String strength = "" + getStrength();
        List<String> fields = new ArrayList<String>();
        fields.add(name);
        fields.add(hitPoints);
        fields.add(strength);
        return fields;
    }

    @Override
    public void read(List<String> fieldList) {
        if(fieldList==null) {
            return;
        }
        if(fieldList.isEmpty()) { return; }
        name = fieldList.get(0);
        hitPoints = Integer.parseInt(fieldList.get(1));
        strength = Integer.parseInt(fieldList.get(2));
    }

    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", hitPoints=" + hitPoints +
                ", strength=" + strength +
                '}';
    }
}
