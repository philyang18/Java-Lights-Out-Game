/* Phil Yang
 * HW10
 * ITP368 Ocean
 */

package controllers;

import application.Main;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class OpeningController {

	@FXML
	private Label numLabel;

	@FXML
	private Slider numSlider;

	@FXML
	private ImageView helpButton;

	@FXML
	private ImageView startButton;

	private Main myGame;
	// hard coded num to always start at 3
	private int numBulbs = 3;

	public void setMain(Main app) {
		myGame = app;
	}

	public void initialize() {
		setUpButton();
		setUpSliderLabel();
		System.out.println("hi");
	}

	private void setUpButton() {
		startButton.setOnMouseClicked(t -> {
			// read value of slider
			int numOfBulbs = (int) numSlider.getValue();
			System.out.println("Start button was clicked.");
			myGame.startGame(numOfBulbs);
		});

		// EXTRA CREDIT WORTHY: I added instructions by creating a pop up alert
		helpButton.setOnMouseClicked(me -> {
			Alert alert = new Alert(AlertType.INFORMATION,
					"There is a monster lurking in the dark. Keep as many lights on as possible to keep him away.\nWhen you flip on a light, the adjacent light(s) are effected.",
					ButtonType.OK);
			alert.setTitle("INSTRUCTIONS");
			alert.setHeaderText("INSTRUCTIONS");
			alert.showAndWait();

			if (alert.getResult() == ButtonType.OK)
				alert.close();
		});

		// flickering of buttons - avoided css
		startButton.setOnMouseEntered(me -> {
			Image replaceImg = new Image("views/images/startButtonHover.png");
			startButton.setImage(replaceImg);
		});
		startButton.setOnMouseExited(me -> {
			Image replaceImg = new Image("views/images/startButton.png");
			startButton.setImage(replaceImg);
		});
		helpButton.setOnMouseEntered(me -> {
			Image replaceImg = new Image("views/images/helpButtonHover.png");
			helpButton.setImage(replaceImg);
		});
		helpButton.setOnMouseExited(me -> {
			Image replaceImg = new Image("views/images/helpButton.png");
			helpButton.setImage(replaceImg);
		});
	}

	private void setUpSliderLabel() {
		numSlider.valueProperty().addListener((ChangeListener<Number>) (ov, oldValue, newVal) -> {
			numLabel.setText(String.valueOf(newVal.intValue()));
			numBulbs = newVal.intValue();
		});
	}

	public int getNumBulbs() {
		return numBulbs;
	}
}
