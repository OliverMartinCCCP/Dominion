package cards;

import dominion.GameController;
import dominion.GameState;
import dominion.Player;
/**
 *
 * @author StefVanremoortele
 */
public abstract class TreasureCard extends Card{
    public abstract void play(GameController game);
    public abstract Card clone();
    public TreasureCard(String name, int cost, int value){
        super(name,"treasure", cost, value);
    }    
}
