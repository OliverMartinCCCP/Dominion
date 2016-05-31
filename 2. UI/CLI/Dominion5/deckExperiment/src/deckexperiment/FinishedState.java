/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deckexperiment;
import deckexperiment.cards.*;
import java.util.ArrayList;
/**
 *
 * @author StefVanremoortele
 */
public class FinishedState implements GameState {
    private GameController game;
    private Player winningPlayer;
            
    @Override
    public void run(GameController game){
        System.out.println("THE GAME IS FINISHED");
        this.game = game;
        winningPlayer = game.getPlayers().get(0);
        int mostValue = 0;
        for (Player player : game.getPlayers()){
            System.out.println("Total value points for "+player.getName()+": "+game.getTotalValue(player));
            if (game.getTotalValue(player) == getMostValue()) winningPlayer = player;
        }
        System.out.println(winningPlayer.getName()+" wins with "+game.getTotalValue(winningPlayer)+" points.");
    }
    public void setWinningPlayer(int mostValue){
        for (Player player : game.getPlayers()) {
            if (game.getTotalValue(player) == mostValue){
                this.winningPlayer = player;
            }
        }
    }
    
    public int getMostValue(){
        int mostValue = 0;
        for (Player player : game.getPlayers()){
            if (game.getTotalValue(player) > mostValue) mostValue = game.getTotalValue(player);
        }
        return mostValue;
    }
    public String toString(){
        return "finished state";
    }
}
