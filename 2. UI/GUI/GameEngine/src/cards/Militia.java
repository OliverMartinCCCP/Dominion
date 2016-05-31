package cards;
import dominion.GameController;
import dominion.GameState;
import dominion.Player;
/**
 *
 * @author StefVanremoortele
 */
public class Militia extends ActionCard{
    
    public Militia(){
        super("militia", 4);
    }
    public void play(GameController game){
        //+2 coins
        //Each other player discards down to 3 cards in his hand
        Player activePlayer = game.getActivePlayer();
        activePlayer.updateCoins(2);
        for ( Player player : game.getPlayers() ){
            //TODO: player.discard( ) fix
        }
    }
    public Card clone(){
        return new Militia();
    }
}
