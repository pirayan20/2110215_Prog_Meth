package game.object;

import game.logic.GameAction;
import game.logic.GameLogic;
import role.base.PreRoundActable;
import role.base.PreTurnActable;
import role.base.Role;

public class Player extends GameObject {

	private String name;
	private int order;
	private Role role;
	private int marblesEarned;
	private boolean toBeSwapped;

	public Player(String name, Role role) {
		setName(name);
		setOrder(-1);
		setRole(role);
		setMarblesEarned(GameLogic.INITIAL_MARBLES);
		setToBeSwapped(false);
	}

	
	public void doPreRoundAction(GameAction action) {
		//TODO
		if (this.getRole() instanceof PreRoundActable) {
			PreRoundActable x = (PreRoundActable)this.getRole();
			x.preRoundActs(action);
		}
	}

	public void doPreTurnAction(GameAction action) {
		//TODO
		if (this.getRole() instanceof PreTurnActable) {
			PreTurnActable x = (PreTurnActable)this.getRole();
			x.preTurnActs(action);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
		if (0 <= order && order < GameLogic.getInstance().getNumPlayers())
			GameLogic.getInstance().getCurrentRound().setPlayerAt(order, this);
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role playerRole) {
		this.role = playerRole;
	}

	public int getMarblesEarned() {
		return marblesEarned;
	}

	public void setMarblesEarned(int marblesEarned) {
		this.marblesEarned = Math.max(0, marblesEarned);
	}

	public boolean isToBeSwapped() {
		return toBeSwapped;
	}

	public void setToBeSwapped(boolean toBeSwapped) {
		this.toBeSwapped = toBeSwapped;
	}

	public int getRoundRewards() {
		Card card = role.getCardInHand();
		return card.getMultiplier() * card.getSlot();
	}

	public void takesRoundRewards() {
		setMarblesEarned(marblesEarned + getRoundRewards());
	}

	@Override
	public String toString() {
		return String.format("[%s: %2d]", name, marblesEarned);
	}
}
