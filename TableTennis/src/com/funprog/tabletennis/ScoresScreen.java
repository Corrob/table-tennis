package com.funprog.tabletennis;

import java.util.Iterator;

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
* The ScoresScreen class is the screen that displays
* the high scores obtained in the game. These scores
* are local and only based on games played on the same
* device.
*/
public class ScoresScreen implements Screen{
	
	TableTennis game;
	
	public ScoresScreen(TableTennis game) {
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		
	}
	
	@Override
	public void resize(int width, int height) {
	}
	
	@Override
	public void show() {
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
