package cards;

import dominion.GameController;
import dominion.GameState;
import dominion.Player;
/**
 *
 * @author StefVanremoortele
 */
public class Copper extends TreasureCard{
    
    private int value;
    
    public Copper(){
        super("copper", 0, 1);
    }
    @Override
    public void play(GameController game){
        Player activePlayer = game.getActivePlayer();
        activePlayer.updateCoins(this.getValue());
        System.out.println(this.getValue()+" coin(s) added to "+activePlayer.getName());
    }
    public Card clone(){
        return new Copper();
    }
    
}
