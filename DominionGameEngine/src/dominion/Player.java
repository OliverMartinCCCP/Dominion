package dominion;

import java.util.ArrayList;
import java.util.Collections;
public class Player {

	private String playerName;
	private ArrayList<Card> drawPile;
    private ArrayList<Card> discardPile;
    private ArrayList<Card> cardsInHand;
    private ArrayList<Card> playingCards;
    private ArrayList<Card> playedCards;
    private int remainingActions;
    private int remainingBuys;
	private int remainingCoins;
    
	public Player(String name)
    {
        this.playerName = name;
        initPlayer();
    }
	
	public void initPlayer(){
		playedCards = new ArrayList<Card>();
        drawPile = new ArrayList<Card>();
        cardsInHand = new ArrayList<Card>();
        discardPile = new ArrayList<Card>();
        playingCards = new ArrayList<Card>();
        resetValues();
        giveStartCards();
        drawHand();
	}
	public void resetValues(){
		remainingActions = 1;
		remainingCoins = 0;
		remainingBuys = 1;
	}
	public void giveStartCards(){
		for(int i = 0; i<3;i++){
			drawPile.add(new Estate());
		}
		for(int i = 0; i<7;i++){
			drawPile.add(new Copper());
		}
		Collections.shuffle(drawPile);
	}
	public void drawHand(){
		for(int i = 0;i<5;i++){
			drawCard(i);
		}
	}
	public void drawCard(int i){
		if (drawPile.size()== 0){
			handleEmptyDeck();
		}
		Card card = drawPile.get(0);
		drawPile.remove(0);
		cardsInHand.add(card);
		
	}
	public void handleEmptyDeck(){
		Collections.shuffle(discardPile);
		while (!discardPile.isEmpty()){
			Card c = discardPile.get(0);
			drawPile.add(c);
			discardPile.remove(0);
		}
	}
	public void showHand(){
        for (int i = 0; i < cardsInHand.size(); i++)
        {
            System.out.println((i+1)+" "+cardsInHand.get(i).getName()+"\t\t cost: "+cardsInHand.get(i).getCost()+"\t\t value: "+cardsInHand.get(i).getValue());
        }
    }
	public void discardHand(){
		for (Card card : cardsInHand){
			discardPile.add(card);
		}
		cardsInHand.clear();
	}
	
	public void playTreasureCards(){
		int grootte = cardsInHand.size();
		for(int i = grootte-1; i >= 0 ;i--){
			Card card = cardsInHand.get(i);
			if(card.getType().equals("treasure")){
				cardsInHand.remove(i);
				addCoins(card.getValue());
			}
		}
		
	}
	
	public String getPlayerName(){
		return playerName;
	}
	
	public boolean hasActionCard(){
		boolean hasActionCard = false;
		for(Card card : cardsInHand){
			if(card.getType().equals("kingdom")){
				hasActionCard = true;
			}
		}
		return hasActionCard;
	}
	
	public ArrayList<Card> getCardsInHand(){
		return cardsInHand;
	}
	
	public int getRemainingBuys(){
		return remainingBuys;
	}
	public int getRemainingActions(){
		return remainingActions;
	}
	public int getRemainingCoins(){
		return remainingCoins;
	}
	
	public void addCoins(int amount){
		remainingCoins += amount;
	}    public void addActions(int amount)
    {
        remainingActions += amount;
    }
    public void addBuys(int amount)
    {
        remainingBuys += amount;
    }
	
	
}
