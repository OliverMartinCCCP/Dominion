package cards;

import dominion.GameController;
import dominion.GameState;
import dominion.Player;
/**
 *
 * @author StefVanremoortele
 */
public class Gold extends TreasureCard {
    
    public Gold(){
        super("gold", 6, 3);
    }
    public void play(GameController game){
        Player activePlayer = game.getActivePlayer();
        activePlayer.updateCoins(this.getValue());
        System.out.println(this.getValue()+" coin(s) added");
    }
    public Card clone(){
        return new Gold();
    }
}
