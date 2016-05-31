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
public class BuyStateTest implements GameState {

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
        this.activePlayer = game.getActivePlayer();
        this.game = game;
        this.supply = game.getSupply();
        this.input = "";
        this.cardBought = false;
        boolean stateFinished = false;

        while (!stateFinished){
            if (activePlayer.handContainsType("treasure") && activePlayer.getHandSize() > 0){
                chooseCardToPlay);
            } else {
                buyCard();
            }
        }
        chooseCard();
        tryToPlay();
        tryToBuy();
        buy(cardName);
        gainCard();        
    }
    public void chooseCardToPlay() {
        
    }
    public void play() {
        
    }
    public void tryToBuy() {
        
    }
    public void chooseCardToBuy(String cardName){
        
    }
    public void gainCard() {
        
    }

    public void updateSupply() {
        this.availableCardsToBuy = supply.getAllCardsWithMaxCost(activePlayer.getCoins());
    }

    public String toString() {
        return "buy state";
    }
}
