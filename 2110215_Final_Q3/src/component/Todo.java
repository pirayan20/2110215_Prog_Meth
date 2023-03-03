package component;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class Todo extends BorderPane{
	private ImageButton imageButton;
	private Text text;
	
	public Todo(Text text, ImageButton imageButton) {
		//TODO
		super();
		this.imageButton = imageButton;
		this.text = text;
		this.setLeft(text);
		this.setRight(imageButton);
	}

	public ImageButton getImageButton() {
		return imageButton;
	}
	
	
}
