package deckexperiment.cards;
import deckexperiment.GameController;
import deckexperiment.Player;
import deckexperiment.*;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author StefVanremoortele
 */
public class Workshop extends ActionCard{
    private Player activePlayer;
    public Workshop(){
        super("workshop", 3);
    }
    public void play(GameController game){
        //Gain a card costing up to 4 coins
        Scanner sc = new Scanner(System.in);        
        Supply supply = game.getSupply();
        Player activePlayer = game.getActivePlayer();
        
        boolean cardGained = false;
        ArrayList<Card> availableCards = supply.getAllCardsWithMaxCost(4);
        while (!cardGained){
            System.out.println("Cards to gain:");
            System.out.println(availableCards);
            System.out.println("Choose a card to gain: ");
            String input = sc.nextLine();
            Card cardToGain = supply.showCard(input);
            if (cardToGain != null && availableCards.contains(cardToGain)){
                supply.gainCard(activePlayer, cardToGain);
                System.out.println(cardToGain.getName()+" gained");
                cardGained = true;
            }
        }
        
    }
    public Card clone(){
        return new Workshop();
    }
}
