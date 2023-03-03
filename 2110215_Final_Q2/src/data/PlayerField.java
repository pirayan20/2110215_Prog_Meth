package data;

import java.util.ArrayList;
import java.util.Random;
import application.Main;

public class PlayerField {
	private final ArrayList<Player> playerList;	
	private int playerCount = 0;

	private int playerKillCount = 0;
	private int playerStopCount = 0;
	public static Random random = new Random();

	public PlayerField(){
		playerList = new ArrayList<Player>();
	}	
		
	public Player addPlayer() {
        Player p = new Player(-10);
		playerList.add(p);
		playerCount++;
		return p;
	}

	public void killPlayer() {
		if(playerKillCount < playerCount) {
			playerList.get(playerKillCount).kill();
			playerKillCount++;
		}

	}
	
	public void killAllRunPlayer() {
		for(int i = 0 ; i < playerList.size() ; i++) {
			if(!playerList.get(i).isStop()) {
				playerList.get(i).kill();
				playerKillCount++;
			}
				
		}

	}
	
	public void killAllPlayer() {
		for(int i = 0 ; i < playerList.size() ; i++) {
			playerList.get(i).kill();
			playerKillCount++;
		}

	}
	
	
	
	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	public int getPlayerCount() {
		return playerCount;
	}

	public void setPlayerCount(int pCount) {
		this.playerCount = pCount;
	}
	
}


