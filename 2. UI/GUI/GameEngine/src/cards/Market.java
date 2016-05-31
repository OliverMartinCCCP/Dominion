package cards;
import dominion.GameController;
import dominion.GameState;
import dominion.Player;
/**
 *
 * @author StefVanremoortele
 */
public class Market extends ActionCard{
    private Player activePlayer;
    public Market(){
        super("market", 5);
    }
    public void play(GameController game){
        // +1 card, +1 action, +1 coin, +1 buy
        activePlayer.drawCard(1);
        activePlayer.updateActions(1);
        activePlayer.updateCoins(1);
        activePlayer.updateBuys(1);
    }
    public Card clone(){
        return new Market();
    }
}

