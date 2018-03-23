package de.samuelschepp.vokabeltrainer.uielements;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

public class SAButton extends SAObject {

	public boolean showPushed;
	public Color hoverColor;
	public Color pushedColor;
	public String text;
	
	public SAButton() throws SlickException {
		super();
		showPushed = true;
		hoverColor = Color.gray;
		pushedColor = Color.black;
		text = "";
	}
}
