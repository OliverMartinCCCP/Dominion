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
public interface GameState {
    public void run(GameController game);

	public void SetPlayedCard(String cardPlayed);   
}