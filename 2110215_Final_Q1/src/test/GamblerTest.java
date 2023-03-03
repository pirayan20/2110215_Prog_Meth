package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.logic.GameAction;
import game.logic.GameLogic;
import game.logic.GameRound;
import game.object.GameObject;
import game.object.Player;
import role.base.PreRoundActable;
import role.base.PreTurnActable;
import role.base.Role;
import role.derived.Gambler;
import role.derived.Master;
import role.derived.Ordinary;
import role.derived.Seer;

public class GamblerTest {

	private Role role;
	private GameRound currentRound;
	private Player[] players;

	@BeforeEach
	public void setUp() throws Exception {
		role = new Gambler();

		GameLogic.getInstance().initializeGame();
		currentRound = GameLogic.getInstance().getCurrentRound();

		Player p0 = new Player("A", new Ordinary());
		Player p1 = new Player("B", new Seer());
		Player p2 = new Player("C", new Master());
		Player p3 = new Player("D", new Gambler());
		players = new Player[] { p0, p1, p2, p3 };
		currentRound.setPlayers(players);
	}

	@Test
	public void testRole() {
		assertTrue(role instanceof Role);
		assertTrue(role instanceof PreRoundActable);
		assertTrue(role instanceof PreTurnActable);
	}

	@Test
	public void testToString() {
		assertEquals("Gambler", role.toString());
	}

	@Test
	public void testPreRoundActs() {
		PreRoundActable preRoundActor = (PreRoundActable) role;

		for (int i = 0; i < 4; i++)
			assertEquals(players[i], currentRound.getPlayerAt(i));

		ArrayList<GameObject> actionInfo = new ArrayList<GameObject>();
		actionInfo.add(players[1]);
		actionInfo.add(players[3]);
		actionInfo.add(players[2]);
		actionInfo.add(players[0]);
		preRoundActor.preRoundActs(new GameAction(actionInfo));

		int[] permutation = new int[] { 3, 0, 2, 1 };

		for (int i = 0; i < 4; i++)
			assertEquals(permutation[i], players[i].getOrder());

		for (int i = 0; i < 4; i++)
			assertEquals(actionInfo.get(i), currentRound.getPlayerAt(i));
	}

	@Test
	public void testPreTurnActs() {
		PreTurnActable preTurnActor = (PreTurnActable) role;

		assertTrue(currentRound.getToBeSwappedPlayers().isEmpty());

		ArrayList<GameObject> actionInfo = new ArrayList<GameObject>();
		actionInfo.add(players[0]);
		actionInfo.add(players[2]);
		preTurnActor.preTurnActs(new GameAction(actionInfo));

		ArrayList<Player> swapPair = currentRound.getToBeSwappedPlayers();
		assertEquals(swapPair.size(), 2);
		assertTrue(swapPair.contains(players[0]));
		assertTrue(swapPair.contains(players[2]));
	}

}
