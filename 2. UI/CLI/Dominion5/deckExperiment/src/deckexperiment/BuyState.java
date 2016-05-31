/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deckexperiment;

import deckexperiment.cards.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author StefVanremoortele
 */
public class BuyState implements GameState {

    private GameController game;
    private Supply supply;
    private Player activePlayer;
    private ArrayList<Card> availableCardsToBuy;
    private Scanner sc = new Scanner(System.in);
    private String input;
    private boolean cardBought;

    @Override
    public void run(GameController game) {
        System.out.println(this);
        boolean buyPhaseFinished = false;
        this.activePlayer = game.getActivePlayer();
        this.game = game;
        this.supply = game.getSupply();
        this.input = "";
        this.cardBought = false;
        boolean playOrBuy = false;
        boolean stopBuyPhase = false;
        // ZOLANG SPELER BUYS HEEFT EN NIET OP STOP HEEFT GEKLIKT
        //    ALS SPELERS NOG GEEN KAART GEKOCHT HEEFT => tryToPlay OR tryToBuy 
        //      ANDERS: tryToBuy
        while (!stopBuyPhase) {
            updateSupply();
            activePlayer.printInfo();
            if (!cardBought && activePlayer.handContainsType("treasure")) {
                System.out.println("Choose a card to buy or play");
            } else {
                System.out.println("Choose a card to buy");
            }

            showAvailableCardsToBuy();

            String input = sc.nextLine();
            if (input.toLowerCase().equals("stop")) {
                stopBuyPhase = true;
            } else if (input.toLowerCase().equals("all")) {
                playAllTreasures();
            } else {
                playOrBuy(input);
            }
            if ( (activePlayer.getBuys() == 0) || (input.toLowerCase().equals("stop")) ) stopBuyPhase = true;
        }
        game.setState(new CleanState());
        game.getState().run(game);
    }

    public void playOrBuy(String input) {
        try {
            int selectedCard = Integer.parseInt(input);
            if (!cardBought && activePlayer.showCardFromHand(selectedCard) instanceof TreasureCard) {
                Card cardToPlay = activePlayer.showCardFromHand(selectedCard);
                activePlayer.playCard(cardToPlay);
                cardToPlay.play(game);
            }
        } catch (Exception e) {
            if (supply.isAbleToSell(activePlayer, input)) {
                Card boughtCard = supply.showCard(input);
                activePlayer.updateBuys(-1);
                activePlayer.updateCoins(-(boughtCard.getCost()));
                gain(input);
                cardBought = true;
            }
        }
    }

    public void playAllTreasures() {
        Deck hand = activePlayer.getHand();

        for (Card card : hand.getCardsOftType("treasure")) {
            card.play(game);
            activePlayer.playCard(card);
        }
    }

    public void gain(String cardName) {
        Card cardToGain = supply.gainCard(cardName);
        activePlayer.discard(cardToGain);
        System.out.println(activePlayer.getName() + " gained a " + cardToGain.getName());
    }

    public void updateSupply() {
        this.availableCardsToBuy = supply.getAllCardsWithMaxCost(activePlayer.getCoins());
    }

    public void showAvailableCardsToBuy() {
        for (Card c : availableCardsToBuy) {
            System.out.println(c.getName()+"\t");
        }
    }

    public String toString() {
        return "buy state";
    }
}
