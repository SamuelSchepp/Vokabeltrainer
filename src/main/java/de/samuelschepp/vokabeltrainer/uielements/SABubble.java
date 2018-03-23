package de.samuelschepp.vokabeltrainer.uielements;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SABubble extends SAObject {

	float alpha;
	float alphaSpeed;
	
	float speedX;
	float speedY;
	
	float scale;
	
	public SABubble(GameContainer gc) throws SlickException {
		super();
		texture = new Image("textures/bubble.png");
		
		init(gc);
	}
	
	public void init(GameContainer gc) {
		
		speedX = (float) (Math.random() * 2 - 1) * 0.5f + 0.02f;
		speedY = (float) (Math.random() * 2 - 1) * 0.5f + 0.02f;
		
		scale = (float) Math.random() + 0.5f;
		
		width = height = 200f * scale;
		
		alpha = 0;
		alphaSpeed = (float) Math.random() / 200f;
		
		x = (float) (Math.random() * (gc.getWidth() - width));
		y = (float) (Math.random() * (gc.getHeight() - height));
	}
	
	public void update(GameContainer gc) {
		x += speedX;
		y += speedY;
		
		alpha += alphaSpeed;
		
		if(x > gc.getWidth() || x < 0 - width) { // TODO
			init(gc);
		}
		if(y > gc.getHeight() || y < 0 - height) {
			init(gc);
		}
	}
	
	public void render(Graphics g) {
		texture.draw(x, y, scale, new Color(1f, 1f, 1f, alpha));
		g.resetTransform();
	}
}
