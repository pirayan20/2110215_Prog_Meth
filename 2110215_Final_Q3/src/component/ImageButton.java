package component;

import application.ImageButtonType;
import application.Main;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.TodoLogic;

public class ImageButton extends ImageView {
	private static final String addButtonUrl = "/res/plus-icon.png";
	private static final String removeButtonUrl = "/res/minus-icon.png";

	public ImageButton(ImageButtonType imageButtonType) {
		super();
		initImageButton(imageButtonType);
		initEventHandler(imageButtonType);
	}
	private void initImageButton(ImageButtonType imageButtonType) {
        //TODO
        this.setFitHeight(26);
        this.setFitWidth(26);
        if (imageButtonType == ImageButtonType.ADD) {
            this.setImage(new Image(addButtonUrl));
        } else if (imageButtonType == ImageButtonType.REMOVE) {
            this.setImage(new Image(removeButtonUrl));
        }
    }
    private void initEventHandler(ImageButtonType imageButtonType) {
        //TODO
        this.setCursor(Cursor.HAND);
        this.setOnMouseClicked((MouseEvent) -> {
        	if (imageButtonType == ImageButtonType.ADD) {
                TodoLogic.addTodo(Main.getAdderBarCurrentText());
            } else if (imageButtonType == ImageButtonType.REMOVE) {
                TodoLogic.removeTodo(this);
            }
        });
        
    }
}