package de.samuelschepp.vokabeltrainer.engine;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class SATimer {

	public int fps;
	public int milli;
	public boolean running;
	
	public SATimer() {
		fps = 60;
		milli = 0;
		running = false;
	}

	public void update(int delta) {
		if (running) milli += delta;
	}
	
	public boolean isExpired() {
		return false;
	}
	
	public String toString() {
		int seconds = (int) (milli / 1000f);
		int minutes = 0;

		while (seconds > 59) {
			minutes += 1;
			seconds -= 60;
		}
		
		NumberFormat formatter = new DecimalFormat("##00");
		return formatter.format(minutes) + ":" + formatter.format(seconds);
	}
}
