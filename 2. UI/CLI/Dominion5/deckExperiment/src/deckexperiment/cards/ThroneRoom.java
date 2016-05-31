package deckexperiment.cards;

import deckexperiment.GameController;
import deckexperiment.GameState;
import deckexperiment.Player;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author StefVanremoortele
 */
public class ThroneRoom extends ActionCard {

    public ThroneRoom() {
        super("throneroom", 4);
    }

    public void play(GameController game) {
        Player activePlayer = game.getActivePlayer();
        //Choose an action card from your hand, play it twice
        Scanner sc = new Scanner(System.in);
        /*
        if (activePlayer.handContainsType("action")){
            System.out.println("Choose an action card from your hand. Play it twice.");
            boolean cardChosen = false;
            while (!cardChosen){
                String input = sc.nextLine();
                try{
                    int selectedCard = Integer.parseInt(input);
                    if (activePlayer.showCardFromHand(selectedCard) instanceof ActionCard){
                        Card cardToPlay = activePlayer.getCardFromHand(selectedCard);
                        
                    }
                    
                } catch (Exception e){
                    System.err.println(e);
                }
            }
        }
        */

    }
    public Card clone(){
        return new ThroneRoom();
    }
}

