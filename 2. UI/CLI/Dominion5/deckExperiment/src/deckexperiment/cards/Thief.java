package deckexperiment.cards;

import deckexperiment.*;
import deckexperiment.Player;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author StefVanremoortele
 */
public class Thief extends ActionCard {

    public Thief() {
        super("thief", 4);
    }

    public void play(GameController game) {
        //Each other player reveals the top 2 cards of his deck.
        //If they revealed any TreasureCard, they trash one of them that you choose.
        //You may gain any or all of these trashed cards.
        //They discard the other revealedCards
        Supply supply = game.getSupply();
        Player activePlayer = game.getActivePlayer();
        Scanner sc = new Scanner(System.in);

        ArrayList<Card> revealedCards = new ArrayList<Card>();
        ArrayList<Card> treasureCards = new ArrayList<Card>();

        for (Player target : game.getPlayers()) {
            revealedCards.add(target.getDrawPile().getCard(1));
            revealedCards.add(target.getDrawPile().getCard(1));
            boolean treasureRevealed = false;
            for (Card card : revealedCards) {
                if (card instanceof TreasureCard) {
                    treasureRevealed = true;
                }
            }
            if (treasureRevealed) {
                System.out.println("Choose a card to trash");
                String res = "";
                int i = 1;
                for (Card card : revealedCards) {

                    res += i + ": " + card.getName();
                    i++;
                }
            }
            boolean cardGained = false;
            while (!cardGained) {
                Card cardToTrash = null;
                String input = sc.nextLine();
                try {
                    int selectedCard = Integer.parseInt(input);
                    if (selectedCard == 1 || selectedCard == 2) {
                        cardToTrash = revealedCards.get(selectedCard-1);
                        revealedCards.remove(cardToTrash);
                        game.trashCard(cardToTrash);
                    }
                    
                } catch (Exception e) {
                    System.out.println(e);
                }

                for (Card card : revealedCards) {
                    target.discard(card);
                }
            }
        }
    }

    public Card clone() {
        return new Thief();
    }
}
