package deckexperiment.cards;
import deckexperiment.*;
import java.util.Scanner;
/**
 *
 * @author StefVanremoortele
 */
public class Chancellor extends ActionCard{
    private Scanner sc;
    public Chancellor(){
        super("chancellor", 3);
    }
    @Override
    public void play(GameController game){
            //+2 coins
            //you may immediately put your deck into your discardPile
            Player activePlayer = game.getActivePlayer();
            sc = new Scanner(System.in);
            activePlayer.updateCoins(2);
            boolean decided = false;
            
            
            while ( !decided ){
                System.out.println("Discard deck?  yes or no");         //INPUT
                String answer = sc.nextLine();
                if (answer.toLowerCase().equals("yes")){ //Discard deck
                    activePlayer.discardDeck(activePlayer.getDrawPile());
                    decided = true;
                    System.out.println(activePlayer.getName()+" discards deck");
                }
                if (answer.toLowerCase().equals("no")  ) decided = true;
            }
    }
    public Card clone(){
        return new Chancellor();
    }
}
