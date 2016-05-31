package cards;

import dominion.*;
/**
 *
 * @author StefVanremoortele
 */
public class Witch extends ActionCard{
    private Player activePlayer;
    private Supply supply;
    public Witch(){
        super("witch", 5);
    }
    public void play(GameController game){
        //+2 cards
        //Each other player gains curse card
        supply = game.getSupply();
        this.activePlayer = game.getActivePlayer();
        activePlayer.drawCard(2);
        for (Player target : game.getPlayers()){
            if ( (!target.equals(activePlayer)) && (!supply.isPileEmpty("curse")) ){
                    Card cardtoGain = supply.showCard("curse");
                    supply.gainCard(target, cardtoGain);
                }
        }
        
    }
    public Card clone(){
        return new Witch();
    }
}
