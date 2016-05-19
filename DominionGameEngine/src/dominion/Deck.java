package dominion;

import java.util.*;
public class Deck
{
	private ArrayList<String> availableCardList;
    private ArrayList<Card> copper = new ArrayList<Card>();
    private ArrayList<Card> silver = new ArrayList<Card>();
    private ArrayList<Card> gold = new ArrayList<Card>();
    
    private ArrayList<Card> estate = new ArrayList<Card>();
    private ArrayList<Card> duchy = new ArrayList<Card>();
    private ArrayList<Card> province = new ArrayList<Card>();

    private HashMap<String, ArrayList<Card>> treasureDeck;
    private HashMap<String, ArrayList<Card>> victoryDeck;
    private HashMap<String, ArrayList<Card>> kingdomDeck;
    private ArrayList<String> availableKingdomCards;
    private ArrayList<String> emptyDeckList;
    private Card c;
    
    private HashMap<String, Map<String, ArrayList<Card>>> cardSupply = new HashMap<String, Map<String, ArrayList<Card>>>();
    
    public Deck(){
    	init();
    }
    public void init()
    {
        treasureDeck = new HashMap<String, ArrayList<Card>>();
        victoryDeck = new HashMap<String, ArrayList<Card>>();
        kingdomDeck = new HashMap<String, ArrayList<Card>>();
        emptyDeckList = new ArrayList<String>();
        availableKingdomCards = new ArrayList<String>();

        
        availableKingdomCards.add("militia");
        availableKingdomCards.add("festival");

        makeDecks();
        
        cardSupply.put("treasurecard", treasureDeck);
        cardSupply.put("victorycard", victoryDeck);
        cardSupply.put("kingdomcard", kingdomDeck);
    }
    public void makeDecks()
    {   
        makeTreasurePile();
        makeVictoryPile();
        addPileToDeck();
    }
    public void addCardsToPile(ArrayList<Card> pile, int amount, Card card)
    {
        for (int i = 0; i < amount; i++)
        {
            pile.add(card);
        }
    }
    public void addPileToDeck()
    {
        treasureDeck.put("copper", copper);
        treasureDeck.put("silver", silver);
        treasureDeck.put("gold", gold);
        
        victoryDeck.put("estate", estate);
        victoryDeck.put("duchy", duchy);
        victoryDeck.put("province", province);
        
    }
    public void makeTreasurePile()
    {
        addCardsToPile(copper, 60, new Copper());
        addCardsToPile(silver, 40, new Silver());
        addCardsToPile(gold, 30, new Gold());
    }
    public void makeVictoryPile()
    {
        addCardsToPile(estate, 8, new Estate());
        addCardsToPile(duchy, 8, new Duchy());
        addCardsToPile(province, 8, new Province());
    }
    
    public int getSizeOfPile(String name)
    {
        int size = 0;
        for(Map.Entry<String, Map<String, ArrayList<Card>>> deck : cardSupply.entrySet()){
            for(Map.Entry<String, ArrayList<Card>> pile : deck.getValue().entrySet()){
                if (pile.getKey().equals(name)){
                    size = pile.getValue().size();
                }
            }
        }
        return size;
    }
        public void testPile(String name)
    {
        for(Map.Entry<String, Map<String, ArrayList<Card>>> deck : cardSupply.entrySet()){
            for(Map.Entry<String, ArrayList<Card>> pile : deck.getValue().entrySet()){
                if (pile.getKey().equals(name)){
                    System.out.println();
                }
            }
        }
        
    }
    public Card gainCard(String name)
    {
        for(Map.Entry<String, Map<String, ArrayList<Card>>> deck : cardSupply.entrySet()){
            for(Map.Entry<String, ArrayList<Card>> pile : deck.getValue().entrySet()){
                if (pile.getKey().equals(name)){
                    c = pile.getValue().get(0);
                    pile.getValue().remove(0);
                }
            }
        }
        return c;
    }
    
    public void vraagKost(String kaartNaam)
    {
        for(Map.Entry<String, Map<String, ArrayList<Card>>> deck : cardSupply.entrySet()){
            for(Map.Entry<String, ArrayList<Card>> pile : deck.getValue().entrySet()){
                if (pile.getKey().equals(kaartNaam)) 
                {
                    System.out.println(pile.getValue());
                   Card c = pile.getValue().get(0);
                   System.out.println(c.getCost());
                }
            }
        }
    }
    
    public void makeAvailableCardList()
    {
     availableCardList = new ArrayList<String>();
        for(Map.Entry<String, Map<String, ArrayList<Card>>> deck : cardSupply.entrySet()){
            for(Map.Entry<String, ArrayList<Card>> pile : deck.getValue().entrySet()){
                availableCardList.add(pile.getKey());
            }
        }   
    }
    public boolean validateCost(int coins, String cardName)
    {
     boolean buyAble = false;
        for(Map.Entry<String, Map<String, ArrayList<Card>>> deck : cardSupply.entrySet()){
            for(Map.Entry<String, ArrayList<Card>> pile : deck.getValue().entrySet()){
                if (pile.getKey().equals(cardName))
                {
                 Card c = pile.getValue().get(0);
                 if (c.getCost() <= coins)
                 {
                  buyAble = true;
                 }
                }
            }
        }   
        return buyAble;
    }
    
    public void printAvailableCards()
    {
	  System.out.println("****************************");
	  System.out.println("Available cards in this game");
	  System.out.println("****************************");
     for (String pileName : availableCardList)
     {

      System.out.println(pileName);
     }
    }


    public void addKingdomPilesToDeck(String pileName, ArrayList<Card> pile)
    {
        kingdomDeck.put(pileName, pile);
    }
    public HashMap getTreasureDeck()
    {
        return treasureDeck;
    }
    public HashMap getVictoryDeck()
    {
        return victoryDeck;
    }
    public HashMap getKingdomDeck()
    {
        return kingdomDeck;
    }
    //***GETTERS***
    public ArrayList<String> getAvailableKingdomCards()
    {
        return availableKingdomCards;
    }
    public ArrayList<String> getEmptyDeckList()
    {
        return emptyDeckList;
    }
    public int getAmountOfEmptyDecks()
    {
        return emptyDeckList.size();
    }
    public HashMap getCardSupply()
    {
        return cardSupply;
    }
    public boolean isPileEmpty(String pileName)
    {
        boolean emptyDeck = false;
        for (String emptyPile : emptyDeckList)
        {
            if (emptyPile.equals(pileName))
            {
                emptyDeck = true;
            }
        }
        return emptyDeck;
    }

}