package com.cardgamechallenge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public record Card(Card.Suit suit, String face, int rank) {
    public enum Suit { CLUB,DIAMOND,HEART,SPADE;

        public char getImage() {
            return (new char[]{9827,9830,9829,9824})[this.ordinal()];
        }
    }

    public static Comparator<Card> sortRankReversedSuit() {
        return Comparator.comparing(Card::rank).reversed().thenComparing(Card::suit);
    }

    public static Card getNumericCard(Card.Suit suit, int number) {
        if(number > 1 && number < 11) {
            return new Card(suit, String.valueOf(number), number-2);
        }
        System.out.println("Invalid Numeric Card Selected.");
        return null;
    }

    public static Card getFaceCard(Card.Suit suit, char abbrev) {
        int charIndex = "JQKA".indexOf(abbrev);
        if (charIndex>-1) {
            return new Card(suit, "" + abbrev, charIndex + 9);
        }
        System.out.println("Invalid Face Card Selected.");
        return null;
    }

    public static ArrayList<Card> getStandardDeck() {
        ArrayList<Card> deck = new ArrayList<>(52);
        for(Card.Suit suit : Card.Suit.values()) {
            for(int c = 2; c <= 10; c++) {
                deck.add(getNumericCard(suit,c));
            }
            for(char c : new char[] {'J','Q','K','A'}) {
                deck.add(getFaceCard(suit,c));
            }
        }
        return deck;
    }

    public static void printDeck(List<Card> deck) {
        printDeck(deck, "Current Deck", 4);
    }

    public static void printDeck(List<Card> deck, String description, int rows) {
        System.out.println("-".repeat(50));
        if(description!=null) {
            System.out.println(description);
        }
        int cardsInRow = deck.size() / rows;
        for(int i = 0; i < rows; i++) {
            int startIndex = i * cardsInRow;
            int endIndex = startIndex + cardsInRow;
            deck.subList(startIndex, endIndex).forEach(c -> System.out.print(c + " "));
            System.out.println();
        }
    }

    @Override
    public String toString() {
        int index = face.equals("10") ? 2 : 1;
        String faceString = face.substring(0,index);
        return "%s%c(%d)".formatted(faceString, suit.getImage(),rank);
    }
}
