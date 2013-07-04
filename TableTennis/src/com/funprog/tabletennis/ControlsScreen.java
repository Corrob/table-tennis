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
* The ControlsScreen class is a tutorial screen that
* aims to inform the user of the controls.
*/
public class ControlsScreen implements Screen{
	
	TableTennis game;
	
	SpriteBatch spriteBatch;
	
	Texture tutorial;
	
	ControlTool backButton;
	
	public ControlsScreen(TableTennis game) {
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// If user presses the Back button, go back to the main menu
		for (int i = 0; Gdx.input.isTouched(i); i++) {
			if (backButton.isTouched(Gdx.input.getX(i), 480 - Gdx.input.getY(i))) {
				game.setScreen(game.mainMenuScreen);
			}
		}
		spriteBatch.begin();
		
		// Draw the tutorial image
		spriteBatch.draw(tutorial, 0, 0, 800, 480);
		
		backButton.draw(spriteBatch);
		
		spriteBatch.end();
	}
	
	@Override
	public void resize(int width, int height) {
	}
	
	@Override
	public void show() {
		spriteBatch = new SpriteBatch();
		
		tutorial = new Texture(Gdx.files.internal("tutorial.png"));
		
		backButton = new ControlTool(new Texture(Gdx.files.internal("Buttons/backButton.png")), 
				new Rectangle(10, 406, 192, 64));
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
}
