package component;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TitlePane extends BorderPane{
	private Text title = new Text();
	public TitlePane() {
		//TODO
		super();
		initTitle();
		this.setBackground(new Background(
				new BackgroundFill(Color.DARKSLATEBLUE, null, null)));
		this.setPrefHeight(100);
		this.setCenter(title);
	}
	private void initTitle() {
		//TODO
		title.setFont(new Font("Roberto",48));
		title.setText("Todo App");
	}
	
	public Text getTitle() {
		return title;
	}
	public void setTitle(Text title) {
		this.title = title;
	}
	
}
