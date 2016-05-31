package cards;
import dominion.GameController;
import dominion.GameState;
import dominion.Player;
/**
 *
 * @author StefVanremoortele
 */
public class Remodel extends ActionCard{
    private Player activePlayer;
    public Remodel(){
            super("remodel", 4);
    }
    public void play(GameController game){
        // Trash a card from your hand.
        // Gain a card costing up to 2 more than the trashed card.
        
    }
    public Card clone(){
        return new Remodel();
    }
}
