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
import role.derived.Seer;

public class SeerTest {

	private Role role;
	private GameRound currentRound;
	private Card[] cards;


	@BeforeEach
	public void setUp() throws Exception {
		role = new Seer();
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
		assertFalse(role instanceof PreRoundActable);
		assertTrue(role instanceof PreTurnActable);
	}

	@Test
	public void testToString() {
		assertEquals("Seer", role.toString());
	}

	@Test
	public void testPreTurnActs() {
		PreTurnActable preTurnActor = (PreTurnActable) role;
		ArrayList<GameObject> actionInfo = new ArrayList<GameObject>();
		actionInfo.add(cards[1]);
		actionInfo.add(cards[3]);
		preTurnActor.preTurnActs(new GameAction(actionInfo));
		int[] permutation = new int[] { 0, 3, 2, 1, 4, 5, 6 };
		for (int i = 0; i < 7; i++)
			assertEquals(permutation[i], cards[i].getSlot());
		
		assertEquals(actionInfo.get(0), currentRound.getCardAt(3));
		assertEquals(actionInfo.get(1), currentRound.getCardAt(1));
	}

}
