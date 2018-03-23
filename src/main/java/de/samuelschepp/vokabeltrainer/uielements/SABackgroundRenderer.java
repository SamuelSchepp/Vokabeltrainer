package de.samuelschepp.vokabeltrainer.uielements;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SABackgroundRenderer {
	

	Image backgroundTexture;
	SABubble[] bubbles;

	public SABackgroundRenderer(GameContainer gc) throws SlickException {
		bubbles = new SABubble[15];
		for (int i = 0; i < bubbles.length; i++) {
			bubbles[i] = new SABubble(gc);
		}
		backgroundTexture = new Image("textures/background.png");
	}
	
	public void update(GameContainer gc) {
		for(SABubble bubble : bubbles) {
			bubble.update(gc);
		}
	}

	public void render(Graphics g) {
		backgroundTexture.draw();
		for(SABubble bubble : bubbles) {
			bubble.render(g);
		}
	}
}
