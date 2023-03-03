package fighters.derived;

import fighters.base.Attackable;
import fighters.base.Guardable;
import fighters.base.Unit;
import logic.BattleUtils;

public class Guildmaster extends Unit implements Attackable,Guardable{
	
	public Guildmaster(int maxHealth, int speed, int power, int defense, int location) {
		super("Guildmaster","G",maxHealth,speed,location,false);
		setPower(power);
		setDefense(defense);
		setRange(1);
	}
	
	@Override
	public boolean move(int spaces) {
		return super.move(-1);
	}
	 
	public void guard() {}
	
	public int attack(Unit e) {
		if (e.sameTeam(this) || this.getRange() < Math.abs(this.getLocation() - e.getLocation())) {
			return -1;
		} else {
			return BattleUtils.calculateDamage(this.getPower(),e);
		}
	}
}
