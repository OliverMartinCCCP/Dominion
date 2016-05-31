package cards;
import dominion.GameController;
import dominion.GameState;
import dominion.Player;
/**
 *
 * @author StefVanremoortele
 */
public class Feast extends ActionCard{
    
    public Feast(){
        super("feast", 4);
    }
    public void play(GameController game){
        //trash this card
        //Gain a card up to 5 coins
        game.trashCard(this);
        
    }
    public Card clone(){
        return new Feast();
    }
}
