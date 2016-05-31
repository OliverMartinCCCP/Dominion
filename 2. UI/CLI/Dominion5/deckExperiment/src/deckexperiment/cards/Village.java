package deckexperiment.cards;
import deckexperiment.GameController;
import deckexperiment.GameState;
import deckexperiment.Player;
/**
 *
 * @author StefVanremoortele
 */
public class Village extends ActionCard{
    private Player activePlayer;
    public Village(){
        super("village", 3);
    }
    public void play(GameController game){
        //+1 card , +2 actions
        game.getActivePlayer().drawCards(1);
        game.getActivePlayer().updateActions(+2);
    }
    public Card clone(){
        return new Village();
    }
}
