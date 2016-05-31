package dominion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.Set;

import cards.*;

/**
 *
 * @author StefVanremoortele
 */
public class Supply {

    private HashMap<Card, Integer> piles; // INTEGER VALUE == HOW MANY CARDS IN PILE
    private static String[] kingdomList = new String[]{"adventurer", "bureaucrat", "cellar", "chapel", "woodcutter", "village", "chancellor", "feast", "militia", "moneylender", "remodel", "thief", "spy", "smithy", "throneroom", "councilroom", "festival", "laboratory", "library", "market", "mine", "witch", "moat", "workshop"};
    private static String[] victoryList = new String[]{"estate", "duchy", "province"};
    private static String[] treasureList = new String[]{"copper", "silver", "gold"};
    private static int numberOfPlayers;

    public Supply() {
        this.piles = new HashMap<Card, Integer>();
        makeSupply(2);
    }

    public void makeSupply(int numberOfPlayers) {
        makeTreasurePiles(numberOfPlayers);
        makeVictoryPiles(numberOfPlayers);
        makeKingdomPiles(numberOfPlayers);
    }

    public void makeTreasurePiles(int numberOfPlayers) {
        addPileToSupply(treasureList[2], 60);
        addPileToSupply(treasureList[1], 30);
        addPileToSupply(treasureList[0], 20);
    }

    public void makeVictoryPiles(int numberOfPlayers) {
        if (numberOfPlayers == 2) {
            for (String cardName : victoryList) {
                addPileToSupply(cardName, 8); 
            }
            addPileToSupply("curse", 10);
        } else {
            for (String cardName : victoryList) {
                addPileToSupply(cardName, 12);
            }
        }
    }

    public void makeKingdomPiles(int numberOfPlayers) {
    	shuffle();
        if (numberOfPlayers == 2) {
            for (int i = 0; i < 10; i++) {
                addPileToSupply(kingdomList[i], 10);
            }
        } else {
            for (int i = 0; i < 10; i++) {
                addPileToSupply(kingdomList[i], 12);
            }
        }
    }
    
    public void shuffle(){
    	int i = 0, j = 0 ;
    	String temp;
    			for (i = kingdomList.length - 1; i > 0; i--) {
    			    j = (int)(Math.random() * (i + 1));
    			    temp = kingdomList[i];
    		    	kingdomList[i] = kingdomList[j];
    		    	kingdomList[j] = temp;
    		  }
    }

    //Makes Cards and puts in HashMap<Card, Integer> (Integer value = cards left in pile) 
    public void addPileToSupply(String cardName, int amountOfCards) {
        Card pile = null;
        switch (cardName) {
        	case "curse":
        		pile = new Curse();
        		break;
            case "copper":
                pile = new Copper();
                break;
            case "silver":
                pile = new Silver();
                break;
            case "gold":
                pile = new Gold();
                break;
            case "estate":
                pile = new Estate();
                break;
            case "duchy":
                pile = new Duchy();
                break;
            case "province":
                pile = new Province();
                break;
            case "adventurer":
                pile = new Adventurer();
                break;
            case "bureaucrat":
                pile = new Bureaucrat();
                break;
            case "cellar":
                pile = new Cellar();
                break;
            case "chapel":
                pile = new Chapel();
                break;
            case "woodcutter":
                pile = new WoodCutter();
                break;
            case "village":
                pile = new Village();
                break;
            case "chancellor":
                pile = new Chancellor();
                break;
            case "feast":
                pile = new Feast();
                break;
            case "moneylender":
                pile = new MoneyLender();
                break;
            case "remodel":
                pile = new Remodel();
                break;
            case "thief":
            	pile = new Thief();
                break;
            case "smithy":
                pile = new Smithy();
                break;
            case "throneroom":
                pile = new ThroneRoom();
                break;
            case "councilroom":
                pile = new CouncilRoom();
                break;
            case "festival":
                pile = new Festival();
                break;
            case "laboratory":
                pile = new Laboratory();
                break;
            case "library":
                pile = new Library();
                break;
            case "market":
                pile = new Market();
                break;
            case "mine":
                pile = new Mine();
                break;
            case "witch":
                pile = new Witch();
                break;
            case "moat":
                pile = new Moat();
                break;
            case "workshop":
                pile = new Workshop();
                break;
            case "spy":
                pile = new Spy();
                break;
            case "militia":
                pile = new Militia();
        }
        if (pile != null) {
            piles.put(pile, amountOfCards);
        } else {
            System.out.println(cardName + "This card is not available");  // ConsoleGame
        }
    }

    //Returns true or false if card with the same name is found in HashMap piles
    public boolean contains(String cardName) {
        boolean containsPile = false;
        for (Card card : piles.keySet()) {
            if (card.getName().equals(cardName) && piles.get(card) > 0) {
                containsPile = true;
            }
        }
        return containsPile;
    }

    public Integer getCost(String cardName) { //Returns null of card doesn't exist
        Integer cost = null;
        for (Card card : piles.keySet()) {
            if (card.getName().equals(cardName)) {
                cost = card.getCost();
            }
        }
        return cost;
    }

    // returns the value of hashmap<Card, Integer> 
    public Integer getSize(String cardName) {
        Integer size = null;
        for (Map.Entry<Card, Integer> pile : piles.entrySet()) {
            if (pile.getKey().getName().equals(cardName)) {
                size = pile.getValue();
            }
        }
        return size;
    }

    //Check if pile has cards left | Based on Integer Value of HashMap pile<Card, Integer>
    public boolean isPileEmpty(String pileName) {
        boolean pileIsEmpty = true;
        for (Map.Entry<Card, Integer> pile : piles.entrySet()) {
            if (pile.getKey().getName().equals(pileName) && pile.getValue() > 0) {
                pileIsEmpty = false;
            }
        }
        return pileIsEmpty;
    }

    public ArrayList getEmptyPiles() {
        ArrayList<Card> allEmptyPiles = new ArrayList<Card>();
        for (Card card : piles.keySet()) {
            if (isPileEmpty(card.getName())) {
                allEmptyPiles.add(card);
            }
        }
        return allEmptyPiles;
    }

    public ArrayList getVictoryPile() {
        ArrayList<Card> victoryCards = new ArrayList<Card>();
        for (Card card : piles.keySet()) {
            if (card instanceof VictoryCard) {
                victoryCards.add(card);
            }
        }
        return victoryCards;
    }

    public ArrayList getTreasurePile() {
        ArrayList<Card> treasureCards = new ArrayList<Card>();
        for (Card card : piles.keySet()) {
            if (card instanceof TreasureCard) {
                treasureCards.add(card);
            }
        }
        return treasureCards;
    }

    public ArrayList getActionPile() {
        ArrayList<Card> actionCards = new ArrayList<Card>();
        for (Card card : piles.keySet()) {
            if (card instanceof ActionCard) {
                actionCards.add(card);
            }
        }
        return actionCards;
    }

    public ArrayList getAvailablePiles() {
        ArrayList<Card> availablePiles = new ArrayList<Card>();

        for (Card card : piles.keySet()) {
            if (!isPileEmpty(card.getName())) {
                availablePiles.add((Card) card);
            }
        }
        return availablePiles;
    }

    public ArrayList getAllCardsWithMaxCost(int maxCost) {
        ArrayList<Card> availableCards = new ArrayList<Card>();
        for (Card card : piles.keySet()) {
            if (card.getCost() <= maxCost && (!isPileEmpty(card.getName()))) {
                availableCards.add(card);
            }
        }
        return availableCards;
    }

    //Returns null if card not found
    public Card showCard(String cardName) {
        Card cardToCheck = null;
        for (Card card : piles.keySet()) {
            if (card.getName().equals(cardName)) {
                cardToCheck = card;
            }
        }
        return cardToCheck;
    }
    // Clones the key object ( returns new Card() as long as Integer value > 0 ( = pile empty) )
    //if no card found, Null returned

    public Card gainCard(String cardToBuy) {
        Card gainedCard = null;
        for (Map.Entry<Card, Integer> pile : piles.entrySet()) {
            if (pile.getKey().getName().equals(cardToBuy) && (pile.getValue() > 0)) {
                gainedCard = pile.getKey().clone();
                pile.setValue(pile.getValue() - 1);
                System.out.println(gainedCard.getName() + " sold..");
            }
        }
        if (gainedCard == null) {
            System.err.println("card not found in supply");
        }
        if (isPileEmpty(gainedCard.getName())) System.out.println(gainedCard.getName()+" pile is now empty...");
        //Discards the bought card to hand
        return gainedCard;
    }

    //Decrements hashmap value (# cards left in pile)
    public void gainCard(Player player, Card card) {

        try {
            if (piles.containsKey(card) && piles.get(card) > 0) {
                player.discard(card);
                piles.put(card, piles.get(card) - 1);
            }
        } catch (Exception e){
            System.err.println("Error gaining card");
        }
    }

    public boolean canPlayerBuyCard(Player player, String pile) {
        return (!(isPileEmpty(pile)) && (getCost(pile) <= player.getCoins()));
    }

}
