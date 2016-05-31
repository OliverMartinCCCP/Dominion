package cards;
import java.util.ArrayList;

import dominion.GameController;
import dominion.GameState;
import dominion.Player;
/**
 *
 * @author StefVanremoortele
 */
public class Library extends ActionCard{
    private ArrayList<Card> cardsAside;
    public Library(){
        super("library", 5);
    }
    public void play(GameController game){
        // Draw until you have 7 cards in hand. 
        // You may set aside any Action cards drawn this way, as you draw them
        // Discard the set aside cards after you finish drawing
        cardsAside = new ArrayList<Card>();
        Player activePlayer = game.getActivePlayer();
        
        while (activePlayer.getHandSize() > 7){
            int cardPosition = 1;
            Card card = activePlayer.giveCardFromHand(cardPosition);
            activePlayer.revealCard(card);
            boolean setAside = false;
            if (card.getType().equals("action")){
                setAside = chooseToSetAside();
            }
            if (setAside){
                cardsAside.add(activePlayer.giveCardFrom(activePlayer.getDrawPile(), 1));
            } else {
                
            }
            cardPosition++;
        }
        
    }
    public Card clone(){
        return new Library();
    }
    public boolean chooseToSetAside(){
        //check for valid input
        boolean answer = true; // input
        return answer;
    }
}
