package cards;

import cards.*;
import dominion.GameController;
import dominion.GameState;
import dominion.Player;

/**
 *
 * @author StefVanremoortele
 */
public class Estate extends VictoryCard{
    
    public Estate(){
        super("estate", 2, 1);
    }
    public Card clone(){
        return new Estate();
    }
    
    public void play(GameController game){}
}
