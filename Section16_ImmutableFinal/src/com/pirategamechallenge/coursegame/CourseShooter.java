package com.pirategamechallenge.coursegame;

public record CourseShooter(String name) implements CoursePlayer {

    boolean findPrize() {
        System.out.println("Prize found, score should be adjusted.");
        return false;
    }

    boolean useWeapon(String weapon) {
        System.out.println("You shot your " + weapon);
        return false;
    }
}
