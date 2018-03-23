package de.samuelschepp.vokabeltrainer.uielements;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SAObject {

	public float height;
	public float width;
	public float x;
	public float y;
	
	Image texture;
	
	public SAObject() throws SlickException {
		height = 0;
		width = 0;
		x = 0;
		y = 0;
		
		texture = new Image(0, 0);
	}
}
