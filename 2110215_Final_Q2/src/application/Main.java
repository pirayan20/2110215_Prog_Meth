package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Random;

import data.Player;
import data.PlayerField;

public class Main extends Application {
  	protected static PlayerField playerField;
  	private ThreadMain threadMain;
  	private static Button summonPlayer;	
  	private static Button switchLight;
  	private static Button timeUp;
  	public int frames = 10;
  	public String title = "B Us";
  	public static int width = 600;
  	public static int height = 400;
  	public static Random random = new Random();
  	Canvas canvas = new Canvas();
  	static Pane pane = new Pane();
  	GraphicsContext ctx = canvas.getGraphicsContext2D();
  	private static final Image background = new Image("FieldBackground.png");
    
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
    	threadMain = new ThreadMain();
    	setup();
		canvas.setHeight(height);
		canvas.setWidth(width);
		playerField = new PlayerField();

		VBox root = new VBox();
		HBox controlTab = new HBox();
		controlTab.setAlignment(Pos.CENTER);
		summonPlayer = new Button("Summon Player");
		switchLight = new Button("Red Light");
		timeUp = new Button("Time Up");
		controlTab.getChildren().addAll(summonPlayer,switchLight,timeUp);
		root.getChildren().addAll(controlTab, pane);
		stage.setTitle(title);
		stage.setScene(new Scene(root, width, height));
		stage.show();
		drawBackground();
		
		summonPlayer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//playerField.addPlayer(); 
				//threadMain.initalizeNewPlayer(playerField.getPlayerCount()-1);
				threadMain.initalizeNewPlayer(playerField.addPlayer());
			}
		});
		switchLight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				playerField.killAllRunPlayer(); 
			}
		});
		
		timeUp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				playerField.killAllPlayer(); 
			}
		});
		
		canvas.requestFocus();
    }
	
	void setup() {
		title = "Squid Us";
		width = 1200;
		height = 700;
	}
	void drawBackground() {
		ImageView backgroundImgView = new ImageView(background);	
		addToPane(backgroundImgView);
	}

	protected static void drawPlayer(Player b) {
		ImageView im = b.getImageView();
        removeFromPane(im);
        im.relocate((double)(b.getX()), (double)(b.getY()));
        addToPane(im);

	}
	/*
	protected static void turnAlarm() {
		isAlarmOn = !isAlarmOn;
		ImageView im = alarm.getImageView();
        removeFromPane(im);
        im.relocate(0, (double)0);
        if(isAlarmOn)addToPane(im);

	}
	*/
	 public static void addToPane(ImageView imageview) {
	        pane.getChildren().add(imageview);
	}

	public static void removeFromPane(ImageView imageview) {
	        pane.getChildren().remove(imageview);
	}
}