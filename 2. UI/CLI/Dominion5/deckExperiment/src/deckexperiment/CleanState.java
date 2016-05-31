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
public class CleanState implements GameState {
    private GameController game;
    @Override
    public void run(GameController game){
        System.out.println(this);
        Player activePlayer = game.getActivePlayer();
        
        //Discard inplay cards
        //discard hand
        activePlayer.discardDeck(activePlayer.getCardsInPlay());
        activePlayer.discardDeck(activePlayer.getHand());
        // draw new hand for activePlayer
        activePlayer.drawHand();
        // reset status activeplayer
        activePlayer.resetValues();
        // check for finished game (province pile empty or 3 empty piles)
        game.nextPlayer();
       
       if (game.isGameFinished()) {
           game.setState(new FinishedState());
           game.getState().run(game);
       }
       System.out.println("End cleanstate");
    }
    @Override
    public String toString() {
        return "clean state";
    }
}
