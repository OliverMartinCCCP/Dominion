package cards;

import java.util.ArrayList;

import dominion.GameController;
import dominion.GameState;
import dominion.Player;

/**
 *
 * @author StefVanremoortele
 */
public class ThroneRoom extends ActionCard {

    private Player activePlayer;
    private Player target;
    private ArrayList<Card> revealedCards;

    public ThroneRoom() {
        super("throneroom", 4);
    }

    public void play(GameController game) {
        Player activePlayer = game.getActivePlayer();
        //Choose an action card from your hand, play it twice
        
        // INPUT
        System.out.println("Choos an action card from your hand. Play it twice.");
        int selectedCard = 1;//TODO: input check;
        Card card = activePlayer.giveCardFromHand(1);
        //TODO: check game rules
        
    }
    public Card clone(){
        return new ThroneRoom();
    }
}

