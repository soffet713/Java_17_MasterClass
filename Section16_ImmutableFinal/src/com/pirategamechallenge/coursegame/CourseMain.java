package com.pirategamechallenge.coursegame;

public class CourseMain {

    public static void main(String[] args) {

        var console = new CourseGameConsole<>(new CourseShooterGame("The Shootout Game"));

        int playerIndex = console.addPlayer();
        console.playGame(playerIndex);
    }
}
