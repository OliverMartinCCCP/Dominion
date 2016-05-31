package deckexperiment.cards;
import deckexperiment.*;
import java.util.ArrayList;
/**
 *
 * @author StefVanremoortele
 */
public class Adventurer extends ActionCard{
    public Adventurer(){
        super("adventurer", 6);
    }
    public void play(GameController game){
            //Reveal cards from your deck until you reveal 2 treasureCards
            //Put those treasureCards in your hand and discard the other revealed cards
        
        Player activePlayer = game.getActivePlayer();
        ArrayList<Card> revealedCards = new ArrayList<Card>();
        ArrayList<Card> revealedTreasures = new ArrayList<Card>();
        
        while (revealedTreasures.size() < 2){
            Card card = activePlayer.getCardFrom(activePlayer.getDrawPile(), 1);
            if (card.getType().equals("treasure")){
                revealedTreasures.add(card);
                System.out.println(card.getName()+" revealed..");
            } else {
                revealedCards.add(card);
                System.out.println(card.getName()+" revealed..");
            }
        }
        for (Card card : revealedCards){
            activePlayer.discard(card);
        }
        for (Card card : revealedTreasures){
            activePlayer.addCardToHand(card);
        }
    }
    
    public Card clone(){
        return new Adventurer();
    }
}
