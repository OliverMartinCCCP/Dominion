package cards;
import java.util.Scanner;

import dominion.*;
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
            
            int cardsToDraw = 0;
            boolean endCellar = false;
            activePlayer.updateActions(+1);
            
            while (!endCellar){
                System.out.println("choose a card to discard. +1 card for each card discarded.");
                String input = sc.nextLine();
                try{
                    int selectedCard = Integer.parseInt(input);
                    Card cardToDiscard = activePlayer.giveCardFromHand(selectedCard);
                    activePlayer.discard(cardToDiscard);
                    cardsToDraw++;
                } catch (Exception e){
                    if ( input.toLowerCase().equals("stop")) endCellar = true;
                }
                if (activePlayer.getHand().isEmpty()) endCellar = true;
            }
            activePlayer.drawCard(cardsToDraw);
    }
    @Override
    public Card clone(){
        return new Cellar();
    }
}
