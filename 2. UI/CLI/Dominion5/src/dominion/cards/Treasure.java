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
public abstract class Treasure implements Card {
    private String name;
    private int cost;
    private int value;
    public Treasure(String cardName, int cost, int value){
        this.name = cardName;
        this.cost = cost;
        this.value = value;
    }
    public String getName(){
        return name;
    }
    public int getCost(){
        return this.cost;
    }
    public int getValue(){
        return this.value;
    }
}
