package game.logic;

import java.util.ArrayList;

import game.object.Card;
import game.object.Player;
import role.derived.Gambler;
import role.derived.Master;
import role.derived.Ordinary;
import role.derived.Seer;

public class GameLogic {

	public static final int INITIAL_MARBLES = 20;
	public static final int WINNING_MARBLES = 100;

	private static GameLogic instance = null;

	private Player[] players;
	private Card[] cards;
	private GameRound currentRound;

	private ArrayList<String> messages = new ArrayList<String>();

	private GameLogic() {
		initializeGame();
	}

	public void initializeGame() {
		int numPlayers = 4;
		players = new Player[numPlayers];
		players[0] = new Player("Anny", new Ordinary());
		players[1] = new Player("Beth", new Seer());
		players[2] = new Player("Carl", new Master());
		players[3] = new Player("Doug", new Gambler());

		int numCards = 7;
		cards = new Card[numCards];
		cards[0] = new Card("C001", -2);
		cards[1] = new Card("C002", -1);
		cards[2] = new Card("C003", 0);
		cards[3] = new Card("C004", 1);
		cards[4] = new Card("C005", 3);
		cards[5] = new Card("C006", 5);
		cards[6] = new Card("C007", 7);

		currentRound = new GameRound(numPlayers, numCards);
	}

	public static GameLogic getInstance() {
		if (instance == null)
			instance = new GameLogic();
		return instance;
	}

	public Player[] getPlayers() {
		return players;
	}

	public int getNumPlayers() {
		return players.length;
	}

	public Card[] getCards() {
		return cards;
	}

	public int getNumCards() {
		return cards.length;
	}

	public GameRound getCurrentRound() {
		return currentRound;
	}

	public Player getWinner() {
		int maxMarbles = 0;
		Player winner = null;
		for (Player player : players)
			if (player.getMarblesEarned() == maxMarbles)
				winner = null;
			else if (player.getMarblesEarned() > maxMarbles) {
				winner = player;
				maxMarbles = player.getMarblesEarned();
			}
		return maxMarbles < WINNING_MARBLES ? null : winner;
	}

	public boolean isGameEnd() {
		return getWinner() != null;
	}

	public void announces() {
		announces(String.join(" ", messages));
		messages.clear();
	}

	public void announces(String message) {
		System.out.println("[ANNOUNCEMENT] " + message);
	}

	public void privatesTo(Player player) {
		privatesTo(player, String.join(" ", messages));
		messages.clear();
	}

	public void privatesTo(Player player, String message) {
		String playerInfo = String.format("%s<%s>", player.getName(), player.getRole().toString().charAt(0));
		System.out.println(" [TO: " + playerInfo + "]\t  " + message);
	}

	public void addsMessage(String message) {
		messages.add(message);
	}
}
