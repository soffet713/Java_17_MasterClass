package com.adventuregamechallenge;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String myLocations = """
                lake,at the edge of Lake Inba-numa,N:cave,S:well house,E:ocean,W:forest
                ocean,on Inage beach before an angry sea,W:lake
                cave,Nōmizo Waterfall & Turtle-Rock Cave,N:forest,S:ocean,E:ocean,W:ocean
                """;

        AdventureGame game = new AdventureGame(myLocations);
        game.play("lake");

        Scanner scanner = new Scanner(System.in);

        while(true) {
            String direction = scanner.nextLine().trim().toUpperCase().substring(0,1);
            if(direction.equals("Q")) break;
            game.move(direction);
        }
    }
}
