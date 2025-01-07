package com.pirategamechallenge.coursegame;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public enum CourseWeapon {
    KNIFE(0, 10),
    AXE(0, 30),
    MACHETE(1, 40),
    PISTOL(1, 50);

    private final int minLevel;
    private final int hitPoints;

    CourseWeapon(int minLevel, int hitPoints) {
        this.minLevel = minLevel;
        this.hitPoints = hitPoints;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public static CourseWeapon getWeaponByChar(char firstInitial) {

        for (CourseWeapon w : values()) {
            if (w.name().charAt(0) == firstInitial) {
                return w;
            }
        }
        return values()[0];
    }

    public static List<CourseWeapon> getWeaponsByLevel(int levelOfPlay) {

        List<CourseWeapon> weapons = new ArrayList<>(EnumSet.allOf(CourseWeapon.class));
        weapons.removeIf(w -> (w.minLevel > levelOfPlay));
        return weapons;
    }
}
