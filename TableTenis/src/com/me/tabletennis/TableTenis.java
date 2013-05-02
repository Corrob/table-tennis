package com.me.tabletennis;

import com.badlogic.gdx.Game;

public class TableTenis extends Game {
	GameScreen gameScreen;
	
	@Override
	public void create() {
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}
}
