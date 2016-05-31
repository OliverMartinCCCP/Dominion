package cards;

import java.util.ArrayList;

import dominion.*;

/**
 *
 * @author StefVanremoortele
 */
public class Thief extends ActionCard {

    private Player activePlayer;
    private Player target;
    private ArrayList<Card> revealedCards;
    private Supply supply;

    public Thief() {
        super("thief", 4);
    }

    public void play(GameController game) {
        //Each other player reveals the top 2 cards of his deck.
        //If they revealed any TreasureCard, they trash one of them that you choose.
        //You may gain any or all of these trashed cards.
        //They discard the other revealedCards
        this.supply = game.getSupply();
        this.activePlayer = game.getActivePlayer();
        revealedCards = new ArrayList<Card>();
        
        
        for (Player target : game.getPlayers()) {
            boolean targetHasRevealed = false;
            if (!target.equals(activePlayer)) {
                while (!targetHasRevealed){
                    target.revealCard(target.getDrawPile().getCard(1));
                  /*  if (target.getDrawPile().getFirstCard() instanceof TreasureCard){
                        
                    } */
                }
            } // revealCards method necessary?
            revealedCards.add(target.giveCardFromHand(1));
            revealedCards.add(target.giveCardFromHand(1));
            for (Card card : revealedCards){
                activePlayer.revealCard(card);
            }
        }
        //System.out.println(revealedCards)     //INPUT
        
        boolean treasureRevealed = false;
        for (Card c : revealedCards){
            if (c.getType().equals("treasure")) treasureRevealed = true;
        }
        for (Card card : revealedCards){
            boolean answer = false;         // INPUT
            if (card.getType().equals("treasure")){
                //Choose to gain card, yes/no
                if (answer){
                    answer = true;      // INPUT
                    activePlayer.discard(card);
                    revealedCards.remove(card);
                }
            }
        }
        revealedCards.clear();
    }
    public Card clone(){
        return new Thief();
    }
}
