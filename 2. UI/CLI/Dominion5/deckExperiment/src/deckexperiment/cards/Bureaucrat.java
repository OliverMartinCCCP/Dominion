package deckexperiment.cards;

import deckexperiment.*;
import java.util.Scanner;

/**
 *
 * @author StefVanremoortele
 */
public class Bureaucrat extends ActionCard {

    private Scanner sc;

    public Bureaucrat() {
        super("bureaucrat", 4);
    }

    @Override
    public void play(GameController game) {
        //Gain a silver card, put it on top of your deck
        //Each other playe rreveals a victory card from his hand and puts it on his deck
        //(or reveals a hand with no victory cards)
        Player activePlayer = game.getActivePlayer();
        Supply supply = game.getSupply();

        Card gainedCard = supply.gainCard("silver");
        activePlayer.getDrawPile().putCardOnTop(gainedCard);
        
        
        for (Player target : game.getPlayers()) {
            boolean cardRevealed = false;
            
            //Execute only from other players (not activeplayer)
            if (!target.equals(activePlayer)) {
                //checks if player has victory cards
                if (target.hasVictoryCardInHand()) {
                    String input = sc.nextLine();
                    while (!cardRevealed) {
                        try {
                            //Checks for valid input (and reveals)
                            int selectedCard = Integer.parseInt(input);
                            Card cardToReveal = activePlayer.getHand().showCard(selectedCard);
                            if (cardToReveal instanceof VictoryCard){
                                activePlayer.revealCard(cardToReveal);
                                cardRevealed = true;
                            }
                        } catch (Exception e) {
                            System.err.println("please choose a victory card from your hand to reveal");
                        }
                    }
                //if no victory cards in hand, then show hand
                } else {
                    target.revealHand();
                }
            }
        }
    }
    @Override
    public Card clone() {
        return new Bureaucrat();
    }
}
