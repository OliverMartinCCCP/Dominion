package cards;

import java.util.ArrayList;

import dominion.GameController;
import dominion.GameState;
import dominion.Player;

/**
 *
 * @author StefVanremoortele
 */
public class Spy extends ActionCard {

    private Player activePlayer;
    private Player target;
    private ArrayList<Card> revealedCards;

    public Spy() {
        super("spy", 4);
    }

    public void play(GameController game) {
        //+1 card, +1 action
        //Each player (including you) reveals the top card of his deck
        //and either discards it or puts it back, your choise
        
    }
    public Card clone(){
        return new Spy();
    }
}

