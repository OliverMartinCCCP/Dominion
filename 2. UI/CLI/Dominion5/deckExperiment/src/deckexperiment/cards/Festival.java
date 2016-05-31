package deckexperiment.cards;
import deckexperiment.GameController;
import deckexperiment.GameState;
import deckexperiment.Player;
/**
 *
 * @author StefVanremoortele
 */
public class Festival extends ActionCard{
    
    public Festival(){
        super("festival", 5);
    }
    public void play(GameController game){
        //+3 actions, +1 buy, +2 coins
        Player activePlayer = game.getActivePlayer();
        activePlayer.updateActions(+3);
        activePlayer.updateBuys(+1);
        activePlayer.updateCoins(+2);
    }
    public Card clone(){
        return new Festival();
    }
}
