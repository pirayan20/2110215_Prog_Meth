package role.derived;

import game.logic.GameAction;
import game.object.Player;
import role.base.PreRoundActable;
import role.base.PreTurnActable;
import role.base.Role;

public class Gambler extends Role implements PreRoundActable,PreTurnActable{

	public void preRoundActs(GameAction action) {
		for (int i=0; i<4; i++) {
			Player player = (Player)action.getInfo(i);
			player.setOrder(i);
		}
	}
	
	public void preTurnActs(GameAction action) {
		Player player1 = (Player)action.getInfo(0);
		Player player2 = (Player)action.getInfo(1);
		player1.setToBeSwapped(true);
		player2.setToBeSwapped(true);
	}
	
	
	public String toString() {
		return "Gambler";
	}
}
