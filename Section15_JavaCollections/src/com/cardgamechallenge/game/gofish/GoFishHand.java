package com.cardgamechallenge.game.gofish;

import com.cardgamechallenge.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GoFishHand {

    private ArrayList<Card> hand;
    private ArrayList<Card> pairs;
    private int pairCount = 0;
    private int playerNo;

    public GoFishHand(int playerNo, ArrayList<Card> hand) {
        hand.sort(Card.sortRankReversedSuit());
        this.hand = hand;
        this.playerNo = playerNo;
        pairs = new ArrayList<>(hand.size());
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public ArrayList<Card> getPairs() {
        return pairs;
    }

    public int getPlayerNo() {
        return playerNo;
    }

    public void removeCard(Card c) {
        hand.remove(c);
    }

    public void addCard(Card c) {
        hand.add(c);
    }

    public void addPair(Card card1, Card card2) {
        if(Objects.equals(card1.face(), card2.face()) && card1.suit()!=card2.suit()) {
            pairs.addAll(List.of(card1, card2));
        }
    }

    public int getPairCount() {
        if(pairs.size()>0) {
            pairCount = pairs.size()/2;
        }
        return pairCount;
    }

    @Override
    public String toString() {
        return "%d. %-40s %s".formatted(
                playerNo, hand,
                (pairs.size() > 0) ? "Pairs:" + pairs : "");
    }
}
