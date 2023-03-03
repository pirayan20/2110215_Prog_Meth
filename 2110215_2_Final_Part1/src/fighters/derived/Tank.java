package fighters.derived;

import fighters.base.Guardable;
import fighters.base.Unit;

public class Tank extends Unit implements Guardable{

	public Tank(int maxHealth,int speed, int defense, int location) {
		super("Tank","t",maxHealth,speed,location,true);
		setDefense(defense);
	}
	
	@Override
	public void guard() {
		this.setOnGuard(true);
	}
	
	@Override
	public boolean move(int spaces) {
		this.setOnGuard(false);
		return super.move(spaces);
	}
}