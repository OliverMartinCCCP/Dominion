package deckexperiment.cards;
import deckexperiment.GameController;
import deckexperiment.GameState;
import deckexperiment.Player;
/**
 *
 * @author StefVanremoortele
 */
public abstract class ActionCard extends Card {
    
    public abstract void play(GameController game);
    public abstract Card clone();
    
    public ActionCard(String name, int cost){
        super(name, "action", cost, 0);
    }
}
