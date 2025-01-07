package com.pirategamechallenge.game;

import java.util.Map;
import java.util.Scanner;

public class GameConsole<T extends Game>{

    static Scanner sc = new Scanner(System.in);
    private final Game game;
    static String separator = "-".repeat(75);

    public GameConsole(Game game) {
        this.game = game;
    }

    public int addPlayer() {
        System.out.print("Enter your player name: ");
        String name = sc.nextLine();

        System.out.printf("Welcome to %s, %s!%n%n", game.getGameName(), name);
        return game.addPlayer(name);
    }

    public void playGame(int playerIndex) {
        boolean done = false;
        while(!done) {
            Map<Character, GameAction> gameActions = game.getGameActions(playerIndex);
            System.out.println("Select from one of the following Actions:");
            for(Character c : gameActions.keySet()) {
                String prompt = gameActions.get(c).prompt();
                System.out.println("\t" + prompt + " (" + c + ")");
            }
            System.out.println("Enter Next Action:");

            char nextMove = sc.nextLine().toUpperCase().charAt(0);
            GameAction gameAction = gameActions.get(nextMove);

            if(gameAction != null) {
                System.out.println(separator);
                done = game.executeGameAction(playerIndex, gameAction);
                if(!done) {
                    System.out.println(separator);
                }
            }
        }
    }
}
