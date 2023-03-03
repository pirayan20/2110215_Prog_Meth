package game.object;

import game.logic.GameLogic;

public class Card extends GameObject {

	private String id;
	private int slot;
	private int multiplier;
	private boolean pickedUp;

	public Card(String id, int multiplier) {
		setId(id);
		setSlot(-1);
		setMultiplier(multiplier);
		setPickedUp(false);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
		if (0 <= slot && slot < GameLogic.getInstance().getNumCards())
			GameLogic.getInstance().getCurrentRound().setCardAt(slot, this);
	}

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

	@Override
	public String toString() {
		return String.format("[%s: %2d]", id, multiplier);
	}

	public String getStatus() {
		return pickedUp ? revealInfo() : String.format("[S%d|####]", slot);
	}

	public String revealInfo() {
		return String.format("[S%d| %2d ]", slot, multiplier);
	}

}