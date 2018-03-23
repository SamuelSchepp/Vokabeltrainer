package de.samuelschepp.vokabeltrainer.uielements;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import de.samuelschepp.vokabeltrainer.engine.SAVoc;

public class SACard extends SAObject {

	public SAVoc voc;
	
	float fastSpeed;
	float slowSpeed;
	
	float acc;
	float currentSpeed;
	
	boolean shouldSlower;
	boolean shouldFaster;
	
	float border;
	
	TrueTypeFont font;
	
	public int rotation;
	
	public boolean done;
	
	public SACard(int difficulty) throws SlickException {
		super();
		texture = new Image("textures/vocCard.png");
		font = new TrueTypeFont(new java.awt.Font("Arial", java.awt.Font.TRUETYPE_FONT, 40), true);
		
		init(difficulty);
	}
	
	public void init(int difficulty) {
		border = 45;
		height = 208;
		width = 294;
		
		y = 0 - 2*border - height - 10;
		x = 350;
		setRotation();
		
		done = false;
		
		voc = new SAVoc();
		fastSpeed = 25;
		slowSpeed = 0.4f+(float)difficulty / 0.4f;
		acc = 1.1f;
		currentSpeed = fastSpeed;
		
		shouldFaster = false;
		shouldSlower = true;
	}
	
	public void update(GameContainer gc) {
		if(shouldSlower) {
			currentSpeed -= acc; 
			if(currentSpeed < slowSpeed) {
				shouldSlower = false;
				currentSpeed = slowSpeed;
			}
		}
		if(shouldFaster) {
			currentSpeed += acc;
			if(currentSpeed > fastSpeed) {
				shouldFaster = false;
				currentSpeed = fastSpeed;
			}
		}
		
		if(y > gc.getHeight() - height) {
			shouldFaster = true;
		}
		
		y += currentSpeed;
	}
	
	public void setRotation() {
		rotation = (int) (Math.random() * 20f - 10f);
	}
	
	public void goAway() {
		shouldFaster = true;
	}
	
	public boolean away(GameContainer gc) {
		if(y > gc.getHeight()) return true;
		return false;
	}
	
	public void render(Graphics g) {
		g.rotate(x + (2*border+width) / 2, 500 / 2, rotation);
		g.drawImage(texture, x, y);
		font.drawString((2*border + width)/2 - (font.getWidth(voc.nativeWord))/2 + x,
				(2*border + height)/2 - (font.getHeight(voc.nativeWord))/2 + y + 1, voc.nativeWord, Color.white); // Shadow
		font.drawString((2*border + width)/2 - (font.getWidth(voc.nativeWord))/2 + x,
				(2*border + height)/2 - (font.getHeight(voc.nativeWord))/2 + y, voc.nativeWord, Color.black);
		
	}

}
