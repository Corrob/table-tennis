package com.funprog.tabletennis;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.graphics.GL10;

/**
* The MainMenuScreen class is the screen where the main menu of
* the game occurs.
*/
public class MainMenuScreen implements Screen{
	
	TableTennis game;
	
	SpriteBatch spriteBatch;
	
	Texture background;
	Texture logo;
	
	ControlTool startGame;
	ControlTool controls;
	ControlTool options;
	ControlTool highScores;
	ControlTool credits;
	
	public MainMenuScreen(TableTennis game) {
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Call the getInput() function to process user input.
		getInput();
		
		spriteBatch.begin();
		
		// Draw the background
		spriteBatch.draw(background, 0, 0, 800, 480);
		spriteBatch.draw(logo, 200, 380, 400, 133.33f);
		startGame.draw(spriteBatch);
		controls.draw(spriteBatch);
		options.draw(spriteBatch);
		highScores.draw(spriteBatch);
		credits.draw(spriteBatch);
		
		spriteBatch.end();
	}
	
	@Override
	public void resize(int width, int height) {
	}
	
	@Override
	public void show() {
		spriteBatch = new SpriteBatch();
		// Load texture for background image
		background = new Texture(Gdx.files.internal("background.png"));
		logo = new Texture(Gdx.files.internal("logo.png"));
		
		startGame = new ControlTool(new Texture(Gdx.files.internal("startButton.png")), 
				new Rectangle(304, 338, 192, 64));
		controls = new ControlTool(new Texture(Gdx.files.internal("controlsButton.png")), 
				new Rectangle(304, 262, 192, 64));
		options = new ControlTool(new Texture(Gdx.files.internal("optionsButton.png")), 
				new Rectangle(304, 186, 192, 64));
		highScores = new ControlTool(new Texture(Gdx.files.internal("highScoresButton.png")), 
				new Rectangle(304, 110, 192, 64));
		credits = new ControlTool(new Texture(Gdx.files.internal("creditsButton.png")), 
				new Rectangle(304, 34, 192, 64));
	}
	
	@Override
	public void hide() {
	}
	
	@Override
	public void pause() {
	}
	
	@Override
	public void resume() {
	}
	
	@Override
	public void dispose() {
	}
	
	/**
	 * Checks the touch screen and mouse input, then responds
	 */
	private void getInput() {
		// Loop through each touch input
		for (int i = 0; Gdx.input.isTouched(i); i++) {
			// Initializing the X and Y coordinates of the input
			float xInput = Gdx.input.getX(i);
			float yInput = 480 - Gdx.input.getY(i);

			// Decide which screen to go to based on user input
			if (startGame.isTouched(xInput, yInput)) {
				game.setScreen(game.gameScreen);
			} else if (controls.isTouched(xInput, yInput)) {
				game.setScreen(game.controlsScreen);
			} else if (options.isTouched(xInput, yInput)) {
				game.setScreen(game.optionsScreen);
			} else if (highScores.isTouched(xInput, yInput)) {
				game.setScreen(game.scoresScreen);
			} else if (credits.isTouched(xInput, yInput)) {
				game.setScreen(game.creditsScreen);
			}
		}
	}
}
