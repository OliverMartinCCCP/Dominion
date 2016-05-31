package deckexperiment.cards;

import deckexperiment.GameController;
import deckexperiment.*;
import java.util.Scanner;

/**
 *
 * @author StefVanremoortele
 */
public class MoneyLender extends ActionCard {

    private Player activePlayer;

    public MoneyLender() {
        super("moneylender", 4);
    }

    public void play(GameController game) {
        // Trash a copper from your hand
        // if you do: +3 coins
        Scanner sc = new Scanner(System.in);
        boolean copperTrashed = false;
        while (!copperTrashed) {
            System.out.println("choose a copper card to trash");
            String input = sc.nextLine();
            try {
                int selectedCard = Integer.parseInt(input);
                if (activePlayer.showCardFromHand(selectedCard) instanceof TreasureCard) {
                    game.trashCardFrom(activePlayer, activePlayer.getHand(), selectedCard);
                    copperTrashed = true;
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        activePlayer.updateCoins(+3);
    }

    public Card clone() {
        return new MoneyLender();
    }

}
