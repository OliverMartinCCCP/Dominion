package cards;
import dominion.GameController;
import dominion.GameState;
import dominion.Player;
/**
 *
 * @author StefVanremoortele
 */
public class MoneyLender extends ActionCard{
    private Player activePlayer;
    public MoneyLender(){
            super("moneylender", 4);
    }
    public void play(GameController game){
        // Trash a copper from your hand
        // if you do: +3 coins
        
        
    }
    public Card clone(){
        return new MoneyLender();
    }
    
}
