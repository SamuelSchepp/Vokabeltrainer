package de.samuelschepp.vokabeltrainer.uielements;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import de.samuelschepp.vokabeltrainer.engine.SAGameState;

public class SAFadeAnimator {

	int fadeInTimer;
	int fadeOutTimer;
	Image black;
	Image black2;
	SAGameState gameStateAfterFade;
	SAGameState currentGameState;
	boolean fadeInDone;
	public boolean dontShowLoading;
	
	public SAFadeAnimator(SAGameState _currentGameState) throws SlickException {
		fadeInTimer = 0;
		fadeOutTimer = -1;
		black = new Image("textures/black.png");
		black2 = new Image("textures/black2.png");
		currentGameState = _currentGameState;
		gameStateAfterFade = SAGameState.None;
		fadeInDone = false;
		dontShowLoading = false;
	}
	
	public SAGameState update() {
		if(fadeInTimer > -1) {
			fadeInTimer += 1;
			fadeInDone = false;
			black.setAlpha(1 - (float)fadeInTimer / 20f);
			black2.setAlpha(1 - (float)fadeInTimer / 20f);
			if(fadeInTimer > 20) {
				fadeInTimer = -1;
				fadeInDone = true;
			}
		}
		if (fadeOutTimer > -1) {
			fadeOutTimer += 1;
			black.setAlpha((float)fadeOutTimer / 20f);
			black2.setAlpha(1 - (float)fadeInTimer / 20f);
			if(fadeOutTimer > 20) {
				fadeOutTimer = -1;
				return gameStateAfterFade;
			}
		}
		
		return currentGameState;
	}
	
	public void render(Graphics g) {
		g.resetTransform();
		if(fadeOutTimer > -1 || fadeInTimer > -1) {
			if(dontShowLoading) {
				g.drawImage(black2, 0, 0);
			}
			if(!dontShowLoading) {
				g.drawImage(black, 0, 0);
			}
		}
	}
	
	public void fadeOut(SAGameState _toGameState, boolean animate) {
		gameStateAfterFade = _toGameState;
		if(!animate) fadeOutTimer = 19;
		if (animate) fadeOutTimer = 0;
	}
	
	public boolean isFading() {
		if(fadeInTimer > -1 || fadeOutTimer > -1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isFadingOut() {
		if(fadeOutTimer > -1) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isFadeInDone() {
		boolean result = fadeInDone;
		fadeInDone = false;
		return result;
	}
}
