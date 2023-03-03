package logic;

import application.ImageButtonType;
import application.Main;
import component.ImageButton;
import component.Todo;
import javafx.scene.text.Text;

public class TodoLogic {
	public static void addTodo(String todoText) {
		if (!todoText.isBlank()) {
			ImageButton imageButton = new ImageButton(ImageButtonType.REMOVE);
			Todo todo = new Todo(new Text(todoText), imageButton);
			Main.getRootPane().getTodoListView().getItems().add(todo);
		}
	}

	public static void removeTodo(ImageButton imageButton) {
		Main.getRootPane().getTodoListView().getItems().removeIf(i -> i.getImageButton().equals(imageButton));
	}
}
