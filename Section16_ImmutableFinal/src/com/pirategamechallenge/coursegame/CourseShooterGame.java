package com.pirategamechallenge.coursegame;

import java.util.LinkedHashMap;
import java.util.Map;

public class CourseShooterGame extends CourseGame<CourseShooter> {

    public CourseShooterGame(String gameName) {
        super(gameName);
    }

    @Override
    public CourseShooter createNewPlayer(String name) {
        return new CourseShooter(name);
    }

    @Override
    public Map<Character, CourseGameAction> getGameActions(int playerIndex) {
        var map = new LinkedHashMap<>(Map.of(
                'F', new CourseGameAction('F', "Find Prize", this::findPrize),
                'S', new CourseGameAction('S', "Use your gun", this::useWeapon)
        ));
        map.putAll(getStandardActions());
        return map;
    }

    public boolean findPrize(int playerIndex) {
        return getPlayer(playerIndex).findPrize();
    }

    public boolean useWeapon(int playerIndex) {
        return getPlayer(playerIndex).useWeapon("pistol");
    }
}
