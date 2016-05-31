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
            System.out.println("availableCardsToPlay: ");
            System.out.println(availableCardsToPlay);
            System.out.println("Choose a card to play or enter STOP");
            input = sc.nextLine();
            try {
                int selectedCard = Integer.parseInt(input);
                Card cardToPlay = activePlayer.getCardFromHand(selectedCard);
                if ( cardToPlay instanceof ActionCard) {
                    System.out.println("now playing "+cardToPlay.getName());
                    activePlayer.playCard(cardToPlay);
                    activePlayer.updateActions(-1);
                    cardToPlay.play(game);
                }                
            } catch (NumberFormatException e) {
                if ( input.toLowerCase().equals("stop") ) stopPhase = true;
            }
            if (!ableToPlayAnotherAction(activePlayer) ) stopPhase = true;
        }
        System.out.println(this+" finished..");
        game.setState(new BuyState());
    }
    public boolean ableToPlayAnotherAction(Player player){
        return ( (player.hasActionCardInHand()) && (player.getActions() > 0) );
    }
    public String toString() {
        return "action state";
    }
	@Override
	public void SetPlayedCard(String cardPlayed) {
		// TODO Auto-generated method stub
		
	}
}
