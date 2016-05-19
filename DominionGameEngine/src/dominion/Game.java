package dominion;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;

public class Game
{	
	private int numberOfPlayers;
	private int aantalSpelers;
	private Scanner sc = new Scanner(System.in);
	private ArrayList<Player> playerlist;
    private boolean actionsFinished;
    private boolean buysFinished;
    private Player activePlayer;
    private Board board;
	
    public Game(){
    	board = new Board();
    	initGame();
    }
    
    public void initGame(){
    	System.out.println("Welcome to dominion!");
    	numberOfPlayers = askNumberOfPlayers();
    	makePlayerList(numberOfPlayers);
    	startGame();
    }
    
    public int askNumberOfPlayers(){
    	System.out.println("With how many players do you want to play?");
    	aantalSpelers = sc.nextInt();
    	while (aantalSpelers < 2 || aantalSpelers >4){
    		System.out.println("Het aantal spelers moet kleiner zijn dan 5 en groter dan 1");
    		aantalSpelers = sc.nextInt();
    	}
    	return aantalSpelers;
    }
    
    public void makePlayerList(int number){
    	playerlist = new ArrayList<Player>();
        for (int i = 0; i < numberOfPlayers; i++)
        {
            System.out.println("give player name: "); 
            String name = sc.next();
            playerlist.add(new Player(name));
        }
    }
    
    public void startGame(){
    	clearScreen();
    	for(int i = 0; i<numberOfPlayers;i++){
    		for(Player player : playerlist){
    			actionsFinished = false;
    			buysFinished = false;
    			activePlayer = player;
    			while (!actionsFinished){
    				actionPhase();
    			}
    			while (!buysFinished){
    				buyPhase();
    			}
    			cleanUp();
    			clearScreen();
    		}
    	}
    	
    }
    
    public void showGameInfo(){
    	System.out.println("currently playing: "+activePlayer.getPlayerName());
    	System.out.println("Remaining actions: "+activePlayer.getRemainingActions());
    	System.out.println("Remaining buys: "+activePlayer.getRemainingBuys());
    	System.out.println("Remaining coins: "+activePlayer.getRemainingCoins());
    }
    
    public void actionPhase(){
    	if(activePlayer.hasActionCard()){
    		showGameInfo();
    		System.out.println("ACTION PHASE");
    		activePlayer.showHand();
    		askForAction();	
    	}
    	else{
    		actionsFinished = true;
    	}
    }
    
    
    public void buyPhase(){
    	showGameInfo();
    	System.out.println("BUY PHASE");
    	System.out.println("What do you want to do?");
    	System.out.println("\n1 play TreasureCards \n2 End buy phase");
    	activePlayer.showHand();
    	int action = sc.nextInt();
    	switch(action){
    		case 1 : 
    			activePlayer.playTreasureCards();
    			cardsThatCanBeBought();
    			break;
    		case 2:
    			buysFinished = true;
    			break;
    		default:
    			System.out.println("no valid action");
    	}
    }
    
    public void cleanUp(){
    	activePlayer.resetValues();
    	activePlayer.discardHand();
    	activePlayer.drawHand();
    }
    
    public void clearScreen(){
    	for(int i = 0; i<30;i++){
    		System.out.println();
    	}
    }

    public void askForAction(){
    	boolean validInput = false;
    	System.out.println("please choose an action card to play..");
    	int selectedCard = 0;
    	while (!validInput){
    		selectedCard = sc.nextInt()-1;
    		if (selectedCard > activePlayer.getCardsInHand().size()|| selectedCard < 0){
    			System.out.println("Card not found please choose another card");
    		}
    		else{
    			if (activePlayer.getCardsInHand().get(selectedCard).isActionCard()){
    				validInput = true;
    			}
    			else{
    				System.out.println("The selected card is not an action card!");
    			}
    		}
    		
    	}
    }

    public void cardsThatCanBeBought(){
    	board.showDecks(activePlayer.getRemainingCoins());
    }


}
