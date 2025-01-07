package com.pirategamechallenge;

import com.pirategamechallenge.game.GameConsole;
import com.pirategamechallenge.pirate.PirateGame;

public class Main {

    static String separator = "-".repeat(75);

    public static void main(String[] args) {
        //var console = new GameConsole<>(new ShooterGame("The Shootout Game"));

        //int playerIndex = console.addPlayer();
        //console.playGame(playerIndex);
        //Weapon weapon = Weapon.getWeaponByChar('R');
        //System.out.println("Weapon = " + weapon + ", hitPoints = " + weapon.getHitPoints() + ", minLevel = "
        //        + weapon.getMinLevel());

        //var list = Weapon.getWeaponsByLevel(1);
        //list.forEach(System.out::println);

        //Pirate tommy = new Pirate("Tommy");
        //tommy.setCurrentWeapon(Weapon.SWORD);
        //System.out.println(tommy);
        //System.out.println(separator);

        //PirateGame.getTowns(0).forEach(t -> System.out.println(t.information()));
        //System.out.println(separator);
        //PirateGame.getTowns(1).forEach(t -> System.out.println(t.information()));
        //System.out.println(separator);
        //PirateGame.getTowns(2).forEach(t -> System.out.println(t.information()));
        //System.out.println(separator);

        var console = new GameConsole<>(new PirateGame("The Pirate Game"));
        int playerIndex = console.addPlayer();
        console.playGame(playerIndex);

    }
}
