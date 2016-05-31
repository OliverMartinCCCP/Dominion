package deckexperiment.cards;
import deckexperiment.GameController;
import deckexperiment.GameState;
import deckexperiment.Player;
/**
 *
 * @author StefVanremoortele
 */
public class CouncilRoom extends ActionCard{
    private Player activePlayer;
    public CouncilRoom(){
        super("councilroom", 5);
    }
    @Override
    public void play(GameController game){
        //+4 cards, +1 buy
        //Each other player draws a card
        this.activePlayer = activePlayer;
        activePlayer.drawCards(4);
        activePlayer.updateBuys(+1);
        for (Player target : game.getPlayers()){
            if (!target.equals(activePlayer)){
                target.drawCards(1);
            }
        }
    }
    public Card clone(){
        return new CouncilRoom();
    }
}
