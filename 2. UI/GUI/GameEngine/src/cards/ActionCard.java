package cards;
import dominion.GameController;
import dominion.GameState;
import dominion.Player;
/**
 *
 * @author StefVanremoortele
 */
public abstract class ActionCard extends Card {
    
    public abstract void play(GameController game);
    public abstract Card clone();
    public ActionCard(String name, int cost){
        super(name, "action", cost, 0);
    }
}
