package application;

import component.RootPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	private static RootPane rootPane;
	
	@Override
    public void start(Stage primaryStage) throws Exception {
		rootPane = new RootPane();
		//TODO
		Scene scene = new Scene(rootPane,640,480);
		
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Todo App");
		primaryStage.show();
		
    }

	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public static String getAdderBarCurrentText() {
		return rootPane.getAdderBar().getTextField().getText();
	}
	
	public static RootPane getRootPane() {
		return rootPane;
	}
}
