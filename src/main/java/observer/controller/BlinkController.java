package observer.controller;

import observer.model.Led;

public class BlinkController {
	
	private Led led;
	
	public  BlinkController(Led led) {
		this.led = led;
	}
	
	public void toggle() {
		
		led.setLedOn(led.isLedOn() ? false : true);
		
	}

}
