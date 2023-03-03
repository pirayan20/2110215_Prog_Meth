package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

//You might need to do something to the following line
public class ControlPane extends VBox{
	
	private Text drawnNumberText = new Text();
	private Text drawCountText = new Text();
	private Text bingoText = new Text();
	private Button drawButton = new Button();
	private Button newRoundButton = new Button();
	private NumberGrid numberGrid = new NumberGrid();

	
	public ControlPane(NumberGrid numberGrid) {
		// TODO
		this.numberGrid = numberGrid;
		this.setAlignment(Pos.CENTER);
		this.setPrefWidth(300);
		this.setSpacing(20);
		this.setBorder(new Border(
				new BorderStroke(Color.LIGHTGRAY,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
		this.drawnNumberText.setFont(new Font(20));
		//initializeDrawCounterText();
		initializeBingoText();
		initializeDrawButton();
		initializeNewRoundButton();
		this.getChildren().addAll(this.drawnNumberText,this.drawCountText
				,this.bingoText,this.drawButton,this.newRoundButton);
		BingoUtil.setTextsBeginning(drawnNumberText, drawCountText);
	}
	
	private void initializeBingoText() {
		// TODO
		bingoText.setText("Bingo!!");
		bingoText.setFont(new Font(40));
		bingoText.setVisible(false);
		
	}

	private void initializeDrawButton() {
		// TODO
		drawButton.setText("Draw a number");
		drawButton.setPrefWidth(100);
		drawButton.setOnAction((event) -> {
			drawButtonHandler();
		});
	}

	private void initializeNewRoundButton() {
		// TODO
		newRoundButton.setText("New Round");
		newRoundButton.setPrefWidth(100);
		newRoundButton.setDisable(true);
		newRoundButton.setOnAction((event) -> {
			newRoundButtonHandler();
		});
	}
	
	private void drawButtonHandler() {
		// TODO
		int x = BingoUtil.drawNumber();
		numberGrid.highlightNumber(x);
		if (BingoUtil.isBingo(numberGrid)) {
			bingoText.setVisible(true);
			drawButton.setDisable(true);
			newRoundButton.setDisable(false);
		}
		BingoUtil.updateTextsAfterDrawn(x, drawnNumberText, drawCountText);
		
	}
	
	private void newRoundButtonHandler() {
		// TODO
		BingoUtil.assignRandomNumbers(numberGrid.getNumberSquares());
        bingoText.setVisible(false);
        drawButton.setDisable(false);
        newRoundButton.setDisable(true);
		BingoUtil.setTextsBeginning(drawnNumberText, drawCountText);
	}

}
