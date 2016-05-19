package dominion;

public abstract class VictoryCard extends Card {
	abstract void performAction(Game game);
	public VictoryCard(String name, int cost, int value){
		super(name, "victory", cost, value);
	}
}
