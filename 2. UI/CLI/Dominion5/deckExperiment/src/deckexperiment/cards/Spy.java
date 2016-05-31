package deckexperiment.cards;

import deckexperiment.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author StefVanremoortele
 */
public class Spy extends ActionCard {

    private Player activePlayer;
    private Player target;
    private ArrayList<Card> revealedCards;

    public Spy() {
        super("spy", 4);
    }

    public void play(GameController game) {
        //+1 card, +1 action
        //Each player (including you) reveals the top card of his deck
        //and either discards it or puts it back, your choise
        Scanner sc = new Scanner(System.in);
        Supply supply = game.getSupply();
        Player activePlayer = game.getActivePlayer();
        ArrayList<Card> revealedCards = new ArrayList<Card>();
        
        activePlayer.drawCards(1);
        activePlayer.updateActions(+1);
        
        for (Player target : game.getPlayers()){
            Card revealedCard = target.getDrawPile().showCard(1);
            System.out.println( target.getName()+" reveals "+revealedCard.getName() );
            boolean decided = false;
            while (!decided){
                System.out.println("1: discard");
                System.out.println("2: put back");
                String input = sc.nextLine();
                try{
                    int answer = Integer.parseInt(input);
                    if ( answer == 1 ){
                        target.discard(revealedCard);
                        decided = true;
                    } 
                    if ( answer == 2) decided = true;
                        
                }catch (Exception e){
                    System.err.println(e);
                }
            }
        }
    }
    public Card clone(){
        return new Spy();
    }
}

