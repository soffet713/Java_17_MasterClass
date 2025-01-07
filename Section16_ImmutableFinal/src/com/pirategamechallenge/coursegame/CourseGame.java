package com.pirategamechallenge.coursegame;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class CourseGame<T extends CoursePlayer> {

    private final String gameName;
    private final List<T> players = new ArrayList<>();
    private Map<Character, CourseGameAction> standardActions = null;

    public CourseGame(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    public Map<Character, CourseGameAction> getStandardActions() {
        if(standardActions == null) {
            standardActions = new LinkedHashMap<>(Map.of(
                    'I', new CourseGameAction('I', "Print Player Info", i -> this.printPlayer(i)),
                    'Q', new CourseGameAction('Q', "Quit Game", this::quitGame)
            ));
        }
        return standardActions;
    }

    public abstract T createNewPlayer(String name);

    public abstract Map<Character, CourseGameAction> getGameActions(int playerIndex);

    final int addPlayer(String name) {
        T player = createNewPlayer(name);
        if(player != null) {
            players.add(player);
            return players.size() - 1;
        }
        return -1;
    }

    protected final T getPlayer(int playerIndex) {
        return players.get(playerIndex);
    }

    public boolean executeGameAction(int player, CourseGameAction action) {
        return action.action().test(player);
    }

    public boolean printPlayer(int playerIndex) {
        CoursePlayer player = players.get(playerIndex);
        System.out.println(player);
        return false;
    }

    public boolean quitGame(int playerIndex) {
        CoursePlayer player = players.get(playerIndex);
        System.out.println("Sorry to see you go, " + player.name());
        return true;
    }
}
