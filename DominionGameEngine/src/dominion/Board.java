package dominion;

public class Board {

	private Deck deck = new Deck();
	
	public Board(){
		test();
	}
	
	public void showDecks(int amountCoins){
		System.out.println("These are the cards on the board you can buy");
	
		deck.vraagKost("copper");
		
		
	}
	
	public void test()
	{
		System.out.println("never gonna give you up");
		System.out.println("never gonna let you down");
		System.out.println("never gonna run around");
		System.out.println("and desert you");
		System.out.println("never gonna make you cry");
		System.out.println("never gonna say goodbye");
		System.out.println("never gonna tell a lie");
		System.out.println("and hurt you");
		System.out.println(deck.validateCost(2, "province"));
	}
}
