package game.logic;

import java.util.ArrayList;

import game.object.Card;
import game.object.Player;
import role.base.Role;

public class GameRound {

	private Player[] players;
	private Card[] cards;

	public GameRound(int numPlayers, int numCards) {
		players = new Player[numPlayers];
		cards = new Card[numCards];
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		for(int i=0; i< players.length; i++) {
			players[i].setOrder(i);
		}
		
	}

	public Card[] getCards() {
		return cards;
	}

	public void setCards(Card[] cards) {
		for(int i=0; i< cards.length; i++) {
			cards[i].setSlot(i);
		}
	}

	public Player getPlayerAt(int order) {
		return players[order];
	}

	public void setPlayerAt(int order, Player player) {
		players[order] = player;
	}

	public Card getCardAt(int slot) {
		return cards[slot];
	}

	public void setCardAt(int slot, Card card) {
		cards[slot] = card;
	}

	public void swapCards(Card card1, Card card2) {
		int slot1 = card1.getSlot();
		int slot2 = card2.getSlot();
		card1.setSlot(slot2);
		card2.setSlot(slot1);
	}

	public ArrayList<Player> getToBeSwappedPlayers() {
		ArrayList<Player> swapPair = new ArrayList<Player>();
		for (Player player : players)
			if (player.isToBeSwapped())
				swapPair.add(player);
		return swapPair;
	}

	public void roundSummarize() {
		ArrayList<Player> swapPair = getToBeSwappedPlayers();
		Role tempRole = swapPair.get(0).getRole();
		swapPair.get(0).setRole(swapPair.get(1).getRole());
		swapPair.get(1).setRole(tempRole);

		for (Player player : players)
			player.takesRoundRewards();
	}

	public void clear() {
		for (Player player : players) {
			player.setToBeSwapped(false);
			player.getRole().dumpsCard();
			player.setOrder(-1);
		}
		for (Card card : cards) {
			card.setSlot(-1);
		}
	}

	public void doPreRoundActions(Player[] players, GameAction[] actions) {
		for (int i = 0; i < players.length; i++)
			players[i].doPreRoundAction(actions[i]);
	}

	public void doTurnAction(Player player, GameAction preTurnAction, Card chosenCard) {
		player.doPreTurnAction(preTurnAction);
		player.getRole().picksUpCard(chosenCard);
	}
}
