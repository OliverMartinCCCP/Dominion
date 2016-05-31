package cards;

import dominion.GameController;
import dominion.GameState;
import dominion.Player;
/**
 *
 * @author StefVanremoortele
 */
public abstract class VictoryCard extends Card {
    public abstract void play(GameController game);
    public abstract Card clone();
    public VictoryCard(String name, int cost, int value){
        super(name, "victory", cost, value);
    }
}
