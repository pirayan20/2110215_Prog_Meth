package component;

import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class RootPane extends VBox {
	private TitlePane title;
	private AdderBar adderBar;
	private TodoListView todoListView;

	public RootPane() {
		super();
		setTitle(new TitlePane());
		setAdderBar(new AdderBar());
		setTodoListView(new TodoListView());
		this.getChildren().addAll(title, adderBar, todoListView);
	}

	public TitlePane getTitle() {
		return title;
	}

	public void setTitle(TitlePane title) {
		this.title = title;
	}

	public AdderBar getAdderBar() {
		return adderBar;
	}

	public void setAdderBar(AdderBar adderBar) {
		this.adderBar = adderBar;
	}

	public ListView<Todo> getTodoListView() {
		return todoListView;
	}

	public void setTodoListView(TodoListView todoListView) {
		this.todoListView = todoListView;
	}


}
