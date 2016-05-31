package deckexperiment.cards;
import deckexperiment.GameController;
import deckexperiment.GameState;
import deckexperiment.Player;
/**
 *
 * @author StefVanremoortele
 */
public class Laboratory extends ActionCard{
    public Laboratory(){
        super("laboratory", 5);
    }
    public void play(GameController game){
        Player activePlayer = game.getActivePlayer();
        //+2 cards , +1 Action
        activePlayer.drawCards(2);
        activePlayer.updateActions(+1);
    }
    public Card clone(){
        return new Laboratory();
    }
}
