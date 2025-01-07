package com.randomizationdicegame.randomchallenge;

import com.randomizationdicegame.dice.DiceGame;
import com.randomizationdicegame.game.GameConsole;

public class MainGame {

    public static void main(String[] args) {

        var console = new GameConsole<>(new DiceGame("Dice Rolling Game"));
        console.playGame(console.addPlayer());
    }
}
