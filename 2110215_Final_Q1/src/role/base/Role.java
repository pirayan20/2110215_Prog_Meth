package role.base;

import game.object.Card;

public abstract class Role {

	private Card cardInHand;

	public Card getCardInHand() {
		return cardInHand;
	}

	public void picksUpCard(Card chosenCard) {
		cardInHand = chosenCard;
		cardInHand.setPickedUp(true);
	}

	public void dumpsCard() {
		cardInHand.setPickedUp(false);
		cardInHand = null;
	}

	public int getRewards() {
		return cardInHand.getMultiplier() * cardInHand.getSlot();
	}

	@Override
	public abstract String toString();

}
