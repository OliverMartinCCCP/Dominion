package cards;
import dominion.GameController;
import dominion.GameState;
import dominion.Player;
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
        //+4 Cards
        activePlayer.drawCard(4);
        //+1 Buy
        activePlayer.updateBuys(1);
        //Each other player draws a card
        for (Player target : game.getPlayers()){
            if (!target.equals(activePlayer)){
                target.drawCard(1);
            }
        }
    }
    public Card clone(){
        return new CouncilRoom();
    }
}
