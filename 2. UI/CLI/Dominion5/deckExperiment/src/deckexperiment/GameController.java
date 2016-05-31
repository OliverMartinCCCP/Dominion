package deckexperiment;

import java.util.ArrayList;
import java.util.Scanner;
import deckexperiment.cards.*;

/**
 *
 * @author StefVanremoortele
 */
public class GameController {

    private ArrayList<Player> playerList = new ArrayList<Player>();
    private Player activePlayer;
    private Supply supply = new Supply(2);
    private Deck trashPile = new Deck();
    private GameState state;
    private Scanner sc = new Scanner(System.in);
    private GameState finishedState = new FinishedState();
    
    
    public GameController() {
        startGame(); 
        startNewTurn();
    }
    public void startGame() {
        registerPlayers(2);
        activePlayer = playerList.get(0);
        while (state != finishedState) {
            
            startNewTurn();
        }  
    }
    public void startNewTurn() {
        System.out.println("New turn");
        System.out.println("Currently playing: "+activePlayer.getName());
        if ( activePlayer.hasActionCardInHand() && activePlayer.getActions() > 0 ) {
            state = new ActionState();
        }  else {
            state = new BuyState();
        }
        state.run(this); //gameFinish trigger implemented at the end of CleanState        
    }
    public void registerPlayers(int amount) {
        for (int i = 0; i < amount; i++) {
            System.out.println("Enter playername: ");
            playerList.add(new Player(sc.nextLine()));
            makeStartDeck(playerList.get(i));
            playerList.get(i).getDrawPile().shuffle();
            playerList.get(i).drawHand();
        }
    }
    public void nextPlayer(){
        int currentPlayer = (playerList.indexOf(activePlayer));
        this.activePlayer = playerList.get( (currentPlayer+1)%playerList.size() );
    }
    //Adds 7 coppers and 3 estates to discardPile
    public void makeStartDeck(Player player) {
        for (int i = 0; i < 7; i++) {
            player.getDrawPile().addCard(new Copper());
        }
        for (int i = 0; i < 3; i++) {
            player.getDrawPile().addCard(new Estate());
        }
        System.out.println("StartDeck made for player "+ player.getName());
    }
    //Sets the current game state | available states: action state , buy state , cleanup state , finished state
    public void setState(GameState state) {
        this.state = state;
    }
    public void trashCardFrom(Player player, Deck deck, int position) {
        trashPile.addCard(player.getCardFrom(deck, position));
    }
    //Puts given card to trashPile
    public void trashCard(Card cardToTrash) {
        trashPile.addCard(cardToTrash);
        System.out.println(cardToTrash.getName()+" trashed");
    }
    //Supply can sell card when: cardName exists in pile, pile is not empty AND the card doesn't cost too much
    public boolean canSupplySellCard(String cardName, int maxCost) {
        return (supply.contains(cardName) && (!supply.isPileEmpty(cardName)) && (maxCost >= supply.getCost(cardName)));
    }
    public boolean playerHasChosen(ArrayList<Card> availableCardsToPlay, String cardName){
        boolean playerHasChosen = false;
        for (Card card : availableCardsToPlay ){
            if (card.getName().equals(cardName)) playerHasChosen = true;
        }
        return playerHasChosen;
    }
    public boolean isGameFinished(){
        // checks whethers 3 piles are empty or the province pile is empty
        if (supply.getEmptyPiles().size() > 3 || supply.isPileEmpty("province")){
            return true;
        } else {
            return false;
        }
    }
    public GameState getState() {
        return state;
    }
    public ArrayList<Player> getPlayers() {
        return playerList;
    }
    public Player getActivePlayer() {
        return activePlayer;
    }
    //Gives all piles that are not empty
    public ArrayList getAvailableCards() {
        ArrayList<Card> temp = new ArrayList<Card>();
        ArrayList<Card> availableCards = supply.getAvailablePiles();

        for (int i = 0; i < availableCards.size(); i++) {
            temp.add(availableCards.get(i));
        }
        return temp;
    }
    //Calls availableCards and filters the cards that cost too much
    public ArrayList getAvailableCardsWithCost(int maxCost) {
        return supply.getAllCardsWithMaxCost(maxCost);
    }
    public int getCardCost(String cardName){
        Card card = supply.showCard(cardName);
        if (card != null){
            return card.getCost();
        } else {
            return -1;
        }
    }
    public String getCardType(String cardName){
        Card card = supply.showCard(cardName);
        if (card != null){
            return card.getType();
        } else {
            return "";
        }
    }
    public Card chooseCardFromHand(Player player){
        Card chosenCard = null;
        int selectedCard = 0;       //INPUT
        while ( 0 > selectedCard || selectedCard >= player.getHandSize() ){
            System.out.println(player.getName()+": Choose a card to play");
            selectedCard = sc.nextInt();                            //INPUT
        }
        return chosenCard;
    }
    public Supply getSupply(){
        return this.supply;
    }

    public int getTotalValue(Player player){
        int totalValue = player.getDiscardPile().getTotalValue();
        totalValue += player.getDrawPile().getTotalValue();
        totalValue += player.getHand().getTotalValue();
        return totalValue;
        
    }

}
