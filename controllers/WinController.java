/* Phil Yang
 * HW10
 * ITP368 Ocean
 */

package controllers;

import application.Main;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import views.WinView;

public class WinController {

	private WinView winView;
	private Main myGame;
	private Stage myStage;

	public WinController(WinView wv) {
		winView = wv;
		setUpEventHandlers();
	}

	public void setMain(Main myGame, Stage primaryStage) {
		this.myGame = myGame;
		myStage = primaryStage;
	}

	public void setUpEventHandlers() {
		winView.getPlayAgainButton().setOnMouseClicked(me -> {
			myGame.startGame(winView.getNumLights());
		});

		winView.getMenuButton().setOnMouseClicked(me -> {
			myGame.start(myStage);
		});

		// EXTRA CREDIT WORTHY STUFF: flicker the text images so the user can tell
		// they're buttons
		winView.getPlayAgainButton().setOnMouseEntered(me -> {
			Image replaceImg = new Image("/views/images/playAgainHover.png");
			winView.getPlayAgainButton().setImage(replaceImg);
		});
		winView.getPlayAgainButton().setOnMouseExited(me -> {
			Image replaceImg = new Image("/views/images/playAgain.png");
			winView.getPlayAgainButton().setImage(replaceImg);
		});

		winView.getMenuButton().setOnMouseEntered(me -> {
			Image replaceImg = new Image("/views/images/menuButtonHover.png");
			winView.getMenuButton().setImage(replaceImg);
		});
		winView.getMenuButton().setOnMouseExited(me -> {
			Image replaceImg = new Image("/views/images/menuButton.png");
			winView.getMenuButton().setImage(replaceImg);
		});
	}

	public void showScene() {
		winView.setUpScene();
		Scene newScene = new Scene(winView.getRoot(), 600, 600);
		myStage.setScene(newScene);
		myStage.show();
	}
}
