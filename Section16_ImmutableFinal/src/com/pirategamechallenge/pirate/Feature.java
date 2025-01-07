package com.pirategamechallenge.pirate;

public enum Feature {

    ALOE(5),
    PINEAPPLE(10),
    VITAMIN_D(15),
    SPRING(25),
    BERRIES(30),
    MEAT(35),
    POTION(45),
    HORNETS(-5),
    JELLYFISH(-10),
    SUN_POISON(-15),
    SNAKE(-25),
    ALLIGATOR(-45);

    private final int healthPoints;

    Feature(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }
}
