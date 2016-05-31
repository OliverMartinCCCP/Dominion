/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion.cards;

/**
 *
 * @author STEF
 */
public abstract class Kingdom implements Card, Action {
    private String name;
    private int cost;
    
    public Kingdom(String cardName, int cost){
        this.name = cardName;
        this.cost = cost;
    }
    public int getCost(){
        return this.cost;
    }
    public String getName(){
        return this.name;
    }
    public int getValue(){
        return 0;
    }
}
