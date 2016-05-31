package deckexperiment.cards;

import deckexperiment.GameController;
import deckexperiment.GameState;
import deckexperiment.Player;
/**
 *
 * @author StefVanremoortele
 */
public class Curse extends Card{
 
    public Curse(){
        super("curse", "curse", 0, -1);
    }
    @Override
    public void play(GameController game){
        //TODO: CODE
        //TODO: CODE
        //TODO: CODE
    }
    
    public Card clone(){
        return new Curse();
    }
}
