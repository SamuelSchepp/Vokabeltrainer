package de.samuelschepp.vokabeltrainer.gamestates;

import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import de.samuelschepp.vokabeltrainer.engine.SAGameState;
import de.samuelschepp.vokabeltrainer.engine.SAVoc;
import de.samuelschepp.vokabeltrainer.engine.SAVocListImporter;
import de.samuelschepp.vokabeltrainer.uielements.SAFadeAnimator;

public class SAMenu {
	
	// †berschrift
	TrueTypeFont h1;
	String h1Text;
	
	Image menuGUI;

	float y12;
	float margin;
	TrueTypeFont h3;

	Input input;
	Input oldInput;

	// Dateiwahl
	ArrayList<String> fileNames;
	float xFileNames;
	int hover1;
	int selected1;

	// Schwierigkeit
	ArrayList<String> difficulties;
	float xDifficulties;
	int hover2;
	int selected2;
	
	SAFadeAnimator fadeAnimator;

	public SAMenu(GameContainer gc, boolean firstRun) throws SlickException {
		h1 = new TrueTypeFont(new java.awt.Font("Arial", java.awt.Font.TRUETYPE_FONT, 42), true);
		
		h1Text = "Samuels Vokabeltrainer";

		menuGUI = new Image("textures/menuGUI.png");

		h3 = new TrueTypeFont(new java.awt.Font("Arial", java.awt.Font.TRUETYPE_FONT, 22), true);
		y12 = 191;
		margin = 40;

		// Dateiwahl
		fileNames = SAVocListImporter.getFileNames();
		xFileNames = 149;
		hover1 = -1;
		selected1 = -1;
		
		// Schwierigkeit
		difficulties = new ArrayList<String>();
		difficulties.add("Leicht");
		difficulties.add("Mittel");
		difficulties.add("Schwer");
		xDifficulties = 576;
		hover2 = -1;
		selected2 = -1;
		
		// Fade Animation
		fadeAnimator = new SAFadeAnimator(SAGameState.Menu);
		fadeAnimator.dontShowLoading = firstRun;
		
	}

	public SAGameState update(GameContainer gc, int delta) throws SlickException {
		input = gc.getInput();
		hover1 = -1;
		hover2 = -1;

		// Dateiwahl
		for(int i = 0; i < fileNames.size(); i++) {
			if(input.getMouseX() > xFileNames && input.getMouseX() < xFileNames+h3.getWidth(fileNames.get(i))) {
				if(input.getMouseY() > y12 + i*margin && input.getMouseY() < y12 + i*margin + h3.getHeight(fileNames.get(i))) {
					hover1 = i;
					if(input.isMousePressed(0)) {
						selected1 = i;
					}
				}
			}
		}
		
		// Schwierigkeit
		for(int i = 0; i < difficulties.size(); i++) {
			if(input.getMouseX() > xDifficulties && input.getMouseX() < xDifficulties+h3.getWidth(difficulties.get(i))) {
				if(input.getMouseY() > y12 + i*margin && input.getMouseY() < y12 + i*margin + h3.getHeight(difficulties.get(i))) {
					hover2 = i;
					if(input.isMousePressed(0)) {
						selected2 = i;
					}
				}
			}
		}

		// Start
		if (selected1 > -1 && selected2 > -1 && !fadeAnimator.isFading()) {
			fadeAnimator.fadeOut(SAGameState.Game, true);
		}

		if(fadeAnimator.isFadeInDone()) {
			fadeAnimator.dontShowLoading = false;
		}
		
		return fadeAnimator.update();
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		
		menuGUI.draw();
		
		h1.drawString(gc.getWidth() / 2 - h1.getWidth(h1Text) / 2, 52 + 1, h1Text, Color.black); // Shadow
		h1.drawString(gc.getWidth() / 2 - h1.getWidth(h1Text) / 2, 52, h1Text, Color.white);
		
		Color currentColor = Color.white;
		Color currentShadowColor = Color.black;

		// Dateiwahl
		for(int i = 0; i < fileNames.size(); i++) {
			currentColor = Color.white;
			currentShadowColor = Color.black;
			
			if(hover1 == i) {
				currentColor = new Color(200, 200, 200);
				currentShadowColor = Color.black;
			}
			if(selected1 == i) {
				currentColor = new Color(203, 232, 255);
				currentShadowColor = Color.black;
			}
			h3.drawString(xFileNames, y12 + i*margin + 1, fileNames.get(i), currentShadowColor); // Shadow
			h3.drawString(xFileNames, y12 + i*margin, fileNames.get(i), currentColor);
		}
		
		// Schwierigkeit
		for(int i = 0; i < difficulties.size(); i++) {
			currentColor = Color.white;
			currentShadowColor = Color.black;
			
			if(hover2 == i) {
				currentColor = new Color(200, 200, 200);
				currentShadowColor = Color.black;
			}
			if(selected2 == i) {
				currentColor = new Color(203, 232, 255);
				currentShadowColor = Color.black;
			}
			
			h3.drawString(xDifficulties, y12 + i*margin + 1, difficulties.get(i), currentShadowColor); // Shadow
			h3.drawString(xDifficulties, y12 + i*margin, difficulties.get(i), currentColor);
		}
		
		fadeAnimator.render(g);
	}
	
	public ArrayList<SAVoc> getVocList() throws IOException {
		return SAVocListImporter.getVocList(selected1);
	}
	
	public int getDifficulty() {
		return selected2;
	}
}
