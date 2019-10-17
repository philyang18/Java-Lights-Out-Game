/* Phil Yang
 * HW10
 * ITP368 Ocean
 */

package application;

import controllers.GameController;
import controllers.OpeningController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import views.GameView;

public class Main extends Application {
	private Stage myStage;
	private OpeningController oc;
	private GameController gc;
	private FXMLLoader loader;

	public Main() {
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			myStage = primaryStage;
			myStage.setResizable(false);
			loader = new FXMLLoader(getClass().getResource("/views/OpeningScene.fxml"));
			StackPane root = (StackPane) loader.load();
			oc = loader.getController();
			oc.setMain(this); // to give the controller reference back to this object
			Scene scene = new Scene(root, 600, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void startGame(int numLights) {

		GameView gv = new GameView(numLights);
		gc = new GameController(gv);
		gc.setMain(this, myStage);
		gc.showScene(myStage);
	}

}
