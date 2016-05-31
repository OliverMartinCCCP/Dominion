package deckexperiment.cards;

import deckexperiment.GameController;
import deckexperiment.GameState;
import deckexperiment.Player;
/**
 *
 * @author StefVanremoortele
 */
public abstract class TreasureCard extends Card{
    public abstract void play(GameController game);
    public abstract Card clone();
    public TreasureCard(String name, int cost, int value){
        super(name,"treasure", cost, value);
    }    
}
