/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import deckexperiment.cards.*;
import deckexperiment.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import deckexperiment.Deck;
/**
 *
 * @author StefVanremoortele
 */
public class PlayerTest {
    private Player testPlayer = new Player("Stef");
    Deck testDeck = new Deck();
    public PlayerTest() {
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


    @Test
    public void getNameTest() {
        assertEquals("Stef", testPlayer.getName());
    }
    @Test
    public void hasActionCardInHand(){
        assertEquals(false, testPlayer.hasActionCardInHand());
        Card smithy = new Smithy();
        testPlayer.getHand().addCard(smithy);
    }
    @Test
    public void revealCardTest(){
        testPlayer.getHand().clear();
        
    }

}
