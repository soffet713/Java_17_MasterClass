package com.pirategamechallenge.coursegame;

//cannot inherit from final record class
//class SpecialGameAction extends GameAction {}

//cannot inherit from enum
//class SpecialGameAction extends Weapon {}

class SpecialGameConsole<T extends CourseGame<? extends CoursePlayer>>
        extends CourseGameConsole<CourseGame<? extends CoursePlayer>> {
    public SpecialGameConsole(CourseGame<? extends CoursePlayer> game) {
        super(game);
    }
}

public class CourseMainFinal {

    public static void main(String[] args) {
        SpecialGameConsole<CoursePirateGame> game = new SpecialGameConsole<>(new CoursePirateGame("Pirate Game"));
    }
}
