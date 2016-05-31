package deckexperiment.cards;

import deckexperiment.GameController;
import deckexperiment.*;
import java.util.Scanner;
/**
 *
 * @author StefVanremoortele
 */
public class Feast extends ActionCard{
    
    public Feast(){
        super("feast", 4);
    }
    public void play(GameController game){
        Scanner sc = new Scanner(System.in);
        //trash this card
        //Gain a card up to 5 coins
        Player activePlayer = game.getActivePlayer();
        Deck cardsInPlay = activePlayer.getCardsInPlay();
        Supply supply = game.getSupply();
        game.trashCardFrom(activePlayer, cardsInPlay, cardsInPlay.getCardPosition(this));
        
        boolean cardGained = false;
        while (!cardGained){
            System.out.println("Choose a card to gain");
            for (Card card : supply.getAllCardsWithMaxCost(5)){
                System.out.println(card.getName()+"\t");
            }
            
            String input = sc.nextLine();
            Card gainedCard = supply.gainCard(input);
            if (gainedCard != null){
                activePlayer.discard(gainedCard);
                cardGained = true;
            } 
        }
    }
    public Card clone(){
        return new Feast();
    }
}
