package deckexperiment.cards;

import deckexperiment.*;
import deckexperiment.Player;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author StefVanremoortele
 */
public class Mine extends ActionCard {

    public Mine() {
        super("mine", 5);
    }

    public void play(GameController game) {
        //Trash a treasureCard from your hand
        //Gain a treasureCard costing up to 3coins more
        //Put it into your hand
        Player activePlayer = game.getActivePlayer();
        Supply supply = game.getSupply();
        Scanner sc = new Scanner(System.in);
        int maxCost = 0;
        boolean treasureTrashed = false;
        while (!treasureTrashed) {
            System.out.println("Choose a treasure card to trash");
            String input = sc.nextLine();
            try {
                int selectedCard = Integer.parseInt(input);
                Card cardToTrash = activePlayer.showCardFromHand(selectedCard);
                if (cardToTrash instanceof TreasureCard) {
                    maxCost = cardToTrash.getCost();
                    game.trashCardFrom(activePlayer, activePlayer.getHand(), selectedCard);
                    treasureTrashed = true;
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        boolean cardGained = false;
        while (!cardGained) {
            ArrayList<Card> availableCardsToGain = supply.getAllCardsWithMaxCost(maxCost);
            System.out.println("available cards to gain: ");
            for (Card card : availableCardsToGain) {
                if (card instanceof TreasureCard){
                    System.out.println(card.getName() + "\t");
                } else {
                    availableCardsToGain.remove(card);
                }
            }
            String input = sc.nextLine();
            try{
                int selectedCard = Integer.parseInt(input);
                
                Card card = availableCardsToGain.get(selectedCard-1);
                supply.gainCard(activePlayer, card);
            } catch (Exception e){
                System.err.println(e);
            }
        }
    }

    public Card clone() {
        return new Mine();
    }
}
