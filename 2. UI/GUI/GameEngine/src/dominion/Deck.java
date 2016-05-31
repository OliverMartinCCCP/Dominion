package dominion;

import java.util.ArrayList;
import java.util.Collections;

import cards.*;
/**
 *
 * @author StefVanremoortele
 */
public class Deck {

    private ArrayList<Card> cards;
    public Deck() {
        cards = new ArrayList<Card>();
    }
    public void putCardOnTop(Card card){
        cards.add(0, card);
    }
    public void addCard(Card cardToAdd) {
        if (cardToAdd == null){
            System.err.println("null card found. Card not added");
        } else {
            cards.add(cardToAdd);
        }
    }
    //prints exception when out of bounds
    public void removeCard(int position) {
        try {
            cards.remove(position - 1);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    //TODO: check if neccessary
    public void removeCard(Card c) {
        try {
            cards.remove(c);
        } catch (Exception e) {
            System.err.println("Error removing card: Card not found");
        }
    }
    public void shuffle() {
        Collections.shuffle(cards);
    }
    public void clear() {
        cards.clear();
    }
    public boolean isEmpty() {
        return cards.isEmpty();
    }
    //returns true or false if card with specified type is found
    public boolean containsCardOfType(String type) {
        boolean containsType = false;
        for ( Card card : cards) {
            if (card.getType().equals(type)) {
                containsType = true;
            }
        }
        return containsType;
    }
    public ArrayList<Card> getCardsOftType(String type) {
        ArrayList<Card> temp = new ArrayList<Card>();
        for( Card card : cards ) {
            if (card.getType().equals(type)) {
                temp.add(card);
            }
        }
        return temp;
    }
    public int getSize() {
        return cards.size();
    }
    //IF CARD NOT FOUND => NULL returned ( need fix ) 
    public Card getCard(int position) {
        Card card = null;
        if (!cards.isEmpty()) card = cards.get(position - 1);
        //System.out.println(card+" taken from "+this);
        return card;
    }
    //Does not remove the card
    //IF CARD NOT FOUND => NULL returned ( need fix ) 
    public Card showCard(int position) {
        Card card = null;
        if ((position-1) <= cards.size()) {
            card = cards.get(position - 1);
        }
        return card;
    }
    //Calculates the total value of the deck ( incl gardens value )
    public int getTotalValue() { 
        int totalValue = 0;
        for (Card c : cards) {
            int cardValue = c.getValue();
            if (c.getName().equals("gardens")) cardValue = cards.size()%10;
            totalValue += cardValue;
        }
        return totalValue;
    }
    public int getTotalCost() {
        int totalCost = 0;
        for (Card c : cards) {
            totalCost += c.getCost();
        }
        return totalCost;
    }
    public void printCards() {
        if (!cards.isEmpty()) {
            for (Card c : cards) {
                System.out.println(c);
            }
        } else {
            System.out.println("deck is empty");
        }
    }
    public ArrayList getAllCards(){
        ArrayList<Card> temp = new ArrayList<Card>();
        for (Card card : cards){
           temp.add(card);
        }
        return temp;
    }
    public void addCard(ArrayList<Card> cardsToAdd){
        for (Card card : cardsToAdd) cards.add(card);
        
    }
}
