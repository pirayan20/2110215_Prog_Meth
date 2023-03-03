package role.derived;

import game.logic.GameAction;
import game.logic.GameLogic;
import game.object.Card;
import role.base.PreTurnActable;
import role.base.Role;

public class Seer extends Role implements PreTurnActable{

	public void preTurnActs(GameAction action) {
		Card card1 = (Card)action.getInfo(0);
		Card card2 = (Card)action.getInfo(1);
		GameLogic.getInstance().getCurrentRound().swapCards(card1, card2);
	}
	
	public String toString() {
		return "Seer";
	}
}
