package com.pirategamechallenge.shooter;

import com.pirategamechallenge.game.Game;
import com.pirategamechallenge.game.GameAction;

import java.util.LinkedHashMap;
import java.util.Map;

public class ShooterGame extends Game<Shooter> {

    public ShooterGame(String gameName) {
        super(gameName);
    }

    @Override
    public Shooter createNewPlayer(String name) {
        return new Shooter(name);
    }

    @Override
    public Map<Character, GameAction> getGameActions(int playerIndex) {
        Map<Character, GameAction> actionMap = new LinkedHashMap<>(Map.of(
                'D',new GameAction('D',"Dash Forward", this::dash),
                'F',new GameAction('F',"Find Treasure", this::findTreasure),
                'G',new GameAction('G',"Throw Grenade", this::throwGrenade),
                'J',new GameAction('J',"Jump Over Obstacle", this::jump),
                'S',new GameAction('S',"Fire your weapon", this::fireWeapon)
        ));
        actionMap.putAll(getStandardActions());
        return actionMap;
    }

    public boolean findTreasure(int playerIndex) {
        return getPlayer(playerIndex).findTreasure();
    }

    public boolean dash(int playerIndex) {
        return getPlayer(playerIndex).dash("take cover");
    }

    public boolean throwGrenade(int playerIndex) {
        return getPlayer(playerIndex).throwGrenade();
    }

    public boolean jump(int playerIndex) {
        return getPlayer(playerIndex).jump("fire pit");
    }

    public boolean fireWeapon(int playerIndex) {
        return getPlayer(playerIndex).fireWeapon("Battle Rifle");
    }
}
