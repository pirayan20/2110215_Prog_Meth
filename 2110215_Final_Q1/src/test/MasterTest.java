package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.logic.GameAction;
import game.logic.GameLogic;
import game.logic.GameRound;
import game.object.Card;
import game.object.GameObject;
import role.base.PreRoundActable;
import role.base.PreTurnActable;
import role.base.Role;
import role.derived.Master;

public class MasterTest {

	private Role role;
	private GameRound currentRound;
	private Card[] cards;

	@BeforeEach
	public void setUp() throws Exception {
		role = new Master();
		
		int numCards = 7;
		cards = new Card[numCards];
		cards[0] = new Card("C001", -2);
		cards[1] = new Card("C002", -1);
		cards[2] = new Card("C003", 0);
		cards[3] = new Card("C004", 1);
		cards[4] = new Card("C005", 3);
		cards[5] = new Card("C006", 5);
		cards[6] = new Card("C007", 7);
		
		GameLogic.getInstance().initializeGame();
		currentRound = GameLogic.getInstance().getCurrentRound();
		currentRound.setCards(cards);
	}

	@Test
	public void testRole() {
		assertTrue(role instanceof Role);
		assertTrue(role instanceof PreRoundActable);
		assertFalse(role instanceof PreTurnActable);
	}

	@Test
	public void testToString() {
		assertEquals("Master", role.toString());
	}

	@Test
	public void testPreRoundActs() {
		PreRoundActable preRoundActor = (PreRoundActable) role;
		ArrayList<GameObject> actionInfo = new ArrayList<GameObject>();
		actionInfo.add(cards[1]);
		actionInfo.add(cards[3]);
		actionInfo.add(cards[2]);
		actionInfo.add(cards[0]);
		actionInfo.add(cards[4]);
		actionInfo.add(cards[6]);
		actionInfo.add(cards[5]); 
		preRoundActor.preRoundActs(new GameAction(actionInfo));
		
		int[] permutation = new int[] { 3, 0, 2, 1, 4, 6, 5 };
		for (int i = 0; i < 7; i++)
			assertEquals(permutation[i], cards[i].getSlot());
		for (int i = 0; i < 7; i++)
			assertEquals(actionInfo.get(i), currentRound.getCardAt(i));
	}

}
