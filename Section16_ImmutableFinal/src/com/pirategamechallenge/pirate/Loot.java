package com.pirategamechallenge.pirate;

public enum Loot {

    SILVER_COIN(5),
    GOLD_COIN(10),
    GOLD_RING(125),
    PEARL_NECKLACE(250),
    GOLD_BAR(500),
    BAG_OF_DIAMONDS(5000),
    CHEST_OF_GOLD(25000);

    private final int worth;

    Loot(int worth) {
        this.worth = worth;
    }

    public int getWorth() {
        return worth;
    }
}
