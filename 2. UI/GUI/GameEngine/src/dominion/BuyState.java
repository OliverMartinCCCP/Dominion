/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion;

import java.util.Scanner;

import cards.*;

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
    private String cardPlayed = "";
    

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
        while (!stopBuyPhase){
            updateSupply();
            if (!cardBought && activePlayer.hasTreasureCardInHand() && input == ""){
                playOrBuy();
                clearPlayedCard();
            } else {
                buy();
                clearPlayedCard();
            }
            
            if (activePlayer.getBuys() <= 0 || input.toLowerCase().equals("stop")) stopBuyPhase = true;
        }
        game.setState(new CleanState());
        game.getState().run(game);
    }
    public void SetPlayedCard(String cardPlayed){
    	this.cardPlayed = cardPlayed;
    }
    public void clearPlayedCard(){
    	cardPlayed="";
    }
    public void playOrBuy(){
        activePlayer.printInfo();
        for (Card card : availableCardsToBuy){
            System.out.println(card.getName());
        }
        System.out.println("Choose a card to play or buy, or enter STOP");
        if(cardPlayed == ""){
        	input = sc.nextLine();
        }
        else{
        	input = cardPlayed;
        }
        try {
            int selectedCard = Integer.parseInt(input);
            tryToPlay(selectedCard);
        } catch (Exception e) {
            tryToBuy(input);
        }
    }

    public void buy(){
        activePlayer.printInfo();
        for (Card card : availableCardsToBuy){
            System.out.println(card.getName());
        }
        System.out.println("Choose a card to buy, or enter STOP");
        if(cardPlayed == ""){
        	input = sc.nextLine();
        }
        else{
        	input = cardPlayed;
        }
        if (supply.contains(input)) tryToBuy(input);
    }
    public void tryToPlay(int selectedCard){
        Card cardToPlay = activePlayer.getCardFromHand(selectedCard);
        if (cardToPlay instanceof TreasureCard) {
            activePlayer.playCard(cardToPlay);
            cardToPlay.play(game);
        }
    }
    public void tryToBuy(String cardName){
        if (supply.canPlayerBuyCard(activePlayer, cardName)){
            Card boughtCard = supply.showCard(cardName);
            activePlayer.updateBuys(-1);
            activePlayer.updateCoins(-(boughtCard.getCost()));
            gain(cardName);
        }
    }
    public void gain(String cardName){
        Card cardToGain = supply.gainCard(cardName);
        activePlayer.discard(cardToGain);
        System.out.println(activePlayer.getName()+" gained a "+cardToGain.getName());
    }
    public void updateSupply() {
        this.availableCardsToBuy = supply.getAllCardsWithMaxCost(activePlayer.getCoins());
    }

    public String toString() {
        return "buy state";
    }
}
