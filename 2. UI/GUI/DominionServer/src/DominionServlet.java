
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.*;
import cards.*;
import dominion.*;

public class DominionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WriteToMySql wtms = new WriteToMySql();
	private GameController gc;
	private GameState state;
	private Player activePlayer;
	private String buyCard;
	private String cardPlayed;
	private String kaart1;
	private String kaart2;
	private String kaart3;
	private String kaart4;
	private String kaart5;
	private String activePlayerName;
	private Deck hand = new Deck();	
	
    public DominionServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		Writer writer = response.getWriter();
		
		String operation;
		
		operation = request.getParameter("operation");

		switch(operation)
		{
		case "initialize":
			
			String playerName1 = request.getParameter("name1");
			String playerName2 = request.getParameter("name2");
			gc = new GameController(playerName1,playerName2);
			activePlayerName = gc.getActivePlayer().getName();
			hand = gc.getHand(activePlayerName);
			Supply supply = gc.getSupply();
			ArrayList<Card> kingdom = supply.getActionPile();
			ArrayList<Card> victory = supply.getVictoryPile();
			ArrayList<Card> treasure = supply.getTreasurePile();
			
			kaart1 = hand.getCard(1).getName();
			kaart2 = hand.getCard(2).getName();
			kaart3 = hand.getCard(3).getName();
			kaart4 = hand.getCard(4).getName();
			kaart5 = hand.getCard(5).getName();
			
			String kingdom10 = kingdom.get(0).getName();
			String kingdom1 = kingdom.get(1).getName();
			String kingdom2 = kingdom.get(2).getName();
			String kingdom3 = kingdom.get(3).getName();
			String kingdom4 = kingdom.get(4).getName();
			String kingdom5 = kingdom.get(5).getName();
			String kingdom6 = kingdom.get(6).getName();
			String kingdom7 = kingdom.get(7).getName();
			String kingdom8 = kingdom.get(8).getName();
			String kingdom9 = kingdom.get(9).getName();

			
			String victory1 = victory.get(0).getName();
			String victory2 = victory.get(1).getName();
			String victory3 = victory.get(2).getName();
			String victory4 = victory.get(3).getName();
			
			String treasure1 = treasure.get(0).getName();
			String treasure2 = treasure.get(1).getName();
			String treasure3 = treasure.get(2).getName();
			
			writer.append("{\"kaart1\":\""+kaart1+"\", \"kaart2\":\""+kaart2+"\", \"kaart3\":\""+kaart3+"\", \"kaart4\":\""+kaart4+"\", \"kaart5\":\""+kaart5+"\", \"kingdom1\":\""+kingdom1+"\", \"kingdom2\":\""+kingdom2+"\", \"kingdom3\":\""+kingdom3+"\", \"kingdom4\":\""+kingdom4+"\", \"kingdom5\":\""+kingdom5+"\", \"kingdom6\":\""+kingdom6+"\", \"kingdom7\":\""+kingdom7+"\", \"kingdom8\":\""+kingdom8+"\", \"kingdom9\":\""+kingdom9+"\", \"kingdom10\":\""+kingdom10+"\", \"victory1\":\""+victory1+"\", \"victory2\":\""+victory2+"\", \"victory3\":\""+victory3+"\", \"victory4\":\""+victory4+"\", \"treasure1\":\""+treasure1+"\", \"treasure2\":\""+treasure2+"\", \"treasure3\":\""+treasure3+"\"}");
			
			
			//writer.append("{\"status\":\"OK\", \"name1\":\""+playerName1+"\", \"hallo\":\""+naamKaart+"\", \"kaartnaam2\":\""+naamKaart+"\"}");
			try {
				wtms.createTables();
				wtms.postPlayerName(playerName1);
				wtms.postPlayerName(playerName2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gc.startNewTurn();
			
			break;
			
		case "playTreasureCard":
			String kaart = request.getParameter("card");
			cardPlayed = request.getParameter("number");
			activePlayer = gc.getActivePlayer();
			activePlayerName = activePlayer.getName();
			this.state = gc.getState();
			state.SetPlayedCard(cardPlayed);
			int coins = activePlayer.getCoins();
			int actions = activePlayer.getActions();
			int buys = activePlayer.getBuys();

			writer.append("{\"coins\":\""+coins+"\", \"actions\":\""+actions+"\", \"buys\":\"" +buys+ "\", \"playerName\":\""+activePlayerName+"\"}");
			
			break;
		case "startState":
			state.run(gc);
			break;
		case "buyKingdomCard":
			buyCard = request.getParameter("card");
			this.state = gc.getState();
			state.SetPlayedCard(buyCard);
			state.run(gc);
			
			break;
			
		case "endTurn":
			activePlayerName = gc.getActivePlayer().getName();
			hand = gc.getHand(activePlayerName);
			kaart1 = hand.getCard(1).getName();
			kaart2 = hand.getCard(2).getName();
			kaart3 = hand.getCard(3).getName();
			kaart4 = hand.getCard(4).getName();
			kaart5 = hand.getCard(5).getName();
			gc.startNewTurn();
			
			writer.append("{\"kaart1\":\""+kaart1+"\", \"kaart2\":\""+kaart2+"\", \"kaart3\":\""+kaart3+"\", \"kaart4\":\""+kaart4+"\", \"kaart5\":\""+kaart5+"\", \"activePlayer\":\""+activePlayerName+"\"}");
			break;
		default:
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
