/* Phil Yang
 * HW10
 * ITP368 Ocean
 */

package models;

import java.util.Random;

public class LightGame {
	private Light[] lights;

	// this constructor guarantees that I never start with a winning pattern
	public LightGame(int numOfLights) {
		lights = new Light[numOfLights];
		System.out.println("Loading lights...");

		for (int i = 0; i < lights.length; i++) {
			Light newLight = new Light(true, 315 / lights.length);

			newLight.setIndex(i);
			lights[i] = newLight;
		}
		reset();
		while (checkWin() + 1 < 0.001)
			reset();
	}

	public void reset() {
		for (int i = 0; i < lights.length; i++) {
			Random random = new Random();
			lights[i].update(random.nextBoolean());
		}
	}

	public Light[] getLights() {
		return lights;
	}

	// tells me if I won and the opacity of my background
	public double checkWin() {
		int numLit = lights.length;
		for (int i = 0; i < lights.length; i++)
			if (!lights[i].getIsOn())
				numLit--;
		if (numLit == lights.length)
			return -1.0;

		// I want 16 (which is bigger than the max num of bulbs) to tell me to active
		// jump scare
		else if (numLit == 0)
			return 16;
		return 1.0 * numLit / lights.length;
	}

}
