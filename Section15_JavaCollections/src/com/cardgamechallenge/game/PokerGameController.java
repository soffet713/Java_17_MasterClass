package com.cardgamechallenge.game;

import com.cardgamechallenge.game.poker.PokerGame;

public class PokerGameController {

    public static void main(String[] args) {

        PokerGame fiveCardDraw = new PokerGame(4,5);
        fiveCardDraw.startPlay();
    }
}
