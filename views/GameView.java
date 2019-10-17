/* Phil Yang
 * HW10
 * ITP368 Ocean
 */

package views;

import controllers.GameController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import models.LightGame;

public class GameView {
	private GameController controller;

	private LightGame lightGame;

	private Image image = new Image("views/images/monster.jpg");
	private ImageView imageView = new ImageView(image);
	private StackPane root = new StackPane();
	private VBox grid = new VBox();
	private HBox topRow = new HBox();
	private Image menuImage = new Image("/views/images/menuButton.png");
	private ImageView menuButton = new ImageView(menuImage);
	private Label counterLabel = new Label("Number of Clicks: ");
	private Image counterImage = new Image("/views/images/clickslabel.png");
	private ImageView counterTextLabel = new ImageView(counterImage);
	private HBox counterBox = new HBox(15, counterTextLabel, counterLabel);
	private Rectangle backgroundColor = new Rectangle(600, 600);
	private Image reset = new Image("/views/images/reset.png");
	private ImageView resetButton = new ImageView(reset);
	private HBox resetHolder = new HBox();

	// Take in the number of light bulbs
	public GameView(int numBulbs) {
		lightGame = new LightGame(numBulbs);
		controller = new GameController(this);
	}

	public LightGame getLightGame() {
		return lightGame;
	}

	public void setUpRoot(HBox lightBox) {
		imageView.setOpacity(0);
		menuButton.setFitHeight(30);
		menuButton.setPreserveRatio(true);
		resetButton.setFitHeight(30);
		resetButton.setPreserveRatio(true);
		resetHolder.getChildren().add(resetButton);
		resetHolder.setAlignment(Pos.BASELINE_RIGHT);
		counterTextLabel.setFitHeight(30);
		counterTextLabel.setPreserveRatio(true);
		counterLabel.setFont(new Font(26));
		counterLabel.setTextFill(Color.YELLOW);
		topRow.getChildren().addAll(menuButton, counterBox);
		grid.getChildren().addAll(topRow, lightBox, resetHolder);
		backgroundColor.setFill(Color.BLACK);
		topRow.setSpacing(420);
		root.getChildren().addAll(imageView, backgroundColor, grid);
	}

	public StackPane getRoot() {
		return root;
	}

	public VBox getGrid() {
		return grid;
	}

	public ImageView getMonster() {
		return imageView;
	}

	public Rectangle getBackgroundColor() {
		return backgroundColor;
	}

	public void setController(GameController gc) {
		controller = gc;
	}

	public Label getCounterLabel() {
		return counterLabel;
	}

	public ImageView getMenuButton() {
		return menuButton;
	}

	public ImageView getResetButton() {
		return resetButton;
	}

	// 'single quote' is literal so it will give $error
	// "" will give "Invalid error"
	// change the opacity of the background as the lights fade in/out
	// have a monster's face as the image in the back so as it gets darker, the
	// monster becomes more glowy
	// if possible
}
