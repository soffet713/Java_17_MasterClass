package com.pirategamechallenge.coursegame;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CoursePirateGame extends CourseGame<CoursePirate> {
    private static final List<List<String>> levelMap;

    //------------------------------------------------------------
    static {
        levelMap = new ArrayList<>();
        System.out.println("Loading Data...");
        loadData();

        if (levelMap.size() == 0) {
            throw new RuntimeException("Could not load data, try later");
        }
        System.out.println("Finished Loading Data.");
    }
    //------------------------------------------------------------

    public CoursePirateGame(String gameName) {
        super(gameName);
    }

    @Override
    public CoursePirate createNewPlayer(String name) {
        return new CoursePirate(name);
    }

    @Override
    public Map<Character, CourseGameAction> getGameActions(int playerIndex) {

        CoursePirate pirate = getPlayer(playerIndex);
        System.out.println(pirate);
        List<CourseWeapon> weapons = CourseWeapon.getWeaponsByLevel(pirate.value("level"));

        Map<Character, CourseGameAction> map = new LinkedHashMap<>();
        for (CourseWeapon weapon : weapons) {
            char init = weapon.name().charAt(0);
            map.put(init, new CourseGameAction(init, "Use " + weapon,
                    this::useWeapon));
        }
        map.putAll(getStandardActions());
        return map;
    }

    private static void loadData() {

        // Level 1 Towns
        levelMap.add(new ArrayList<>(List.of(
                "Bridgetown, Barbados",
                "Fitts Village, Barbados",
                "Holetown, Barbados"
        )));
        // Level 2 Towns
        levelMap.add(new ArrayList<>(List.of(
                "Fort-de-France, Martinique",
                "Sainte-Anne, Martinique",
                "Le Vauclin, Martinique"
        )));
    }

    public static List<String> getTowns(int level) {

        if (level <= (levelMap.size() - 1)) {
            return levelMap.get(level);
        }
        return null;
    }

    private boolean useWeapon(int playerIndex) {
        return getPlayer(playerIndex).useWeapon();
    }

    @Override
    public boolean executeGameAction(int player, CourseGameAction action) {

        getPlayer(player).setCurrentWeapon(CourseWeapon.getWeaponByChar(action.key()));
        return super.executeGameAction(player, action);
    }
}
