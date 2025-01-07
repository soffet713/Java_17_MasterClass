package com.randomizationdicegame.randomchallenge;

import com.randomizationdicegame.dice.DiceGame;
import com.randomizationdicegame.game.GameConsole;

public class CourseMainGame {

    public static void main(String[] args) {

//        List<Integer> currentDice = new ArrayList<> (
//                List.of(2,2,4,4,4));
//        for(ScoredItem s : ScoredItem.values()) {
//            System.out.printf("Score for %s is %d %n", s, s.score(currentDice));
//        }

        var console = new GameConsole<>(new DiceGame("Dice Rolling Game"));
        console.playGame(console.addPlayer());
    }
}