package com.funprog.tabletennis;

import com.badlogic.gdx.Game;

/**
* The main game class for table tennis. It initializes all of the screens
* and starts the first screen.
*/
public class TableTennis extends Game {
	MainMenuScreen mainMenuScreen;
	OptionsScreen optionsScreen;
	ControlsScreen controlsScreen;
	ScoresScreen scoresScreen;
	CreditsScreen creditsScreen;
	GameScreen gameScreen;
	
	@Override
	public void create() {
		mainMenuScreen = new MainMenuScreen(this);
		optionsScreen = new OptionsScreen(this);
		controlsScreen = new ControlsScreen(this);
		scoresScreen = new ScoresScreen(this);
		creditsScreen = new CreditsScreen(this);
		gameScreen = new GameScreen(this);
		setScreen(mainMenuScreen);
	}
}