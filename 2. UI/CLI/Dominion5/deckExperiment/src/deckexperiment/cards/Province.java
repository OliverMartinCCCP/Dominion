package deckexperiment.cards;

import deckexperiment.GameController;
import deckexperiment.GameState;
import deckexperiment.Player;
/**
 *
 * @author StefVanremoortele
 */
public class Province extends VictoryCard {
    
    public Province(){
        super("province", 8, 6);
    }
    public void play(GameController game){}
    public Card clone(){
        return new Province();
    }
}
