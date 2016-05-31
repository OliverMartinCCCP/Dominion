package cards;
import cards.*;
import dominion.GameController;
import dominion.GameState;
import dominion.Player;
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
        game.getActivePlayer().drawCard(3);  
    }
    public Card clone(){
        return new Smithy();
    }
}
