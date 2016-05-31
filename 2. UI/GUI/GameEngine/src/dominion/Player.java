package dominion;

import java.util.ArrayList;
import java.util.Scanner;

import cards.*;

/**
 *
 * @author StefVanremoortele
 */
public class Player {

    private final String name;
    private final Deck hand = new Deck();
    private Deck discardPile = new Deck();
    private Deck drawPile = new Deck();
    private final Deck cardsInPlay = new Deck();
    private final Deck gainedCards = new Deck();
    private int actions = 1;
    private int buys = 1;
    private int coins = 0;
    private final Scanner sc = new Scanner(System.in);
    private Card boughtCard;

    public Player(String playerName) {
        this.name = playerName;
    }

    //Draws a card from the discard pile. If drawpile is empty discardpile gets shuffled and becomes the new drawpile
    public void drawCard(int amount) {
        if (drawPile.isEmpty()) {
                drawPile = discardPile;
                discardPile = new Deck();
                drawPile.shuffle();
            }
        Card card = drawPile.getCard(1);
        hand.addCard(card);
        drawPile.removeCard(card);
    }
    public void drawHand(){
        while (hand.getSize() < 5) drawCard(1);
    }
    //adds the amount given
    public void updateActions(int amount) {
        if ((this.actions + amount) < 0) {
            this.actions = 0;
        } else {
            this.actions += amount;
        }
    }
    //adds the amount given
    public void updateBuys(int amount) {
        if ((this.buys + amount) < 0) {
            this.buys = 0;
        } else {
            this.buys += amount;
        }
    }
    //adds the amount given
    public void updateCoins(int amount) {
        if ((this.coins + amount) < 0) {
            this.coins = 0;
        } else {
            this.coins += amount;
        }
    }
    public boolean hasActionCardInHand() {
        return hand.containsCardOfType("action");
    }
    public boolean hasTreasureCardInHand() {
        return hand.containsCardOfType("treasure");
    }
    public boolean hasVictoryCardInHand() {
        return hand.containsCardOfType("victory");
    }
    //Removes the card from the deck
    public Card giveCardFrom(Deck deck, int cardPosition) {
        return deck.getCard(cardPosition);
    }
    //Removes the card from the hand
    public Card giveCardFromHand(int cardPosition) {
        return hand.getCard(cardPosition);
    }
    public void removeCardFromHand(Card card) {
        hand.removeCard(card);
    }
    //Puts the card from hand in cardsInPlay
    public void playCard(Card card) {
        try {
            cardsInPlay.addCard(card);
            hand.removeCard(card);
        } catch (Exception e) {
            System.err.println("Error playing card");
        }
    }

    //Adds the card tot he discardPile
    public void discard(Card card) {
        discardPile.addCard(card);
    }

    public void discardToDeck(Deck pile, Card card) {//TODO: check if needed
        pile.addCard(card);
        while (!pile.isEmpty()){
            
        }
    }
    public void discardDeck(Deck deck) {
        ArrayList<Card> temp = deck.getAllCards();
        for (Card card : temp){
            discardPile.addCard(card);
        }
        deck.clear();
    }
    public Deck getDrawPile() {
        return this.drawPile;
    }

    public Deck getDiscardPile() {
        return this.discardPile;
    }

    public Deck getHand() {
        return this.hand;
    }

    public Deck getCardsInPlay() {
        return this.cardsInPlay;
    }

    public String getName() {
        return this.name;
    }

    public int getActions() {
        return actions;
    }

    public int getBuys() {
        return buys;
    }

    public int getCoins() {
        return coins;
    }

    public int getHandSize() {
        return hand.getSize();
    }

    public void revealCard(Card cardToReveal) {
        System.out.println(this.name + " reveals a card:" + cardToReveal.getName());
    }

    public ArrayList revealHand() {
        ArrayList<Card> revealedHand = new ArrayList<Card>();
        for (int i = 0; i < hand.getSize(); i++) {
            revealedHand.add(hand.showCard(i+1));
        }
        return revealedHand;
    }

    //puts the given card into the discardPile
    public void discardCard(Card card) {
        discardPile.addCard(card);
    }

    public Card getCardFromHand(int position) {
        return hand.showCard(position);
    }

    public void discardDecksAfterPlay() {
        while (!hand.isEmpty()) {
            discard(hand.getCard(1));
        }
        while (!cardsInPlay.isEmpty()) {
            discard(cardsInPlay.getCard(1));
        }
        hand.clear();
        cardsInPlay.clear();
    }

    public void resetValues() {
        this.actions = 1;
        this.buys = 1;
        this.coins = 0;
        System.out.println("values have been reset for player: " + getName());
    }

    public void printStatus() {
        System.out.println("actions: " + actions);
        System.out.println("buys: " + buys);
        System.out.println("coins: " + coins);
    }

    public void printHand() {
        
        hand.printCards();
    }
    
    public void printInfo(){
        printStatus();
        printHand();
    }
}
