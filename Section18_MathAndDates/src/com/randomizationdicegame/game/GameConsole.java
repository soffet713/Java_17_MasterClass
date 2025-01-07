package com.randomizationdicegame.game;

import java.util.Scanner;

public final class GameConsole<T extends Game<? extends Player>> {

    private final T game;
    private static final Scanner sc = new Scanner(System.in);

    public GameConsole(T game) {
        this.game = game;
    }

    public int addPlayer() {
        System.out.print("Please enter your name: ");
        String name = sc.nextLine();

        System.out.printf("Welcome to %s, %s!%n", game.getGameName(), name);
        return game.addPlayer(name);
    }

    public void playGame(int playerIndex) {
        boolean done = false;
        while(!done) {
            var gameActions = game.getGameActions(playerIndex);
            System.out.println("Select from one of the following Actions: ");
            for (Character c : gameActions.keySet()) {
                String prompt = gameActions.get(c).prompt();
                System.out.println("\t" + prompt + " (" + c + ")");
            }
            System.out.println("Enter next action: ");

            char nextMove = sc.nextLine().toUpperCase().charAt(0);
            GameAction gameAction = gameActions.get(nextMove);

            if(gameAction!=null) {
                System.out.println("-".repeat(75));
                done = game.executeGameAction(playerIndex,gameAction);
                if(!done){
                    System.out.println("-".repeat(75));
                }
            }
        }
    }

    public static String getUserInput(String prompt) {

        System.out.print(prompt + ":  ");
        return sc.nextLine();
    }
}
