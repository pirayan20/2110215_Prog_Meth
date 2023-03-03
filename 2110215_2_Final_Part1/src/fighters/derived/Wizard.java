package fighters.derived;

import fighters.base.Attackable;
import fighters.base.Unit;

public class Wizard extends Unit implements Attackable{
	
	public Wizard(int maxHealth, int speed, int power, int location) {
		super("Wizard","w",maxHealth,speed,location,true);
		setPower(power);
		setRange(2);
	}

	@Override
	public int attack(Unit e) {
		if (e.sameTeam(this) || this.getRange() < Math.abs(this.getLocation() - e.getLocation())) {
			return -1;
		} else {
			return this.getPower();
		}
	}
}
