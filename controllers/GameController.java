/* Phil Yang
 * HW10
 * ITP368 Ocean
 */

package controllers;

import application.Main;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Light;
import models.LightGame;
import views.GameView;
import views.WinView;

public class GameController {
	private Main myGame;
	private Stage myStage;
	private HBox bulbBox = new HBox();
	private GameView gameView;
	private LightGame lightGame;
	private boolean gameOver = false;
	private int numLights;
	private Media sounds;
	private MediaPlayer mPlayer;

	private IntegerProperty numOfClicks = new SimpleIntegerProperty(0);

	public GameController(GameView gv) {

		// gameView comes with a LightGame that has a NOT winning pattern
		gv.setController(this);
		gameView = gv;
		lightGame = gv.getLightGame();
		numLights = gv.getLightGame().getLights().length;

		// EXTRA CREDIT WORTHY: Background music
		sounds = new Media(getClass().getResource("/controllers/sounds/backgroundNoise.mp3").toExternalForm());
		mPlayer = new MediaPlayer(sounds);
		mPlayer.setVolume(0.2);
		mPlayer.setAutoPlay(true);

	}

	public void setMain(Main app, Stage primaryStage) {
		myGame = app;
		myStage = primaryStage;
	}

	public void setUpLightEventHandler() {
		Light[] lights = lightGame.getLights();

		for (int i = 0; i < lights.length; i++) {
			int index = i;
			lights[i].setOnAction(e -> {
				lights[index].update();
				if (index == numLights - 1)
					lightGame.getLights()[index - 1].update();
				else if (index == 0)
					lightGame.getLights()[index + 1].update();
				else {
					lightGame.getLights()[index + 1].update();
					lightGame.getLights()[index - 1].update();
				}
				incrementClicks();

				boolean check = gameOver();
				if (check)
					transitionScene();
			});

			// EXTRA CREDIT WORTHY: the bulbs slide up and down when you hover on and off
			lights[i].setOnMouseEntered(me -> {
				lights[index].slideUpLight();
			});
			lights[i].setOnMouseExited(me -> {
				lights[index].slideDownLight();
			});

		}
	}

	public void setUpButtonEventHandlers() {

		gameView.getMenuButton().setOnMouseClicked(me -> {
			myGame.start(myStage);
		});
		gameView.getMenuButton().setOnMouseEntered(me -> {
			Image replaceImg = new Image("/views/images/menuButtonHover.png");
			gameView.getMenuButton().setImage(replaceImg);
		});
		gameView.getMenuButton().setOnMouseExited(me -> {
			Image replaceImg = new Image("/views/images/menuButton.png");
			gameView.getMenuButton().setImage(replaceImg);
		});

		// EXTRA CREDIT WORTHY: reset button that flickers
		gameView.getResetButton().setOnMouseClicked(me -> {
			myGame.startGame(numLights);
		});
		gameView.getResetButton().setOnMouseEntered(me -> {
			Image replacementImg = new Image("/views/images/resetHover.png");
			gameView.getResetButton().setImage(replacementImg);
		});
		gameView.getResetButton().setOnMouseExited(me -> {
			Image replacementImg = new Image("/views/images/reset.png");
			gameView.getResetButton().setImage(replacementImg);
		});
	}

	public void startWinScene() {
		WinView wv = new WinView(numOfClicks.intValue(), lightGame.getLights().length);
		WinController wc = new WinController(wv);
		wc.setMain(myGame, myStage);
		wc.showScene();
	}

	// this prepares the hbox that holds all the bulbs so I can drop this into a
	// game screen that has already been made
	public HBox setUpBulbs() {
		bulbBox.setAlignment(Pos.CENTER);
		bulbBox.setMinWidth(600);
		bulbBox.setMinHeight(540);
		bulbBox.setSpacing(2);
		for (int i = 0; i < numLights; i++)
			bulbBox.getChildren().add(lightGame.getLights()[i]);
		return bulbBox;
	}

	public void showScene(Stage myStage) {
		// gameView prepares my game screen and I just drop off the hbox of light bulbs
		gameView.setUpRoot(setUpBulbs());

		// I already know that I do not have a winning pattern because the LightGame
		// constructor will call itself again if it produces
		// a winning pattern. I call checkWin here solely to grab an alpha value to set
		// my background opacity
		double alpha = lightGame.checkWin();
		gameView.getBackgroundColor().setOpacity(alpha);

		// bind the label with the number of clicks so it auto updates
		gameView.getCounterLabel().textProperty().bind(numOfClicks.asString());
		Scene gameScene = new Scene(gameView.getRoot(), 600, 600);
		myStage.setScene(gameScene);
		setUpLightEventHandler();
		setUpButtonEventHandlers();
		myStage.show();
	}

	public GameView getGameView() {
		return gameView;
	}

	public void incrementClicks() {
		numOfClicks.set(numOfClicks.intValue() + 1);
	}

	// EXTRA CREDIT WORTHY: call checkWin to see if I won and if not then I will
	// change the brightness of my scene
	public boolean gameOver() {
		double alpha = lightGame.checkWin();

		// if alpha is -1 then user won so I want to show the monster dying
		if (alpha < 0) {
			gameView.getBackgroundColor().setOpacity(0);
			return true;
		}

		// EXTRA CREDIT WORTHY:
		// if alpha is 16, that means that the user has clicked 8 bulbs and still has
		// not won, so I added a jump scare
		else if (alpha == 16 || numOfClicks.intValue() % 8 == 0) {
			jumpScare();
			return false;
		}
		// EXTRA CREDIT WORTHY: the monster remains hidden unless 8 bulbs are clicked or
		// all bulbs are off. The less bulbs that are on,
		// the darker the scene gets
		else {
			gameView.getMonster().setOpacity(0);
			gameView.getBackgroundColor().setOpacity(1 - alpha);
			return false;
		}
	}

	// EXTRA CREDIT WORTHY:
	// A screaming sound when the monster appears
	public void scream() {
		AudioClip scream = new AudioClip(getClass().getResource("/controllers/sounds/scream.mp3").toString());
		scream.setVolume(1);
		scream.play();
	}

	// to create the effect that the monster is dying, I shake the image and I added
	// a sound here so this transition has audio
	public RotateTransition dying() {
		RotateTransition newFT = new RotateTransition(Duration.millis(100), gameView.getMonster());
		newFT.setByAngle(5);
		newFT.setAutoReverse(true);
		newFT.setCycleCount(15);
		AudioClip dying = new AudioClip(getClass().getResource("/controllers/sounds/dying.mp3").toString());
		dying.setVolume(.3);
		dying.play();
		return newFT;
	}

	// shows the monster and outputs audio
	public void jumpScare() {
		gameView.getMonster().setOpacity(1);
		scream();
	}

	// transition after all the BULBS ARE ON and the user has "killed" the monster
	// and thus wins the game
	public void transitionScene() {
		SequentialTransition st = new SequentialTransition();
		ParallelTransition pt1 = new ParallelTransition();
		FadeTransition fadeGridOut = new FadeTransition(Duration.millis(2000), gameView.getGrid());
		FadeTransition fadeColorOut = new FadeTransition(Duration.millis(2000), gameView.getBackgroundColor());
		FadeTransition fadeMonsterIn = new FadeTransition(Duration.millis(2000), gameView.getMonster());
		fadeGridOut.setToValue(0);
		fadeColorOut.setToValue(0);
		fadeMonsterIn.setToValue(1);
		pt1.getChildren().addAll(fadeGridOut, fadeColorOut, fadeMonsterIn);
		TranslateTransition slideMonster = new TranslateTransition(Duration.millis(2000), gameView.getMonster());
		slideMonster.setByY(1000);
		slideMonster.setAutoReverse(false);
		st.getChildren().addAll(pt1, dying(), slideMonster);
		st.play();
		st.setOnFinished(event -> startWinScene());

	}
}
