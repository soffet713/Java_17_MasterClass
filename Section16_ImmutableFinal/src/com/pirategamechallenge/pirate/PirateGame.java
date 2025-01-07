package com.pirategamechallenge.pirate;

import com.pirategamechallenge.game.Game;
import com.pirategamechallenge.game.GameAction;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PirateGame extends Game<Pirate> {

    private static final List<List<Town>> levelMap;

    //-----------------------------------------------------------------------------
    static {
        levelMap = new ArrayList<>();
        System.out.println("Loading Data...");
        loadData();

        if(levelMap.size() == 0) {
            throw new RuntimeException("Could not load data, try again later.");
        }
        System.out.println("Finished Loading Data.");
    }
    //-----------------------------------------------------------------------------

    public PirateGame(String gameName) {
        super(gameName);
    }

    @Override
    public Pirate createNewPlayer(String name) {
        return new Pirate(name);
    }

    @Override
    protected Map<Character, GameAction> getGameActions(int playerIndex) {
        Pirate pirate = getPlayer(playerIndex);
        System.out.println(pirate);
        List<Weapon> weapons = Weapon.getWeaponsByLevel(pirate.value("level"));

        Map<Character, GameAction> map = new LinkedHashMap<>();
        if(pirate.hasOpponents()) {
            for(Weapon weapon : weapons) {
                char firstChar = weapon.name().charAt(0);
                map.put(firstChar, new GameAction(firstChar, "Use " + weapon, this::useWeapon));
            }
        }
        map.put('T', new GameAction('T', "Get Town Details", this::getTownInfo));
        map.put('L', new GameAction('L', "Get Level Info", this::getLevelInfo));
        map.put('F',new GameAction('F',"Find Treasure", this::findTreasure));
        if(pirate.isExperienced()) {
            map.put('X', new GameAction('X', "Interact with Town Feature", this::featureInteraction));
        }
        map.putAll(getStandardActions());
        return map;
    }

    private static void loadData() {

        // Level 1 Towns
        levelMap.add(new ArrayList<Town>(List.of(
                new Town("Bridgetown", "Barbados",0),
                new Town("Fitts Village", "Barbados", 0),
                new Town("Holetown", "Barbados", 0)
        )));
        // Level 2 Towns
        levelMap.add(new ArrayList<Town>(List.of(
                new Town("Fort-de-France", "Martinique", 1),
                new Town("Sainte-Anne", "Martinique", 1),
                new Town("Le Vauclin", "Martinique", 1)
        )));
        // Level 3 Towns
        levelMap.add(new ArrayList<Town>(List.of(
                new Town("Izumisano", "Osaka", 2),
                new Town("Osaka", "Osaka", 2),
                new Town("Sakai", "Osaka", 2)
        )));
    }

    public static List<Town> getTowns(int level) {
        if(level >=0 && level <= levelMap.size()-1) {
            return levelMap.get(level);
        }
        return null;
    }

    private boolean useWeapon(int playerIndex) {
        return getPlayer(playerIndex).useWeapon();
    }

    @Override
    public boolean executeGameAction(int player, GameAction action) {

        getPlayer(player).setCurrentWeapon(Weapon.getWeaponByChar(action.key()));
        return super.executeGameAction(player, action);
    }

    private boolean findTreasure(int playerIndex) {
        return getPlayer(playerIndex).findLoot();
    }

    private boolean featureInteraction(int playerIndex) {
        return getPlayer(playerIndex).featureInteraction();
    }

    private boolean getTownInfo(int playerIndex) {
        return getPlayer(playerIndex).getTownInfo();
    }

    private boolean getLevelInfo(int playerIndex) {
        List<Town> currLevel = levelMap.get(getPlayer(playerIndex).value("level"));
        String currIsland = currLevel.get(0).island();
        System.out.println("Current Island: " + currIsland.toUpperCase());
        currLevel.forEach(t -> System.out.println(t.information()));
        return false;
    }

    @Override
    public boolean printPlayer(int playerIndex) {
        System.out.println(getPlayer(playerIndex).information());
        return false;
    }
}
