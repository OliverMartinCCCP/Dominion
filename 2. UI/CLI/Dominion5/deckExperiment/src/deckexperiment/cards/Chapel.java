package deckexperiment.cards;
import deckexperiment.GameController;
import deckexperiment.GameState;
import deckexperiment.Player;
import java.util.Scanner;
/**
 *
 * @author StefVanremoortele
 */
public class Chapel extends ActionCard{
    private Scanner sc;
    public Chapel(){
        super("chapel", 2);
    }
    @Override
    public void play(GameController game){
        //Trash up to 4 cards from your hand
        sc = new Scanner(System.in);
        Player activePlayer = game.getActivePlayer();
        int amountOfCardsTrashed = 0;
        boolean stopTrashing = false;
        while (!stopTrashing){
            System.out.println("Choose a card to trash or enter stop");
            String input = sc.nextLine();
            //Choose a card to trash
            if (input.equals("stop")) break;
            try {
                int selectedCard = Integer.parseInt(input);
                Card cardToTrash = activePlayer.getCardFromHand(selectedCard);
                game.trashCard(cardToTrash);
                amountOfCardsTrashed++;
            } catch (Exception e){
                System.err.println(e);
            }
            if (amountOfCardsTrashed == 4) stopTrashing = true; 
        }
    }
    public Card clone(){
        return new Chapel();
    }
}
