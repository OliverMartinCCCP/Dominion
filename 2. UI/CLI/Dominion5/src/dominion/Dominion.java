
package dominion;
import java.util.ArrayList;
import dominion.cards.*;
/**
 *
 * @author STEF
 */
public class Dominion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Card c1 = new Smithy();
        Card c2 = new Copper();
        Card c3 = new Estate();
        
        c1.performAction();
        ArrayList<Card> test = new ArrayList<Card>();
        test.add(c1);<
        test.add(c2);
        test.add(c3);
        
        for (Card c : test){
            System.out.println(c instanceof Kingdom);
            if (c instanceof Kingdom){
                
            }
        }
    }
    
}
