package com.cardgamechallenge.game.poker;

public enum Ranking {
    //order the values in the enum by ranking, so the least valued hand will have the lowest ordinal value
    NONE, ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, FULL_HOUSE, FOUR_OF_A_KIND;


    @Override
    public String toString() {
        return this.name().replace('_',' ');
    }
}
