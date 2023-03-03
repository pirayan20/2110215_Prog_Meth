package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import game.logic.GameAction;
import game.logic.GameLogic;
import game.object.Card;
import game.object.GameObject;
import game.object.Player;
import role.base.PreRoundActable;
import role.base.PreTurnActable;
import role.derived.Gambler;

public class Main {

	private static Random rand;
	private static GameLogic game;

	private static Player[] players;
	private static Card[] cards;

	private static ArrayList<Card> randomCards;

	private static void initialize(int seed) {
		rand = new Random(seed);
		game = GameLogic.getInstance();
		game.initializeGame();

		players = game.getPlayers();
		cards = game.getCards();
		randomCards = new ArrayList<Card>(Arrays.asList(cards));

		game.announces("##################################################################################");
		game.announces("GAME STARTED");
		game.announces("Players:\t" + getObjectStatus(players));
		game.announces("Cards:\t" + getObjectStatus(cards));
		game.announces("##################################################################################");
		game.announces();
	}

	public static void main(String[] args) {
		initialize(0);

		int roundNum = 0;
		while (!game.isGameEnd()) {
			game.announces();
			game.announces("==================================================================================");
			game.announces(String.format("ROUND %2d  ", ++roundNum));
			game.announces("Players:\t" + getObjectStatus(players));
			game.announces("==================================================================================");

			preRoundActions();
			Collections.shuffle(randomCards, rand);
			for (int turn = 0; turn < players.length; turn++)
				turnAction(turn);
			roundSummary();
		}

		game.announces();
		game.announces("##################################################################################");
		game.announces("GAME ENDED");
		game.announces("Players:\t" + getObjectStatus(players));
		game.announces("The winner is " + game.getWinner().getName() + "!!");
		game.announces("##################################################################################");
	}

	private static void preRoundActions() {
		ArrayList<GameAction> actions = new ArrayList<GameAction>();
		for (Player player : players) {
			ArrayList<GameObject> gameInfo = new ArrayList<GameObject>();
			if (player.getRole() instanceof PreRoundActable) {
				Collections.addAll(gameInfo, player.getRole() instanceof Gambler ? players : cards);
				Collections.shuffle(gameInfo, rand);
			}
			actions.add(new GameAction(gameInfo));
		}
		game.getCurrentRound().doPreRoundActions(players, actions.toArray(GameAction[]::new));

		/* -------------------- Print Game Details -------------------- */
		game.announces("> Pre-round Actions");
		for (Player player : players) {
			if (player.getRole() instanceof PreRoundActable) {
				game.privatesTo(player, "You have   the "
						+ (player.getRole() instanceof Gambler ? "players' order" : "cards' slot") + " as follows: ");
				game.privatesTo(player,
						"  " + (player.getRole() instanceof Gambler
								? getObjectStatus(game.getCurrentRound().getPlayers())
								: getSlotReveal(game.getCurrentRound().getCards())));
			}
		}
		game.announces();
		game.announces("> Current Slots:\t" + getSlotStatus(game.getCurrentRound().getCards()));
		game.announces();
		/* -------------------- ------------------ -------------------- */
	}

	private static void turnAction(int turn) {
		Player player = game.getCurrentRound().getPlayerAt(turn);
		ArrayList<GameObject> gameInfo = new ArrayList<GameObject>();
		if (player.getRole() instanceof PreTurnActable) {
			Collections.addAll(gameInfo, player.getRole() instanceof Gambler ? players : cards);
			Collections.shuffle(gameInfo, rand);
			gameInfo.removeIf(obj -> obj instanceof Card && ((Card) obj).isPickedUp());
			while (gameInfo.size() > 2)
				gameInfo.remove(gameInfo.size() - 1);
		}

		GameAction preTurnAction = new GameAction(gameInfo);
		Card chosenCard = randomCards.get(turn);
		game.getCurrentRound().doTurnAction(player, preTurnAction, chosenCard);

		/* -------------------- Print Game Details -------------------- */
		game.announces("> [Turn: " + (turn + 1) + "] Now it is " + player.getName() + "'s turn.");
		if (player.getRole() instanceof PreTurnActable) {
			String message = "You have chosen ";
			message += player.getRole() instanceof Gambler
					? "to swap (at the end of this round) the role and card of these players:"
					: "to view and swap the slot of these cards: ";
			game.privatesTo(player, message);
			game.privatesTo(player,
					"  " + (player.getRole() instanceof Gambler ? getObjectStatus(gameInfo) : getSlotReveal(gameInfo)));
		}
		game.privatesTo(player, String.format("You have chosen a card at slot (S%d).", chosenCard.getSlot()));
		game.announces(String.format("  %s has chosen a card at slot (S%d) which has multiplier (%2d).",
				player.getName(), chosenCard.getSlot(), chosenCard.getMultiplier()));
		game.announces();
		game.announces("> Current Slots:\t" + getSlotStatus(game.getCurrentRound().getCards()));
		game.announces();
		/* -------------------- ------------------ -------------------- */
	}

	private static void roundSummary() {
		game.getCurrentRound().roundSummarize();

		/* -------------------- Print Game Details -------------------- */
		ArrayList<Player> pair = game.getCurrentRound().getToBeSwappedPlayers();
		game.announces("> [Round Summary]");
		game.announces(String.format("  The role & card of (%s) and (%s) are swapped.", pair.get(0).getName(),
				pair.get(1).getName()));
		game.announces("  Rewards: " + getPlayerRewards());
		game.announces();
		/* -------------------- ------------------ -------------------- */

		game.getCurrentRound().clear();
	}

	private static String getObjectStatus(GameObject[] gameObjects) {
		return getObjectStatus(Arrays.asList(gameObjects));
	}

	private static String getObjectStatus(List<GameObject> gameObjects) {
		ArrayList<String> info = new ArrayList<String>();
		gameObjects.forEach((obj) -> info.add(obj.toString()));
		return String.join(" ", info);
	}

	private static String getSlotStatus(GameObject[] cards) {
		return getSlotStatus(Arrays.asList(cards));
	}

	private static String getSlotStatus(List<GameObject> cards) {
		ArrayList<String> info = new ArrayList<String>();
		cards.forEach((card) -> info.add(((Card) card).getStatus()));
		return String.join(" ", info);
	}

	private static String getSlotReveal(GameObject[] cards) {
		return getSlotReveal(Arrays.asList(cards));
	}

	private static String getSlotReveal(List<GameObject> cards) {
		ArrayList<String> info = new ArrayList<String>();
		cards.forEach((card) -> info.add(((Card) card).revealInfo()));
		return String.join(" ", info);
	}

	private static String getPlayerRewards() {
		ArrayList<String> info = new ArrayList<String>();
		for (Player player : players) {
			int rewards = player.getRoundRewards();
			info.add(String.format("[%s: %c%2d ]", player.getName(), rewards < 0 ? '-' : '+', Math.abs(rewards)));
		}
		return String.join(" ", info);
	}

}
