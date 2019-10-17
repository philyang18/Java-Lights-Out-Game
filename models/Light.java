/* Phil Yang
 * HW10
 * ITP368 Ocean
 */

package models;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

public class Light extends Button {
	private boolean isOn;
	private final ImageView image = new ImageView(new Image("views/images/lightbulb.png"));
	private int index;

	public Light(boolean isOn, int width) {
		this.isOn = isOn;
		image.setFitWidth(width);
		image.setPreserveRatio(true);
		setGraphic(image);
		setStyle("-fx-background-color: transparent;");
		getGraphic().setOpacity(isOn ? 1 : .3); // If your light is off, then change the opacity to .3
	}

	// EXTRA CREDIT WORTHY: makes the bulbs move so it's more user friendly and
	// appealing
	public void slideUpLight() {
		setTranslateY(-5);
	}

	public void slideDownLight() {
		setTranslateY(5);
	}

	public boolean getIsOn() {
		return isOn;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void update() {
		isOn = !isOn;
		getGraphic().setOpacity(isOn ? 1 : .3);
		switchSound();
	}

	public void update(boolean isOn) {
		this.isOn = isOn;
		switchSound();
		getGraphic().setOpacity(isOn ? 1 : .3);

	}

	// EXTRA CREDIT WORTHY: makings a clicking sound when you press a bulb to
	// indicate that it worked
	public void switchSound() {
		AudioClip switchSound = new AudioClip(getClass().getResource("/controllers/sounds/switchSound.mp3").toString());
		switchSound.setVolume(0.5);
		switchSound.play();
	}

}
