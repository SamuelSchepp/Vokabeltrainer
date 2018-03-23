package de.samuelschepp.vokabeltrainer.uielements;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

public class SATextField extends SAObject {

	public String text;

	TrueTypeFont font;

	public boolean locked;
	
	public boolean showGreen;
	Color fontColor;

	public SATextField() throws SlickException {
		super();
		text = "";
		font = new TrueTypeFont(new java.awt.Font("Arial", java.awt.Font.TRUETYPE_FONT, 50), true);
		locked = false;
		showGreen = false;
		fontColor = Color.white;
	}

	public void update(Input input) {
		if(input.isKeyPressed(Input.KEY_A)) if(!locked) text += "a";
		if(input.isKeyPressed(Input.KEY_B)) if(!locked) text += "b";
		if(input.isKeyPressed(Input.KEY_C)) if(!locked) text += "c";
		if(input.isKeyPressed(Input.KEY_D)) if(!locked) text += "d";
		if(input.isKeyPressed(Input.KEY_E)) if(!locked) text += "e";
		if(input.isKeyPressed(Input.KEY_F)) if(!locked) text += "f";
		if(input.isKeyPressed(Input.KEY_G)) if(!locked) text += "g";
		if(input.isKeyPressed(Input.KEY_H)) if(!locked) text += "h";
		if(input.isKeyPressed(Input.KEY_I)) if(!locked) text += "i";
		if(input.isKeyPressed(Input.KEY_J)) if(!locked) text += "j";
		if(input.isKeyPressed(Input.KEY_K)) if(!locked) text += "k";
		if(input.isKeyPressed(Input.KEY_L)) if(!locked) text += "l";
		if(input.isKeyPressed(Input.KEY_M)) if(!locked) text += "m";
		if(input.isKeyPressed(Input.KEY_N)) if(!locked) text += "n";
		if(input.isKeyPressed(Input.KEY_O)) if(!locked) text += "o";
		if(input.isKeyPressed(Input.KEY_P)) if(!locked) text += "p";
		if(input.isKeyPressed(Input.KEY_Q)) if(!locked) text += "q";
		if(input.isKeyPressed(Input.KEY_R)) if(!locked) text += "r";
		if(input.isKeyPressed(Input.KEY_S)) if(!locked) text += "s";
		if(input.isKeyPressed(Input.KEY_T)) if(!locked) text += "t";
		if(input.isKeyPressed(Input.KEY_U)) if(!locked) text += "u";
		if(input.isKeyPressed(Input.KEY_V)) if(!locked) text += "v";
		if(input.isKeyPressed(Input.KEY_W)) if(!locked) text += "w";
		if(input.isKeyPressed(Input.KEY_X)) if(!locked) text += "x";
		if(input.isKeyPressed(Input.KEY_Z)) if(!locked) text += "y";
		if(input.isKeyPressed(Input.KEY_Y)) if(!locked) text += "z";


		if(input.isKeyPressed(Input.KEY_BACK) && text.length() > 0) text = text.substring(0, text.length() - 1);
		if(input.isKeyPressed(Input.KEY_TAB) || input.isKeyPressed(Input.KEY_SPACE)) text = "";
		
		if(showGreen) fontColor = Color.green;
		else fontColor = Color.white;
	}
	
	public void reset() {
		text = "";
		showGreen = false;
		locked = false;
	}

	public void render(Graphics g, GameContainer gc) {
		font.drawString(40, gc.getHeight() - font.getHeight(text) - 82 + 1, text, Color.black); // Shadow
		font.drawString(40, gc.getHeight() - font.getHeight(text) - 82, text, fontColor);
	}
}
