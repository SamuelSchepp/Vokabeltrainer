package de.samuelschepp.vokabeltrainer;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import de.samuelschepp.vokabeltrainer.engine.SAGameState;
import de.samuelschepp.vokabeltrainer.gamestates.SAGame;
import de.samuelschepp.vokabeltrainer.gamestates.SAMenu;
import de.samuelschepp.vokabeltrainer.gamestates.SAScore;
import de.samuelschepp.vokabeltrainer.uielements.SABackgroundRenderer;

public class Vokabeltrainer extends BasicGame {

	SAGameState gameState;
	SAGameState oldGameState;
	
	SAMenu menu;
	SAGame game;
	SAScore score;

	SABackgroundRenderer bgRenderer;
	
	public static void main(String[] args) {
		try {
			
			AppGameContainer appContainer;
			appContainer = new AppGameContainer(new Vokabeltrainer("Vokabeltrainer"));
			appContainer.setDisplayMode(800, 500, false);
			appContainer.setTargetFrameRate(60);
			appContainer.setVSync(true);
			appContainer.setShowFPS(false);
			appContainer.setAlwaysRender(true);
			appContainer.start();
		} catch (SlickException ex) { }
	}
	
	public Vokabeltrainer(String title) throws SlickException {
		super(title);
	}

	public void init(GameContainer gc) throws SlickException {
		gameState = oldGameState = SAGameState.Menu;

		game = new SAGame();
		menu = new SAMenu(gc, true);

		bgRenderer = new SABackgroundRenderer(gc);
	}

	public void update(GameContainer gc, int delta) throws SlickException {
		oldGameState = gameState;
		
		if(gameState == SAGameState.Menu) {
			gameState = menu.update(gc, delta);
		}
		
		/*
		 * Transition:
		 * Menu -> Game
		 */
		if(oldGameState == SAGameState.Menu && gameState == SAGameState.Game) {
			game = new SAGame();
			try { game.init(menu.getVocList(), menu.getDifficulty()); } catch (Exception ex) { }
		}
		
		if(gameState == SAGameState.Game) {
			gameState = game.update(gc, delta);
		}
		
		/*
		 * Transition:
		 * Game -> Menu
		 */
		if(oldGameState == SAGameState.Game && gameState == SAGameState.Menu) {
			menu = new SAMenu(gc, false);
		}
		
		/*
		 * Transition:
		 * Game -> Score
		 */
		if(oldGameState == SAGameState.Game && gameState == SAGameState.Score) {
			score = new SAScore(game);
		}
		
		if(gameState == SAGameState.Score) {
			gameState = score.update(gc, delta);
		}
		
		/*
		 * Transition:
		 * Score -> Menu
		 */
		if(oldGameState == SAGameState.Score && gameState == SAGameState.Menu) {
			menu = new SAMenu(gc, false);
		}
		
		bgRenderer.update(gc);
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		bgRenderer.render(g);
		if(gameState == SAGameState.Menu) {
			menu.render(gc, g);
		}
		if(gameState == SAGameState.Game) {
			game.render(gc, g);
		}
		if(gameState == SAGameState.Score) {
			score.render(gc, g);
		}
	}
}
