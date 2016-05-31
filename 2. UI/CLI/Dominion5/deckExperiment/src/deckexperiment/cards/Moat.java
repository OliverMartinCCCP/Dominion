package deckexperiment.cards;
import deckexperiment.GameController;
import deckexperiment.GameState;
import deckexperiment.Player;
/**
 *
 * @author StefVanremoortele
 */
public class Moat extends ActionCard{
    private Player activePlayer;
    public Moat(){
            super("moat", 2);
    }
    public void play(GameController game){
        // +2 cards
        // When another player plays an attack card, you may reveal this from your hand
        // If you do, you are unaffected by that attack
    }
    public Card clone(){
        return new Moat();
    }
}
