package role.derived;

import game.logic.GameAction;
import game.object.Card;
import role.base.PreRoundActable;
import role.base.Role;

public class Master extends Role implements PreRoundActable{
	
	public void preRoundActs(GameAction action) {
		for (int i=0; i<7; i++) {
			Card card = (Card)action.getInfo(i);
			card.setSlot(i);
		}
	}
	
	public String toString() {
		return "Master";
	}
}
