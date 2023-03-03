package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.logic.GameAction;
import game.logic.GameLogic;
import game.logic.GameRound;
import game.object.Card;
import game.object.GameObject;
import game.object.Player;
import role.derived.Gambler;
import role.derived.Master;
import role.derived.Ordinary;
import role.derived.Seer;

public class PlayerTest {
	Player[] players;
	private GameRound currentRound;
	private Card[] cards;
	
	@BeforeEach
	public void setUp() throws Exception {
		GameLogic.getInstance().initializeGame();
		currentRound = GameLogic.getInstance().getCurrentRound();
		Player p0 = new Player("A", new Ordinary());
		Player p1 = new Player("B", new Seer());
		Player p2 = new Player("C", new Master());
		Player p3 = new Player("D", new Gambler());
		players = new Player[] { p0, p1, p2, p3 };
		
		int numCards = 7;
		cards = new Card[numCards];
		cards[0] = new Card("C001", -2);
		cards[1] = new Card("C002", -1);
		cards[2] = new Card("C003", 0);
		cards[3] = new Card("C004", 1);
		cards[4] = new Card("C005", 3);
		cards[5] = new Card("C006", 5);
		cards[6] = new Card("C007", 7);
		
		currentRound.setPlayers(players);
		currentRound.setCards(cards);
	}
	
	@Test
	public void testDoPreRoundAction() {
		ArrayList<GameObject> actionInfo = new ArrayList<GameObject>();
		ArrayList<GameObject> actionInfo2 = new ArrayList<GameObject>();
		
		actionInfo.add(players[1]);
		actionInfo.add(players[3]);
		actionInfo.add(players[2]);
		actionInfo.add(players[0]);
		
		actionInfo2.add(cards[1]);
		actionInfo2.add(cards[3]);
		actionInfo2.add(cards[2]);
		actionInfo2.add(cards[0]);
		actionInfo2.add(cards[4]);
		actionInfo2.add(cards[6]);
		actionInfo2.add(cards[5]); 
		
		players[3].doPreRoundAction(new GameAction(actionInfo));
		players[2].doPreRoundAction(new GameAction(actionInfo2));
		
		int[] permutation = new int[] { 3, 0, 2, 1 };
		int[] permutation2 = new int[] { 3, 0, 2, 1, 4, 6, 5 };
		
		for (int i = 0; i < 4; i++)
			assertEquals(permutation[i], players[i].getOrder());
		for (int i = 0; i < 4; i++)
			assertEquals(actionInfo.get(i), currentRound.getPlayerAt(i));
		for (int i = 0; i < 7; i++)
			assertEquals(permutation2[i], cards[i].getSlot());
		for (int i = 0; i < 7; i++)
			assertEquals(actionInfo2.get(i), currentRound.getCardAt(i));
		
	}
	@Test
	public void testDoPreTurnAction() {
		assertTrue(currentRound.getToBeSwappedPlayers().isEmpty());
		
		ArrayList<GameObject> actionInfo = new ArrayList<GameObject>();
		ArrayList<GameObject> actionInfo2 = new ArrayList<GameObject>();
		
		actionInfo.add(players[0]);
		actionInfo.add(players[2]);
		
		actionInfo2.add(cards[1]);
		actionInfo2.add(cards[3]);
		
		players[3].doPreTurnAction(new GameAction(actionInfo));
		
		ArrayList<Player> swapPair = currentRound.getToBeSwappedPlayers();
		
		assertEquals(swapPair.size(), 2);
		assertTrue(swapPair.contains(players[0]));
		assertTrue(swapPair.contains(players[2]));
		
		players[1].doPreTurnAction(new GameAction(actionInfo2));
		int[] permutation = new int[] { 0, 3, 2, 1, 4, 5, 6 };
		for (int i = 0; i < 7; i++)
			assertEquals(permutation[i], cards[i].getSlot());
		
		assertEquals(actionInfo2.get(0), currentRound.getCardAt(3));
		assertEquals(actionInfo2.get(1), currentRound.getCardAt(1));
	}
}
