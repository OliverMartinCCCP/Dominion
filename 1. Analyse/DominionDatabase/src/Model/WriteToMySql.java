package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class WriteToMySql {
	public void main(String args[]) throws Exception
	{
	}
	
	public Connection getConnection() throws Exception
	{
		try{
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/dominion";
			String username = "root";
			String password = "";
			Class.forName(driver);
			
			Connection com = DriverManager.getConnection(url,username,password);
			System.out.println("Connected");
			return com;
		} catch(Exception e){System.out.println(e);};
		
		return null;
	}
	
	public void createTables() throws Exception
		{try{
			Connection com = getConnection();
			PreparedStatement createGames = com.prepareStatement("CREATE TABLE IF NOT EXISTS games(id int NOT NULL AUTO_INCREMENT, player1 varchar(255) NOT NULL, player2 varchar(255) NOT NULL, currentplayer varchar(255) NOT NULL, laststep varchar(255) NOT NULL, PRIMARY KEY (id))");
			createGames.executeUpdate();	
			PreparedStatement createPlayers = com.prepareStatement("CREATE TABLE IF NOT EXISTS players(id int NOT NULL AUTO_INCREMENT, name varchar(255), wins int NULL DEFAULT NULL, loses int NULL DEFAULT NULL, lastgame int, PRIMARY KEY (id))");
			createPlayers.executeUpdate();	
			PreparedStatement createCards = com.prepareStatement("CREATE TABLE IF NOT EXISTS cards (cardname varchar(255) NOT NULL, cost int NOT NULL, value int DEFAULT NULL, action varchar(255) DEFAULT ' ', type varchar(255) NOT NULL, PRIMARY KEY (cardname))");
			createCards.executeUpdate();	

		}catch(Exception e){System.out.println(e);}
		finally{System.out.println("function complete...");};
	}
	
	public void postCards() throws Exception
	{
	
	try{
		Connection com = getConnection();
		PreparedStatement postVictory = com.prepareStatement
				("INSERT INTO cards (cardname, cost, value, type) VALUES ('estate', 2, 1, 'victory'),"
						+ "('duchy', 5, 3, 'victory'),"
						+ "('province', 8, 6, 'victory'),"
						+ "('gardens', 4, 0, 'victory')");
		postVictory.executeUpdate();
		
		
		PreparedStatement postTreasure = com.prepareStatement
				("INSERT INTO cards (cardname, cost, value, type) VALUES ('copper', 0, 1, 'treasure'),"
				+ "('silver', 3, 2, 'treasure'),"
				+ "('gold', 6, 3, 'treasure')");
		postTreasure.executeUpdate();
		
		
		PreparedStatement postAction = com.prepareStatement
				("INSERT INTO cards (cardname, cost, action, type) VALUES ('adventurer', 6, 'Reveal cards from your deck until you reveal 2 Treasure cards. Put those Treasure cards in your hand and discard the other revealed cards.', 'action'), ('bureaucrat', 4, 'Gain a silver card; put it on top of your deck. Each other player reveals a Victory card from his hand and puts it on his deck (or reveals a hand with no Victory cards).', 'action'), ('cellar', 2, '+1 Action. Discard any number of cards. +1 Card per card discarded.', 'action'), ('chancellor', 3, '+$2. You may immediately put your deck into your discard pile', 'action'), ('chapel', 2, 'Trash up to 4 cards from your hand.', 'action'), ('councilroom', 5, '+4 Cards. +1 Buy. Each other player draws a card.', 'action'), ('curse', 0, '', 'action'), ('feast', 4, 'Trash this card. Gain a card costing up to $5.', 'action'), ('festival', 5, '+2 Actions. +1 Buy. +$2.', 'action'), ('laboratory', 5, '+2 Cards. +1 Action.', 'action'), ('library', 5, 'Draw until you have 7 cards in hand. You may set aside any Action cards drawn this way, as you draw them; discard the set aside cards after you finish drawing.', 'action'), ('market', 5, '+1 Card. +1 Action. +1 Buy; +$1.', 'action'), ('militia', 4, '+$2. Each other player discards down to 3 cards in his hand.', 'action'), ('mine', 5, 'Trash a Treasure card from your hand. Gain a Treasure card costing up to $3 more. put it into your hand.', 'action'), ('moat', 2, '+2 Cards. When another player plays an Attack card, you may reveal this from your hand. If you do, you are unaffected by that Attack.', 'action'), ('moneylender', 4, 'Trash a Copper from your hand. If you do, +$3.', 'action'), ('remodel', 4, 'Trash a card from your hand. Gain a card costing up to $2 more than the trashed card.', 'action'), ('smithy', 4, '+3 Cards.', 'action'), ('spy', 4, '+1 Card. +1 Action. Each player (including you) reveals the top card of his deck and either discards it or puts it back, your choice.', 'action'), ('thief', 4, 'Each other player reveals the top 2 cards of his deck. If they revealed any Treasure cards, they trash one of them that you choose. You may gain any or all of these trashed cards. They discard the other revealed cards.', 'action'), ('throneroom', 4, 'Choose an Action card in your hand. Play it twice.', 'action'), ('village', 3, '+1 Card. +2 Actions.', 'action'), ('witch', 5, '+2 Cards. Each other player gains a Curse card.', 'action'), ('woodcutter', 3, '+1 Buy. +$2.', 'action'), ('workshop', 3, 'Gain a card costing up to $4.', 'action')");
		postAction.executeUpdate();
		
		
	}catch(Exception e){System.out.println(e);}
	finally{
		System.out.println("Insert Completed");
	}
}

	public void postPlayerName(String playerName) throws Exception
		{
		final String var1 = playerName;
		
		try{
			Connection com = getConnection();
			PreparedStatement posted = com.prepareStatement("INSERT INTO players (name) SELECT * FROM (SELECT '"+var1+"') AS tmp WHERE NOT EXISTS (SELECT name FROM players WHERE name = '"+var1+"')LIMIT 1");
			posted.executeUpdate();
		}catch(Exception e){System.out.println(e);}
		finally{
			System.out.println("Insert Completed");
		}
	}
	
	public void updatePlayerInfo(String playerName, int amountWins, int amountLoses) throws Exception
		{
		final String var1 = playerName;
		final int var2 = amountWins;
		final int var3 = amountLoses;
		
		try{
			Connection com = getConnection();
			PreparedStatement posted = com.prepareStatement("UPDATE players SET wins = '"+var2+"', loses = '"+var3+"' WHERE( name = '"+var1+"');");
			posted.executeUpdate();
		}catch(Exception e){System.out.println(e);}
		finally{
			System.out.println("Insert Completed");
		}
	}
	
	public void postGame(String playerName1, String playerName2) throws Exception
	{
	final String var1 = playerName1;
	final String var2 = playerName2;
	
	try{
		Connection com = getConnection();
		PreparedStatement posted = com.prepareStatement("INSERT INTO games (player1, player2) VALUES ('"+var1+"', '"+var2+"')");
		posted.executeUpdate();
	}catch(Exception e){System.out.println(e);}
	finally{
		System.out.println("Insert Completed");
	}
}
	
	public void updateGameInfo(String playerName1, String playerName2, String currentPlayer, String lastStep) throws Exception
	{
	final String var1 = playerName1;
	final String var2 = playerName2;
	final String var3 = currentPlayer;
	final String var4 = lastStep;
	
	try{
		Connection com = getConnection();
		PreparedStatement posted = com.prepareStatement("UPDATE games SET currentplayer = "+var3+", laststep = "+var4+" WHERE ((player1 = "+var1+") AND (player2 = "+var2+", player2))");
		posted.executeUpdate();
		
		PreparedStatement statement = com.prepareStatement("SELECT id FROM games WHERE ((player1 = "+var1+") AND (player2 = "+var2+", player2))");
		
		ResultSet result = statement.executeQuery();
		PreparedStatement updatePlayer = com.prepareStatement("UPDATE players SET lastgame = "+result+" WHERE ((player1 = "+var1+") OR (player2 = "+var2+", player2))");
		updatePlayer.executeUpdate();
		
	}catch(Exception e){System.out.println(e);}
	finally{
		System.out.println("Insert Completed");
	}
}
	
	public ArrayList<String> get() throws Exception
		{
		try{
			Connection com = getConnection();
			PreparedStatement statement = com.prepareStatement("SELECT * FROM cards WHERE (type = 'action' AND cardname = 'workshop')");
			
			ResultSet result = statement.executeQuery();
			
			ArrayList<String> array = new ArrayList<String>();
			while(result.next()){
				System.out.print(result.getString("cardname"));
				System.out.print(" ");
				System.out.print(result.getString("cost"));
				System.out.print(" ");
				System.out.print(result.getString("value"));
				System.out.print(" ");
				System.out.print(result.getString("action"));
				System.out.print(" ");
				System.out.println(result.getString("type"));
				
				array.add(result.getString("cardname"));
				array.add(result.getString("cost"));
				array.add(result.getString("value"));
				array.add(result.getString("action"));
				array.add(result.getString("type"));
				
			}
			System.out.println("All records have been selected");
			return array;
			
		}catch(Exception e){System.out.println(e);}
		return null;
		
	}
}