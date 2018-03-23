package de.samuelschepp.vokabeltrainer.gamestates;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import de.samuelschepp.vokabeltrainer.engine.SAGameState;
import de.samuelschepp.vokabeltrainer.uielements.SAFadeAnimator;

public class SAScore {

	public SAGame game;
	
	float marginHUD;
	float startY;
	
	TrueTypeFont h1; // Klein
	TrueTypeFont h2; // Mittel
	
	SAFadeAnimator fadeAnimator;
	
	String difficulty;
	
	Image scoreGUI;
	
	float enterAlphaTimer;
	float enterAlpha;
	
	public SAScore(SAGame _game) throws SlickException {
		game = _game;
		
		h1 = new TrueTypeFont(new java.awt.Font("Arial", java.awt.Font.TRUETYPE_FONT, 30), true);
		h2 = new TrueTypeFont(new java.awt.Font("Arial", java.awt.Font.TRUETYPE_FONT, 60), true);
		
		scoreGUI = new Image("textures/scoreGUI.png");
		
		marginHUD = 100;
		startY = 43;
		
		fadeAnimator = new SAFadeAnimator(SAGameState.Score);
		enterAlpha = 0;
		
		difficulty = "";
		if(game.difficulty == 0) difficulty = "Leicht";
		if(game.difficulty == 1) difficulty = "Mittel";
		if(game.difficulty == 2) difficulty = "Schwer";
		enterAlphaTimer = -1;
	}

	public SAGameState update(GameContainer gc, int delta) throws SlickException {
		enterAlphaTimer += 0.05f;
		if(enterAlphaTimer > 1) enterAlphaTimer = -1;
		if(enterAlphaTimer > 0) enterAlpha = enterAlphaTimer;
		if(enterAlphaTimer < 0) enterAlpha = 0 - enterAlphaTimer;
		
		if(gc.getInput().isKeyPressed(Input.KEY_ENTER) && !fadeAnimator.isFading()) {
			fadeAnimator.fadeOut(SAGameState.Menu, true);
		}
		
		return fadeAnimator.update();
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		scoreGUI.draw();
		
		h2.drawString(579 - h2.getWidth(difficulty), startY + 2, difficulty, Color.black); // Shadow
		h2.drawString(579 - h2.getWidth(difficulty), startY, difficulty);
		
		h2.drawString(579 - h2.getWidth(game.timer.toString()), startY + marginHUD + 2, game.timer.toString(), Color.black); // Shadow
		h2.drawString(579 - h2.getWidth(game.timer.toString()), startY + marginHUD, game.timer.toString());
		
		h2.drawString(579 - h2.getWidth(game.done+""), startY + marginHUD*2 + 2, game.done+"", Color.black); // Shadow
		h2.drawString(579 - h2.getWidth(game.done+""), startY + marginHUD*2, game.done+"");
		
		h2.drawString(579 - h2.getWidth(game.missed+""), startY + marginHUD*3 + 2, game.missed+"", Color.black); // Shadow
		h2.drawString(579 - h2.getWidth(game.missed+""), startY + marginHUD*3, game.missed+"");
		
		h1.drawString(219, startY + 26 + 1, "Level:", Color.black); // Shadow
		h1.drawString(219, startY + 26, "Level:");
		
		h1.drawString(219, startY + marginHUD + 26 + 1, "Zeit:", Color.black); // Shadow
		h1.drawString(219, startY + marginHUD + 26, "Zeit:");
		
		h1.drawString(219, startY + marginHUD*2 + 26 + 1, "Richtige:", Color.black); // Shadow
		h1.drawString(219, startY + marginHUD*2 + 26, "Richtige:");
		
		h1.drawString(219, startY + marginHUD*3 + 26 + 1, "Verfehlt:", Color.black); // Shadow
		h1.drawString(219, startY + marginHUD*3 + 26, "Verfehlt:");
		
		h1.drawString(gc.getWidth() - h1.getWidth("Enter drücken") - 20, gc.getHeight() - h1.getHeight("Enter drücken") - 13 + 1, "Enter drücken", new Color(0.1f, 0.1f, 0.1f, enterAlpha));
		h1.drawString(gc.getWidth() - h1.getWidth("Enter drücken") - 20, gc.getHeight() - h1.getHeight("Enter drücken") - 13, "Enter drücken", new Color(1f, 1f, 1f, enterAlpha));
		
		fadeAnimator.render(g);
	}
}
