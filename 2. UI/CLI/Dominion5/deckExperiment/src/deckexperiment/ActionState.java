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
public class ActionState implements GameState {
    private GameController game;
    Player activePlayer; 
    private Scanner sc;
    private ArrayList<Card> availableCardsToPlay;
    public void run(GameController game) {
        System.out.println(this);
        this.game = game;
        this.sc = new Scanner(System.in);
        activePlayer = game.getActivePlayer();
        availableCardsToPlay = new ArrayList<Card>();
        

        //gets the available cards player can buy
        
        String input = "";
        boolean stopPhase = false;
        
        while (!stopPhase){
            availableCardsToPlay = activePlayer.getHand().getCardsOftType("action");
            System.out.println("Players hand:");
            activePlayer.printInfo();            
            System.out.println("Choose a card to play or enter STOP");
            input = sc.nextLine();
            try {
                int selectedCard = Integer.parseInt(input);
                Card cardToPlay = activePlayer.showCardFromHand(selectedCard);
                if ( cardToPlay instanceof ActionCard) {
                    System.out.println("now playing "+cardToPlay.getName());
                    activePlayer.playCard(cardToPlay);
                    activePlayer.updateActions(-1);
                    cardToPlay.play(game);
                }                
            } catch (Exception e) {
                if ( input.equals("stop") ) stopPhase = true;
            }
            if (!ableToPlayAnotherAction(activePlayer) ) stopPhase = true;
        }
        System.out.println(this+" finished..");
        game.setState(new BuyState());
        game.getState().run(game);
    }
    
    public boolean ableToPlayAnotherAction(Player player){
        return ( (player.hasActionCardInHand()) && (player.getActions() > 0) );
    }
    public String toString() {
        return "action state";
    }
    public void printAvailableCardsToPlay(){
        
        String res = "";
        int i = 0;
        for (Card card : availableCardsToPlay){
            res += i+": "+card.getName()+"\t";
            i++;
        }
        System.out.println(res);
    }
}
