package application;


import data.Player;
import javafx.application.Platform;

public class ThreadMain {
	
	protected void initalizeNewPlayer(Player player) {
		/*====================FILL CODE============================
		Add something on the code below to make new player appear while application
		can be responded*/
		
		updatePlayerMovement(player);						
				
		/*========================================================*/
		
	}
	protected void updatePlayerMovement(Player player) {
		try {		
			while(!player.isDespawn()) {
				
				/*===================!DO NOT CHANGE THIS LINE!===========*/
				Thread.sleep(50);
				/*========================================================*/
				
				/*====================FILL CODE============================
				There is JavaFX commands inside the code below
				Add something to the code below to make JavaFX commands can
				function in the thread
				*/
				// Hint : Player use ImageView, which is related to JavaFX.
				player.update();		
				Main.drawPlayer(player);		
				/*========================================================*/	
			}
		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}		
	}

}
