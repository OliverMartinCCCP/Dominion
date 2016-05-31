package deckexperiment.cards;

import deckexperiment.GameController;
import deckexperiment.GameState;
import deckexperiment.Player;
import deckexperiment.cards.*;

/**
 *
 * @author StefVanremoortele
 */
public class Estate extends VictoryCard{
    
    public Estate(){
        super("estate", 2, 1);
    }
    public Card clone(){
        return new Estate();
    }
    
    public void play(GameController game){}
}
