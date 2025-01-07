package com.pirategamechallenge.coursegame;

import java.util.Scanner;

public class CourseGameConsole<T extends CourseGame<? extends CoursePlayer>> {

    private final T game;
    private static final Scanner scanner = new Scanner(System.in);
    static String separator = "-".repeat(75);

    public CourseGameConsole(T game) {
        this.game = game;
    }

    public int addPlayer() {
        System.out.println("Enter your player name: ");
        String name = scanner.nextLine();

        System.out.printf("Welcome to %s, %s!%n",game.getGameName(), name);
        return game.addPlayer(name);
    }

    public void playGame(int playerIndex) {
        boolean done = false;
        while(!done) {
            var gameActions = game.getGameActions(playerIndex);
            System.out.println("Select from one of the following Actions: ");
            for(Character c : gameActions.keySet()) {
                String prompt = gameActions.get(c).prompt();
                System.out.println("\t" + prompt + " (" + c + ")");
            }
            System.out.println("Enter Next Action: ");

            char nextMove = scanner.nextLine().toUpperCase().charAt(0);
            CourseGameAction gameAction = gameActions.get(nextMove);

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
