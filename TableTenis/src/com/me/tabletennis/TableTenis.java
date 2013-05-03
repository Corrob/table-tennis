package com.me.tabletennis;

import com.badlogic.gdx.Game;

/*
 * The main game class for table tennis. It initializes all of the screens
 * and starts the first screen.
 */
public class TableTenis extends Game {
	GameScreen gameScreen;
	
	@Override
	public void create() {
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}
}
