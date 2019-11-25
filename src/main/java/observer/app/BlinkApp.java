package observer.app;

import observer.controller.BlinkController;
import observer.model.Led;

public class BlinkApp {
	
	static BlinkController blinkController;
	static Led led;
	
	public static Led getLed() {
		led = led == null ? new Led() : led;
		return led;
	}
	
	public static BlinkController getBlinkController () {
		blinkController = blinkController == null ? new BlinkController(getLed()) : blinkController;
		return blinkController;
	}

}
