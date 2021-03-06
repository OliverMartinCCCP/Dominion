package cards;
import dominion.GameController;
import dominion.GameState;
import dominion.Player;
/**
 *
 * @author StefVanremoortele
 */
public class WoodCutter extends ActionCard{
    private Player activePlayer;
    public WoodCutter(){
        super("woodcutter", 3);
    }
    public void play(GameController game){
        //+1 buy , +2 coins
        activePlayer.updateCoins(2);
        activePlayer.updateBuys(1);
    }
    public Card clone(){
        return new WoodCutter();
    }
}
