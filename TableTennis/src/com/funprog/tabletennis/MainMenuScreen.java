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
* The MainMenuScreen class is the screen where the main menu of
* the game occurs.
*/
public class MainMenuScreen implements Screen{
	
	TableTennis game;
	
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
