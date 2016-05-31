package cards;
import dominion.GameController;
import dominion.GameState;
import dominion.Player;
/**
 *
 * @author StefVanremoortele
 */
public class Gardens extends ActionCard{
    
    public Gardens(){
        super("gardens", 4);
    }
    public void play(GameController game){
        //Worth 1 victory for every 10 cards in your deck (rounded downd)
    }
    public Card clone(){
        return new Gardens();
    }
}
