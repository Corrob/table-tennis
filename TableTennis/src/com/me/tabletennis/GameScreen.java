package com.me.tabletennis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

/*
 * The GameScreen class is the screen where the main game play
 * elements occur. It brings together the ball, paddle, and table
 * while checking for input and rendering output.
 */
public class GameScreen implements Screen{
	TableTennis game;
	OrthographicCamera camera;
	World world;
	Box2DDebugRenderer debugRenderer;
	Ball ball;
	Table table;
	
	/*
	 * Constructor that initializes the variables and takes
	 * the game as an argument to have the ability to change
	 * screens.
	 * 
	 * @param game The game that maintains the screens.
	 */
	GameScreen(TableTennis game) {
		this.game = game;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 10, 5);
		world = new World(new Vector2(0, -10), true);
		debugRenderer = new Box2DDebugRenderer();

		ball = new Ball(world, new Vector2(5, 5));
		table = new Table(world, new Vector2(5, 1), 8, 0.1f);
	}
	
	@Override
	public void render(float delta) {
		// Clear screen with black
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		// Render the world and go to the next step
		debugRenderer.render(world, camera.combined);
		
		world.step(delta, 8, 3);
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
