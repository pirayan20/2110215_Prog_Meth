package game.logic;

import java.util.ArrayList;

import game.object.GameObject;

public class GameAction {

	public final int length;
	private ArrayList<GameObject> info;

	public GameAction(ArrayList<GameObject> info) {
		this.length = info.size();
		this.info = info;
	}

	public ArrayList<GameObject> getInfo() {
		return info;
		
	}

	public GameObject getInfo(int slot) {
		return info.get(slot);
	}

}