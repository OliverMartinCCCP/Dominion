/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion;
import java.util.ArrayList;

import cards.*;
/**
 *
 * @author StefVanremoortele
 */
public class FinishedState implements GameState {
    private GameController game;
    @Override
    public void run(GameController game){
        System.out.println("THE GAME IS FINISHED");
        this.game = game;
        Player activePlayer = game.getActivePlayer();
        //COUNT VALUE OF PLAYERS
        //MESSAGE WHO WON THE GAME
    }
    public String toString(){
        return "finished state";
    }
	@Override
	public void SetPlayedCard(String cardPlayed) {
		// TODO Auto-generated method stub
		
	}
}
