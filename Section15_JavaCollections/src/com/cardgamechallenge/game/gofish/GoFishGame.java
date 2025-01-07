package com.cardgamechallenge.game.gofish;

import com.cardgamechallenge.Card;
import java.util.*;

public class GoFishGame {

    private final ArrayList<Card> deck = Card.getStandardDeck();
    private int playerCount;
    private int cardsInHand;
    private ArrayList<GoFishHand> goFishHands;
    Random random = new Random();

    public GoFishGame(int playerCount, int cardsInHand) {
        this.playerCount = playerCount;
        this.cardsInHand = cardsInHand;
        goFishHands = new ArrayList<>(cardsInHand);
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public int getCardsInHand() {
        return cardsInHand;
    }

    public int getPlayerHandCardCount(int playerNo) {
        return goFishHands.get(playerNo).getHand().size();
    }

    public void getWinner() {
        int winningPairCount = 0;
        ArrayList<Integer> winningPlayers = new ArrayList<>(4);
        for(GoFishHand h : goFishHands) {
            if(h.getPairCount()>winningPairCount) { winningPairCount = h.getPairCount(); }
        }
        for(GoFishHand h : goFishHands) {
            if(h.getPairCount()==winningPairCount) { winningPlayers.add(h.getPlayerNo()); }
        }
        System.out.println("Winning pair count: " + winningPairCount);
        System.out.print("Winning players: ");
        winningPlayers.forEach(i -> System.out.print(i + " | "));
    }

    public void startPlay() {

        Collections.shuffle(deck);
        Card.printDeck(deck);
        int randomMiddle = new Random().nextInt(15, 35);
        Collections.rotate(deck, randomMiddle);
        Card.printDeck(deck);

        deal();
        System.out.println("---------------------------");
        goFishHands.forEach(System.out::println);

        for(int p = 0; p<playerCount; p++) {
            GoFishHand currHand = goFishHands.get(p);
            ArrayList<Card> currCards = currHand.getHand();
            for(int c=0; c<currCards.size()/2; c++) {
                for(int e=currCards.size()-1; e>c; e--) {
                    if(Objects.equals(currCards.get(c).face(), currCards.get(e).face()) &&
                            currCards.get(c).suit()!=currCards.get(e).suit()) {
                        Card currCard = currCards.get(c);
                        Card matchCard = currCards.get(e);
                        currHand.addPair(currCard,matchCard);
                        currHand.removeCard(matchCard);
                        currHand.removeCard(currCard);
                        e--;
                    }
                }
            }
        }
        System.out.println("------- Checking hands for pairs -------");
        goFishHands.forEach(System.out::println);
    }

    public void checkPairs(int playerNo) {
        GoFishHand currHand = goFishHands.get(playerNo);
        ArrayList<Card> currCards = currHand.getHand();
        for(int c=0; c<currCards.size()/2; c++) {
            for(int e=currCards.size()-1; e>c; e--) {
                if(Objects.equals(currCards.get(c).face(), currCards.get(e).face()) &&
                        currCards.get(c).suit()!=currCards.get(e).suit()) {
                    Card currCard = currCards.get(c);
                    Card matchCard = currCards.get(e);
                    currHand.addPair(currCard,matchCard);
                    currHand.removeCard(matchCard);
                    currHand.removeCard(currCard);
                    e--;
                }
            }
        }
    }

    private void deal() {
        int removeCount = 0;
        Card[][] hands = new Card[playerCount][cardsInHand];
        for (int deckIndex = 0, i = 0; i < cardsInHand; i++) {
            for (int j = 0; j < playerCount; j++) {
                removeCount++;
                hands[j][i] = deck.get(deckIndex++);
            }
        }
        for(int r=0; r<removeCount; r++) {
            deck.remove(0);
            Card.printDeck(deck);
        }

        int playerNo = 1;
        for (Card[] hand : hands) {
            goFishHands.add(new GoFishHand(playerNo++, new ArrayList<>(Arrays.asList(hand))));
        }

    }

    public boolean fish(int playerNo, int opponent) {
        List<Card> opponentHand = goFishHands.get(opponent).getHand();
        List<Card> playerHand = goFishHands.get(playerNo).getHand();
        GoFishHand opponentCards = goFishHands.get(opponent);
        GoFishHand playerCards = goFishHands.get(playerNo);
        Card fishCard = playerHand.get(random.nextInt(playerHand.size()));
        System.out.println("Player" + (playerNo+1) + " is fishing and asks player " + (opponent+1) +
                " if they have any " + fishCard.face());
        for(Card c : opponentHand) {
            if(Objects.equals(c.face(), fishCard.face()) && c.suit() != fishCard.suit()) {
                opponentCards.removeCard(c);
                playerCards.removeCard(fishCard);
                playerCards.addPair(c,fishCard);
                System.out.println("Player" + (playerNo+1) + " caught " + fishCard + " from Player" + (opponent+1));
                goFishHands.forEach(System.out::println);
                return true;
            }
        }
        if(!deck.isEmpty()) {
            Card drawnCard = deck.get(0);
            deck.remove(0);
            playerCards.addCard(drawnCard);
            System.out.println("Player" + (playerNo+1) + " couldn't catch " + fishCard + " from Player" + (opponent+1)
                    + " and draws a card");
            checkPairs(playerNo);
        } else {
            System.out.println("Player" + (playerNo+1) + " couldn't catch " + fishCard + " from Player" + (opponent+1)
                    + " but there are no cards left in the deck. Next player goes.");
        }
        goFishHands.forEach(System.out::println);
        return false;
    }

}
