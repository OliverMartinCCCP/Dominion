package deckexperiment.cards;
import deckexperiment.GameController;
import deckexperiment.GameState;
import deckexperiment.Player;
import java.util.ArrayList;
/**
 *
 * @author StefVanremoortele
 */
public class Library extends ActionCard{

    public Library(){
        super("library", 5);
    }
    public void play(GameController game){
        // Draw until you have 7 cards in hand. 
        // You may set aside any Action cards drawn this way, as you draw them
        // Discard the set aside cards after you finish drawing
        ArrayList<Card> cardsAside = new ArrayList<Card>();
        Player activePlayer = game.getActivePlayer();
        
        while (activePlayer.getHandSize() < 7){
            
            Card lastDrawnCard = activePlayer.getDrawPile().getCard(1);
            if (lastDrawnCard instanceof ActionCard){
                //Choose to set aside, yes or no
                String input = "yes";
                if (input.toLowerCase().equals("yes")){
                    cardsAside.add(lastDrawnCard);
                    System.out.println(activePlayer.getName()+" puts "+lastDrawnCard.getName()+" aside");
                }
            } else {
                activePlayer.getHand().addCard(lastDrawnCard);
            }
        }
        for (Card card : cardsAside){
            activePlayer.addCardToHand(card);
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
