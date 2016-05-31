package cards;

import dominion.GameController;
import dominion.GameState;
import dominion.Player;
/**
 *
 * @author StefVanremoortele
 */
public class Province extends VictoryCard {
    
    public Province(){
        super("province", 8, 6);
    }
    public void play(GameController game){}
    public Card clone(){
        return new Province();
    }
}
