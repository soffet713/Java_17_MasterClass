package com.interfacecodingexercise;

import java.util.ArrayList;
import java.util.List;

public class Player implements ISaveable {

    private String name;
    private String weapon;
    private int hitPoints;
    private int strength;

    public Player(String name, int hitPoints, int strength) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.strength = strength;
        this.weapon = "Sword";
    }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public void setHitPoints(int hitPoints) { this.hitPoints = hitPoints; }
    public int getHitPoints() { return hitPoints; }
    public void setStrength(int strength) { this.strength = strength; }
    public int getStrength() { return strength; }
    public void setWeapon(String weapon) { this.weapon = weapon; }
    public String getWeapon() { return weapon; }

    @Override
    public List<String> write() {
        String name = getName();
        String hitPoints = "" + getHitPoints();
        String strength = "" + getStrength();
        String weapon = getWeapon();
        List<String> fields = new ArrayList<String>();
        fields.add(name);
        fields.add(hitPoints);
        fields.add(strength);
        fields.add(weapon);
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
        weapon = fieldList.get(3);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", hitPoints=" + hitPoints +
                ", strength=" + strength +
                ", weapon='" + weapon + '\'' +
                '}';
    }
}
