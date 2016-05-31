package cards;
import java.util.Scanner;

import dominion.GameController;
import dominion.GameState;
import dominion.Player;
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
        int trashedCards = 0;
        int selectedCard = -1;
        Player activePlayer = game.getActivePlayer();
        while ( activePlayer.getHandSize() > 4 && trashedCards < 4){
            if (selectedCard == 0 ) {
                break;
            } else {
                System.out.println("Choose a card to trash");
                Card cardToDiscard = activePlayer.giveCardFromHand(selectedCard);
                activePlayer.discard(cardToDiscard);
                trashedCards++;
            }
        }
    }
    public Card clone(){
        return new Chapel();
    }
}
