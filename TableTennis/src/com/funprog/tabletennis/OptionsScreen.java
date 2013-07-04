package com.funprog.tabletennis;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.graphics.GL10;

/**
* The OptionsScreen class is the screen where user can
* change the difficulty and turn off the sounds.
*/
public class OptionsScreen implements Screen{
	
	TableTennis game;
	
	SpriteBatch spriteBatch;
	
	Texture background;
	
	ControlTool backButton;
	ControlTool easy;
	ControlTool medium;
	ControlTool hard;
	ControlTool soundOn;
	ControlTool soundOff;
	ControlTool musicOn;
	ControlTool musicOff;
	
	public OptionsScreen(TableTennis game) {
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Process user input
		getInput();
		
		spriteBatch.begin();
		
		// Draw the options screen
		spriteBatch.draw(background, 0, 0, 800, 480);
		
		backButton.draw(spriteBatch);
		easy.draw(spriteBatch);
		medium.draw(spriteBatch);
		hard.draw(spriteBatch);
		soundOn.draw(spriteBatch);
		soundOff.draw(spriteBatch);
		musicOn.draw(spriteBatch);
		musicOff.draw(spriteBatch);
		
		spriteBatch.end();
	}
	
	@Override
	public void resize(int width, int height) {
	}
	
	@Override
	public void show() {
		spriteBatch = new SpriteBatch();
		
		background = new Texture(Gdx.files.internal("options.png"));
		
		backButton = new ControlTool(new Texture(Gdx.files.internal("Buttons/backButton.png")), 
				new Rectangle(10, 406, 192, 64));
		
		easy = new ControlTool(new Texture(Gdx.files.internal("Buttons/easy.png")),
				new Rectangle(92, 230, 192, 64));
		medium = new ControlTool(new Texture(Gdx.files.internal("Buttons/medium.png")),
				new Rectangle(294, 230, 192, 64));
		hard = new ControlTool(new Texture(Gdx.files.internal("Buttons/hard.png")),
				new Rectangle(496, 230, 192, 64));
		soundOn = new ControlTool(new Texture(Gdx.files.internal("Buttons/onButton.png")),
				new Rectangle(188, 130, 192, 64));
		soundOff = new ControlTool(new Texture(Gdx.files.internal("Buttons/offButton.png")),
				new Rectangle(390, 130, 192, 64));
		musicOn = new ControlTool(new Texture(Gdx.files.internal("Buttons/onButton.png")),
				new Rectangle(188, 30, 192, 64));
		musicOff = new ControlTool(new Texture(Gdx.files.internal("Buttons/offButton.png")),
				new Rectangle(390, 30, 192, 64));
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
	 * Changes the game options based on user input.
	 */
	public void getInput() {
		// If user presses the Back button, go back to the main menu
		for (int i = 0; Gdx.input.isTouched(i); i++) {
			if (backButton.isTouched(Gdx.input.getX(i), 480 - Gdx.input.getY(i))) {
				game.setScreen(game.mainMenuScreen);
			}
		}
	}
}
