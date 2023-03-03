package data;

import java.util.Random;

import application.Main;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Player {
	public static Random random = new Random();
	static int spawnLocationCount = 0;
	int x, y, r, speedX, speedY;
	int c;
	float timer;
	float runTime;
	float stopTime;
	float deadTime;
	boolean isDead;
	boolean isStop;
	boolean isDespawn;
	ImageView imageView;
	Animation animation;
	public Player(int x) {
		this.x = x;
		this.y = 10 + spawnLocationCount*57;
		spawnLocationCount++;
		if(spawnLocationCount>=10) spawnLocationCount = 0;
		this.c = random.nextInt(3);
		isDead = false;
		isStop = false;
		r = 30 + random.nextInt(50);
		speedX = 3 + random.nextInt(5) ;
		speedY = 0;
		runTime = 1.2f + (float)(random.nextInt(30))/10 ;
		stopTime = 0.6f + (float)(random.nextInt(20))/10 ;
		deadTime = 3.0f;
		createFirstSprite();
		createSprite();
	}
	/////////////////////////////////////// Player Movement //////////////////////////////////////	
	public void update() {
		if(isDead) {
			if(deadTime<timer) {
				despawn();
			}
		}
		if(this.x >= 1300) {
			isDead = true;
			timer = 4.5f;
		}
		
		if(!(isDead || isStop)) {
			this.x+= speedX;
			this.y+= speedY;
		}
		timer += 0.05f;
		if(!isDead) {
			if(isStop) {
				if(stopTime<timer) {
					timer = 0;
					unStop();
				}
			}
			else {
				if(runTime<timer) {
					timer = 0;
					stop();
				}
			}
		}
		
		
			

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getR() {
		return r;
	}

	public int getC() {
		return c;
	}
	
	public boolean isStop() {
		return isStop;
	}
	public boolean isDead() {
		return isDead;
	}
	public boolean isDespawn() {
		return isDespawn;
	}


	public ImageView getImageView() {
		return imageView;
	}
	
	public void kill() {
		
		isDead = true;
		createSprite();
	}
	public void stop() {
		
		isStop = true;
		createSprite();
	}
	public void unStop() {
		
		isStop = false;
		createSprite();
	}
	public void despawn() {
		
		isDespawn = true;
		createSprite();
	}
	public float getTimer() {
		return timer;
	}
	
	/////////////////////////////////////// Player Animation //////////////////////////////////////
	
	 private static final Image IMAGES = new Image("New Among Us Walk Sprite Squid Game.png");
	 private static final Image IMAGESD = new Image("New Among Us Dead Sprite Squid.png");
	 private static final Image IMAGESDS = new Image("Empty Sprite.png");
	 private static final int COLUMNS  =   12;
	 private static final int COUNT    = 12;
	 private static final int OFFSET_X =  0;
	 private static final int OFFSET_Y =  0;
	 private static final int WIDTH    = 85;
	 private static final int HEIGHT   = 140;
	    
	void createFirstSprite() {
		imageView = new ImageView(IMAGES);
	 }
	 
	void createSprite() {
		///imageView = new ImageView(IMAGES);
		if (isDespawn) {
			imageView.setImage(null);
			imageView = new ImageView(IMAGESDS);
		}
		else if(isDead) {
			imageView.setImage(null);
			animation.stop();
			imageView = new ImageView(IMAGESD);
		}

		else if (isStop) {
			imageView.setImage(null);
			animation.stop();
			imageView = new ImageView(IMAGES);
		}

		imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));
		imageView.relocate((double)(getX()), (double)(getY()));
		//imageView.relocate(arg0, arg1);
		if(!(isDead || isStop)) {
			animation = new SpriteAnimation(
		             imageView,
		             Duration.millis(500),
		             COUNT, COLUMNS,
		             OFFSET_X, OFFSET_Y,
		             WIDTH, HEIGHT
				);
				animation.setCycleCount(Animation.INDEFINITE);
				animation.play();			
		}
	}
	
}
