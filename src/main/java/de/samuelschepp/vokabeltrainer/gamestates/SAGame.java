package de.samuelschepp.vokabeltrainer.gamestates;

import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import de.samuelschepp.vokabeltrainer.engine.SAGameState;
import de.samuelschepp.vokabeltrainer.engine.SATimer;
import de.samuelschepp.vokabeltrainer.engine.SAVoc;
import de.samuelschepp.vokabeltrainer.uielements.SACard;
import de.samuelschepp.vokabeltrainer.uielements.SAFadeAnimator;
import de.samuelschepp.vokabeltrainer.uielements.SATextField;

public class SAGame {

	public ArrayList<SAVoc> vocList;
	int vocListIndex;
	
	Image gameGUI;
	
	SACard card;
	public int difficulty;
	public int done;
	public int missed;
	Color missedColor;
	
	float marginBorder;
	float marginHUD;
	float marginXOfH2;
	
	SATimer timer;
	SATextField textField;
	
	TrueTypeFont h1; // Klein
	TrueTypeFont h2; // Mittel
	
	SAFadeAnimator fadeAnimator;
	
	public SAGame() throws SlickException {
		done = 0;
		missed = 0;
		
		marginBorder = 40;
		marginHUD = 50;
		marginXOfH2 = 140;
		
		timer = new SATimer();
		textField = new SATextField();
		
		fadeAnimator = new SAFadeAnimator(SAGameState.Game);
		
		h1 = new TrueTypeFont(new java.awt.Font("Arial", java.awt.Font.TRUETYPE_FONT, 20), true);
		h2 = new TrueTypeFont(new java.awt.Font("Arial", java.awt.Font.TRUETYPE_FONT, 40), true);
		
		gameGUI = new Image("textures/gameGUI.png");
		
		missedColor = Color.white;
	}
	
	public void init(ArrayList<SAVoc> _vocList, int _difficulty) throws SlickException {
		vocList = _vocList;
		difficulty = _difficulty;
	

		if(vocList.size() == 0) {
			fadeAnimator.fadeOut(SAGameState.Menu, false);
		}
		
		Collections.shuffle(vocList);
		vocListIndex = 0;
		card = new SACard(difficulty);
		card.voc = vocList.get(vocListIndex);
	}

	public SAGameState update(GameContainer gc, int delta) throws SlickException {
		if(timer.running) {
			textField.update(gc.getInput());
			timer.update(delta);

			if(textField.text.equals(card.voc.foreignWord)) {
				card.goAway();
				textField.locked = true;
				textField.showGreen = true;
				if(!card.done) done += 1;
				card.done = true;
			}

			card.update(gc);

			if(card.away(gc)) {
				if(!card.done) missed += 1;
				textField.reset();
				card.init(difficulty);
				vocListIndex += 1;
				if(vocListIndex < vocList.size()) {
					card.voc = vocList.get(vocListIndex);
				}
				else {
					fadeAnimator.fadeOut(SAGameState.Score, true);
					timer.running = false;
				}
			}
			
			if(gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
				timer.running = false;
				fadeAnimator.fadeOut(SAGameState.Menu, true);
			}
		}
		
		if(fadeAnimator.isFadeInDone()) {
			timer.running = true;
		}
		
		if(missed > 0) missedColor = Color.red;
		else missedColor = Color.white;

		return fadeAnimator.update();
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		gameGUI.draw();
		
		h2.drawString(marginXOfH2, marginBorder + 3 + 1, timer.toString(), Color.black); // Shadow
		h2.drawString(marginXOfH2, marginBorder + 3, timer.toString());
		
		h2.drawString(marginXOfH2, marginBorder + marginHUD + 3 + 1, done+"", Color.black); // Shadow
		h2.drawString(marginXOfH2, marginBorder + marginHUD + 3, done+"");
		
		h2.drawString(marginXOfH2, marginBorder + 3 + marginHUD*2 + 1, missed+"", Color.black); // Shadow
		h2.drawString(marginXOfH2, marginBorder + 3 + marginHUD*2, missed+"", missedColor);
		
		h1.drawString(marginBorder, marginBorder + 20 + 1, "Zeit:", Color.black); // Shadow
		h1.drawString(marginBorder, marginBorder + 20, "Zeit:");
		
		h1.drawString(marginBorder, marginBorder + marginHUD + 20 + 1, "Richtige:", Color.black); // Shadow
		h1.drawString(marginBorder, marginBorder + marginHUD + 20, "Richtige:");
		
		h1.drawString(marginBorder, marginBorder + marginHUD*2 + 20 + 1, "Verfehlt:", Color.black); // Shadow
		h1.drawString(marginBorder, marginBorder + marginHUD*2 + 20, "Verfehlt:");

		textField.render(g, gc);
		
		card.render(g);
		
		// Fade Animation
		fadeAnimator.render(g);
	}
}
