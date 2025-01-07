package com.cardgamechallenge.game;

import com.cardgamechallenge.game.gofish.GoFishGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GoFishGameController {

    public static void main(String[] args) {

        GoFishGame sevenCardDraw = new GoFishGame(4, 7);
        sevenCardDraw.startPlay();
        List<Integer> players = new ArrayList<>(List.of(0,1,2,3));
        Collections.shuffle(players);
        Random random = new Random();

        boolean cardCountZero = false;

        while(!cardCountZero) {
            for(int p=0; p<players.size(); p++) {
                int currPlayer = players.indexOf(p);
                int opponent = currPlayer;
                while(opponent==currPlayer) {
                    opponent = random.nextInt(0,4);
                }
                sevenCardDraw.fish(currPlayer, opponent);
                int currPlayerHandCount = sevenCardDraw.getPlayerHandCardCount(currPlayer);
                int opponentHandCount = sevenCardDraw.getPlayerHandCardCount(opponent);
                System.out.println("-".repeat(25) + "Player " + p + " has a hand count of: " +
                        currPlayerHandCount);
                System.out.println("-".repeat(25) + "Player " + opponent + " has a hand count of: " +
                        opponentHandCount);
                if(currPlayerHandCount==0||opponentHandCount==0) {
                    cardCountZero=true;
                    break;
                }
            }
        }

        sevenCardDraw.getWinner();
    }
}
