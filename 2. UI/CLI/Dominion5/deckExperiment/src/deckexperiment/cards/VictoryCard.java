package deckexperiment.cards;

import deckexperiment.GameController;
import deckexperiment.GameState;
import deckexperiment.Player;
/**
 *
 * @author StefVanremoortele
 */
public abstract class VictoryCard extends Card {
    public abstract void play(GameController game);
    public abstract Card clone();
    public VictoryCard(String name, int cost, int value){
        super(name, "victory", cost, value);
    }
}
