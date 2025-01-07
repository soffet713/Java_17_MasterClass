package com.randomizationdicegame.dice;

import com.randomizationdicegame.game.GameConsole;
import com.randomizationdicegame.game.Player;

import java.util.*;

public class DicePlayer implements Player {

    private final String name;
    private final List<Integer> currentDice = new ArrayList<>();
    private final Map<ScoredItem,Integer> scoreCard = new EnumMap<>(ScoredItem.class);
    private int currentRoll = 0;
    private final Map<Integer, Integer> diceRolls = new HashMap<>();

    public DicePlayer(String name) {
        this.name = name;
        for (ScoredItem item : ScoredItem.values()) {
            scoreCard.put(item,null);
        }
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return "DicePlayer: name = " + name + " | currentDice = " + currentDice + " | scoreCard = " + scoreCard;
    }

    public void rollDice() {
        diceRolls.compute(currentRoll, (k, count) -> count + 1);
        int currCount = diceRolls.get(currentRoll);

        if(currCount<8) {
            int randomCount = 5 - currentDice.size();

            var newDice = new Random()
                    .ints(randomCount, 1, 7)
                    .sorted()
                    .boxed()
                    .toList();

            currentDice.addAll(newDice);
        } else {
            System.out.println("You have reached the max number of re-rolls: 7");
            System.out.println("-".repeat(75));
        }

        System.out.println("You're current dice are: " + currentDice);
    }

    private boolean pickReRolls() {
        String prompt = """
                Press Enter to generate Score.
                Type "All" to re-roll all dice.
                List numbers separated by spaces [ ] in descending order to re-roll those selected die.
                """;

        String userInput = GameConsole.getUserInput(prompt + "--> ");
        if (userInput.isBlank()) {
            return true;
        }
        try {
            removeDice(userInput.split(" "));
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Bad input. Try again.");
        }
        return false;
    }

    private void removeDice(String[] selected) {
        if(selected.length == 1 && selected[0].toUpperCase().contains("ALL")) {
            currentDice.clear();
        } else {
            for (String remove : selected) {
                //System.out.println("Removing: " + currentDice.get(Integer.valueOf(remove)));
                currentDice.remove(Integer.valueOf(remove));
            }
            System.out.println("Keeping: " + currentDice);
        }
    }

    public boolean rollDiceAndSelect() {
        this.currentRoll++;
        do {
            rollDice();
        } while (!pickReRolls());

        do {
            System.out.println("Select a score category: ");
        } while (!scoreDice());

        currentDice.clear();
        return (getItemList().isEmpty());
    }

    public List<String> getItemList() {
        return scoreCard
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == null)
                .map(e -> e.getKey().name())
                .toList();
    }

    private boolean scoreDice() {
        List<String> remainingItems = getItemList();
        String prompt = String.join("\n", remainingItems.toArray(new String[0]));
        String userInput = GameConsole.getUserInput(prompt + "\n--> ").toUpperCase();
        if(userInput.isBlank()) {
            return false;
        }

        if(!remainingItems.contains(userInput)) {
            System.out.println("Invalid Selection");
            return false;
        }
        ScoredItem item = ScoredItem.valueOf(userInput);
        scoreCard.put(item, item.score(currentDice));
        return true;
    }
}
