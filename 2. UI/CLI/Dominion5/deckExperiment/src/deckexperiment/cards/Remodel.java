package deckexperiment.cards;
import deckexperiment.GameController;
import deckexperiment.GameState;
import deckexperiment.Player;
import java.util.Scanner;
import java.util.ArrayList;
import deckexperiment.Supply;

/**
 *
 * @author StefVanremoortele
 */
public class Remodel extends ActionCard{
    private Player activePlayer;
    public Remodel(){
            super("remodel", 4);
    }
    public void play(GameController game){
        Scanner sc = new Scanner(System.in);
        Supply supply = game.getSupply();
        ArrayList<Card> availableCardsToGain = new ArrayList<Card>();
        // Trash a card from your hand.
        // Gain a card costing up to 2 more than the trashed card.
        boolean cardTrashed = false;
        int maxCost = 0;
        while (!cardTrashed) {
            System.out.println("Choose a card to trash");
            String input = sc.nextLine();
            try {
                int selectedCard = Integer.parseInt(input);
                Card cardToTrash = activePlayer.showCardFromHand(selectedCard);
                if ( cardToTrash != null ){
                    game.trashCardFrom(activePlayer, activePlayer.getHand(), selectedCard);
                    cardTrashed = true;
                    maxCost = cardToTrash.getCost()+2;
                }
            } catch (Exception e){
                System.out.println(e);
            }
        }
        availableCardsToGain = supply.getAllCardsWithMaxCost(maxCost);
        boolean cardGained = false;
        while (!cardGained){
            System.out.println("Choose a card to gain");
            for (Card card : availableCardsToGain){
                System.out.println(card.getName()+"\t");
            }
            String input = sc.nextLine();
            try{
                Integer.parseInt(input);
            } catch(Exception e){
                if ( availableCardsToGain.contains(supply.showCard(input)) ){
                    activePlayer.discard(supply.gainCard(input));
                    cardGained = true;
                }
            }
        }
    }
    
    public Card clone(){
        return new Remodel();
    }
}
