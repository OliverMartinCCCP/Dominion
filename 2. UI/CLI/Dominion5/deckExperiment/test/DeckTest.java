/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import deckexperiment.cards.*;
import deckexperiment.Deck;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 *
 * @author StefVanremoortele
 */
public class DeckTest {
    private Deck testDeck = new Deck();

    public DeckTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void addCardTest(){
        testDeck.clear();
        Card c = new Copper();
        testDeck.addCard(c);
        assertEquals(c, testDeck.giveFirstCard(1));
    }
    @Test
    public void getSizeTest(){
        testDeck.clear();
        for (int i = 0; i < 5; i++){
            testDeck.addCard(new Copper());
        }
        assertEquals(5, testDeck.getSize());
    }
    @Test
    public void getCard(){ //get card from position
        //testing for position 3;
        Card copper = new Copper();
        Card smithy = new Smithy();
        Card estate = new Estate();
        testDeck.addCard(copper);
        testDeck.addCard(smithy);
        testDeck.addCard(estate);
        
        assertEquals(estate, testDeck.giveFirstCard(3));
    }
    @Test
    public void containsCardOfTypeTest(){
        testDeck.addCard(new Smithy());
        assertEquals(true, testDeck.containsCardOfType("action"));
    }
    @Test
    public void shuffleTest(){ 
            // testen van shuffle is onbetrouwbaar?
    }
    @Test
    public void getTotalCostTest(){
        //copperCost + silverCost + goldCost = 9
        testDeck.addCard(new Copper());
        testDeck.addCard(new Silver());
        testDeck.addCard(new Gold());
        assertEquals(9, testDeck.getTotalCost());
    }
    @Test
    public void getTotalValueTest(){
        //copperValue + silverValue + goldValue = 6
        testDeck.addCard(new Copper());
        testDeck.addCard(new Silver());
        testDeck.addCard(new Gold());
        assertEquals(6, testDeck.getTotalValue());
    }

    @Test public void isEmptyTest(){
        testDeck.addCard(new Copper());
        assertEquals(false, testDeck.isEmpty());
        testDeck.clear();
        assertEquals(true, testDeck.isEmpty());
    }

    
}
