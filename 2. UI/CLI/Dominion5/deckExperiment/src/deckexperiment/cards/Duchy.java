package deckexperiment.cards;

import deckexperiment.GameController;
import deckexperiment.GameState;
import deckexperiment.Player;
/**
 *
 * @author StefVanremoortele
 */
public class Duchy extends VictoryCard{
    
    public Duchy(){
        super("duchy", 5, 3);
    }
    public void play(GameController game){}
    public Card clone(){
        return new Duchy();
    }
}
