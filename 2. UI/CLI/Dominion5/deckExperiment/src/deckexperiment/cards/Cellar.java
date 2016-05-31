package deckexperiment.cards;
import deckexperiment.*;
import java.util.Scanner;
/**
 *
 * @author StefVanremoortele
 */
public  class Cellar extends ActionCard{
    private Scanner sc;
    public Cellar(){
        super("cellar", 2);
    }
    @Override
    public void play(GameController game){
            //+1 action
            //Discard any number of cards
            //+1 cards per card discarded
            
            Player activePlayer = game.getActivePlayer();
            sc = new Scanner(System.in);
            
            activePlayer.updateActions(+1);
            
            int cardsToDraw = 0;
            boolean endCellar = false;
            while (!endCellar){
                System.out.println("choose a card to discard. +1 card for each card discarded.");
                activePlayer.printHand();
                String input = sc.nextLine();
                try{
                    int selectedCard = Integer.parseInt(input);
                    Card cardToDiscard = activePlayer.getCardFromHand(selectedCard);
                    activePlayer.discard(cardToDiscard);
                    cardsToDraw++;
                } catch (Exception e){
                    if ( input.toLowerCase().equals("stop")) endCellar = true;
                }
                if (activePlayer.getHand().isEmpty()) endCellar = true;
            }
            System.out.println(cardsToDraw);
            activePlayer.drawCards(cardsToDraw);
    }
    @Override
    public Card clone(){
        return new Cellar();
    }
}
