package deckexperiment.cards;
import deckexperiment.GameController;
import deckexperiment.GameState;
import deckexperiment.Player;
import deckexperiment.cards.*;
/**
 *
 * @author StefVanremoortele
 */
public class Smithy extends ActionCard{
    
    public Smithy(){
        super("smithy", 4);
    }
    public void play(GameController game){
        //+3 cards
        game.getActivePlayer().drawCards(3);  
    }
    public Card clone(){
        return new Smithy();
    }
}
