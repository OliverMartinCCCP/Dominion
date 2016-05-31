package deckexperiment.cards;

import deckexperiment.GameController;
import deckexperiment.GameState;
import deckexperiment.Player;
import java.util.Scanner;

/**
 *
 * @author StefVanremoortele
 */
public class Militia extends ActionCard {

    public Militia() {
        super("militia", 4);
    }

    public void play(GameController game) {
        //+2 coins
        //Each other player discards down to 3 cards in his hand
        Scanner sc = new Scanner(System.in);
        Player activePlayer = game.getActivePlayer();

        activePlayer.updateCoins(+2);
        for (Player target : game.getPlayers()) {
            //TODO: player.discard( ) fix
            while (target.getHandSize() > 3) {
                System.out.println(activePlayer.getName() + ": choose a card to discard");
                target.printHand();
                String input = sc.nextLine();
                try {
                    int selectedCard = Integer.parseInt(input);
                    target.discard(target.getCardFromHand(selectedCard));
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }
    }

    public Card clone() {
        return new Militia();
    }
}
