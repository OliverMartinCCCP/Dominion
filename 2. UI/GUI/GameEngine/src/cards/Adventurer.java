package cards;
import java.util.ArrayList;

import dominion.*;
/**
 *
 * @author StefVanremoortele
 */
public class Adventurer extends ActionCard{
    private ArrayList<Card> revealedCards;
    private ArrayList<Card> revealedTreasures;
    public Adventurer(){
        super("adventurer", 6);
    }
    public void play(GameController game){
            //Reveal cards from your deck until you reveal 2 treasureCards
            //Put those treasureCards in your hand and discard the other revealed cards
        revealedCards = new ArrayList<Card>();
        revealedTreasures = new ArrayList<Card>();
        Player activePlayer = game.getActivePlayer();
        
        while (revealedTreasures.size() < 2){
            Card card = activePlayer.giveCardFrom(activePlayer.getDrawPile(), 1);
            if (card.getType().equals("treasure")){
                revealedTreasures.add(card);
            } else {
                revealedCards.add(card);
            }
        }
        revealedTreasures.clear();
        revealedCards.clear();
    }
    public Card clone(){
        return new Adventurer();
    }
}
