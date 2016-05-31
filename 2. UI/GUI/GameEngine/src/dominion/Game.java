package dominion;

import java.util.Scanner;

public class Game {

	private String player1;
	private String player2;
	private Scanner sc = new Scanner(System.in);
	private GameState state;
    private GameState finishedState = new FinishedState();
	
	public Game(){
		System.out.println("welcome to dominion");
		askPlayerNames();
		GameController game = new GameController(player1, player2);
        while (state != finishedState) {
            game.startNewTurn();
            this.state = game.getState();
            state.run(game);
        }  
	}

	private void askPlayerNames() {
		System.out.println("please enter name for player 1");
		player1 = sc.nextLine();
		System.out.println("please enter name for player 2");
		player2 = sc.nextLine();
	}
}
