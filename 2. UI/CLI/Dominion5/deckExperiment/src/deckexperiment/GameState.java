/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deckexperiment;
import java.util.ArrayList;

import deckexperiment.cards.*;

/**
 *
 * @author StefVanremoortele
 */
public interface GameState {
    public void run(GameController game);   
}