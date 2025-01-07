package com.pirategamechallenge.shooter;

import com.pirategamechallenge.game.Player;

public record Shooter(String name) implements Player {

    boolean dash(String location) {
        System.out.println("Dashed forward to " + location);
        return false;
    }

    boolean findTreasure() {
        System.out.println("Prize found, score should be adjusted.");
        return false;
    }

    boolean throwGrenade() {
        System.out.println("Throwing Grenade!!!");
        return false;
    }

    boolean fireWeapon(String weapon) {
        System.out.println("You fired your " + weapon);
        return false;
    }

    boolean jump(String obstacle) {
        System.out.println("Jumping over " + obstacle);
        return false;
    }
}
