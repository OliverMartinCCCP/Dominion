package cards;
import dominion.GameController;
import dominion.GameState;
import dominion.Player;
/**
 *
 * @author StefVanremoortele
 */
public class Mine extends ActionCard{
    
    public Mine(){
        super("mine", 5);
    }
    public void play(GameController game){
        //Trash a treasureCard from your hand
        //Gain a treasureCard costing up to 3coins more
        //Put it into your hand
        
    }
    public Card clone(){
        return new Mine();
    }
}
