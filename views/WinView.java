/* Phil Yang
 * HW10
 * ITP368 Ocean
 */

package views;

import javafx.geometry.Insets;
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

public class WinView {
	private int numClicks;
	private int numLights;

	private StackPane root = new StackPane();
	private Image congrats = new Image("views/images/congrats.png");
	private ImageView congratsLabel = new ImageView(congrats);
	private Image clicksText = new Image("views/images/totalClicks.png");
	private ImageView clicksTextLabel = new ImageView(clicksText);
	private Label clicksLabel;
	private Image playAgain = new Image("views/images/playAgain.png");
	private ImageView playAgainButton = new ImageView(playAgain);
	private Image menu = new Image("views/images/menuButton.png");
	private ImageView menuButton = new ImageView(menu);
	private VBox grid = new VBox();
	private StackPane alignTitle = new StackPane();
	private StackPane alignClicksBox = new StackPane();
	private StackPane alignOptionsBox = new StackPane();
	private StackPane alignGrid = new StackPane();
	private HBox clicksBox = new HBox(15);
	private HBox optionsBox = new HBox();
	private Rectangle backgroundColor = new Rectangle(600, 600);

	public WinView(int numClicks, int numLights) {
		this.numClicks = numClicks;
		this.numLights = numLights;
		clicksLabel = new Label(String.valueOf(numClicks));

	}

	public void setEventHandlers() {

	}

	public void setUpScene() {

		grid.setMinSize(600, 550);
		root.setMinSize(600, 600);

		grid.setAlignment(Pos.CENTER);

		optionsBox.setAlignment(Pos.CENTER);
		clicksBox.setAlignment(Pos.CENTER);

		congratsLabel.setFitHeight(100);
		congratsLabel.setPreserveRatio(true);

		clicksTextLabel.setFitWidth(250);
		clicksTextLabel.setPreserveRatio(true);
		clicksLabel.setFont(new Font(45));
		clicksLabel.setTextFill(Color.YELLOW);
		clicksLabel.setPadding(new Insets(0, 0, 10, 0));

		playAgainButton.setFitHeight(85);
		playAgainButton.setPreserveRatio(true);

		menuButton.setFitHeight(85);
		menuButton.setPreserveRatio(true);

		clicksBox.getChildren().addAll(clicksTextLabel, clicksLabel);
		optionsBox.getChildren().addAll(menuButton, playAgainButton);
		grid.getChildren().addAll(alignTitle, alignClicksBox, alignOptionsBox);

		clicksBox.setSpacing(30);
		optionsBox.setSpacing(80);
		grid.setSpacing(30);

		alignTitle.getChildren().add(congratsLabel);
		alignClicksBox.getChildren().add(clicksBox);
		alignOptionsBox.getChildren().add(optionsBox);

		alignGrid.getChildren().add(grid);
		root.getChildren().addAll(backgroundColor, alignGrid);
	}

	public ImageView getPlayAgainButton() {
		return playAgainButton;
	}

	public ImageView getMenuButton() {
		return menuButton;
	}

	public int getNumLights() {
		return numLights;
	}

	public StackPane getRoot() {
		return root;
	}

}
