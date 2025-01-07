package com.cardgamechallenge;

import com.collectionsmethods.Card;

import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<com.collectionsmethods.Card> deck = com.collectionsmethods.Card.getStandardDeck();
        com.collectionsmethods.Card.printDeck(deck);

        Collections.shuffle(deck);
        Card.printDeck(deck, "Shuffled Deck", 4);
    }
}
