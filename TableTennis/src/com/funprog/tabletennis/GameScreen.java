package com.funprog.tabletennis;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
* The GameScreen class is the screen where the main game play
* elements occur. It brings together the ball, paddle, and table
* while checking for input and rendering output.
*/
public class GameScreen implements Screen{
	TableTennis game;
	
	OrthographicCamera camera;
	World world;
	Box2DDebugRenderer debugRenderer;
	SpriteBatch spriteBatch;
	
	Ball ball;
	Table table;
	Table tableVertical;
	Paddle leftPad;
	
	Vector3 touchPos;
	
	ControlTool resetBall;
	RotateTool rotate;
	MovementTool movement;
	
	/**
	* Constructor that initializes the variables and takes
	* the game as an argument to have the ability to change
	* screens.
	*
	* @param game The game that maintains the screens.
	*/
	GameScreen(TableTennis game) {
		this.game = game;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 10, 5); // Create dimensions of world to 10 by 5
		world = new World(new Vector2(0, -10), true); // Create world with gravity
		debugRenderer = new Box2DDebugRenderer();
		spriteBatch = new SpriteBatch();
		
		ball = new Ball(world, new Vector2(2, 3), new Texture(Gdx.files.internal("ball.png")));
		ball.stop(); // Don't let gravity affect the ball initially.
		
		table = new Table(world, new Vector2(5, 1.5f), 8, 0.1f);
		tableVertical = new Table(world, new Vector2(9, 3), 0.1f, 3);
		
		leftPad = new Paddle(world, new Vector2(1, 2.5f), 
				new Texture(Gdx.files.internal("paddle.png")));
		
		resetBall = new ControlTool(new Texture(Gdx.files.internal("resetBall.png")), 
				new Rectangle(4, 0, 2, 1));
		rotate = new RotateTool(new Texture(Gdx.files.internal("rotate.png")),
				new Rectangle(8.6f, 0, 1.4f, 1.4f));
		
		movement = new MovementTool(world, new Texture(Gdx.files.internal("movement.png")),
				new Rectangle(0.05f, 0.05f, 1.4f, 1.4f), new Texture(Gdx.files.internal("movementBall.png")));		
	}
	
	/**
	 * Checks for input and renders the world.
	 * @param delta
	 */
	@Override
	public void render(float delta) {
		// Clear screen with black
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		getInput();
		
		// If the inside circle of the movement tool is out of bounds, return it
		// to its center position.
		if (movement.isOutOfBounds()) {
			movement.stopMovingBall();
			movement.repositionBall();
		}
		
		// Go to the next step and render the world
		world.step(delta, 8, 3);
		
		// Draw all the sprites
		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		
		// Draw the tools
		resetBall.draw(spriteBatch);
		rotate.draw(spriteBatch);
		movement.draw(spriteBatch);
		
		// Draw the sprites of all the bodies
		Iterator<Body> bi = world.getBodies();
        
		while (bi.hasNext()){
		    Body b = bi.next();
		    
		    Sprite spr = (Sprite) b.getUserData();

		    if (spr != null) {
		        spr.setPosition(b.getPosition().x - spr.getOriginX(),
		        		b.getPosition().y - spr.getOriginY());
		        spr.setRotation(MathUtils.radiansToDegrees * b.getAngle());
		        spr.draw(spriteBatch);
		    }
		}
		
		spriteBatch.end();
		
		// Draw the box2d bodies for debugging
		debugRenderer.render(world, camera.combined);
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
	
	/**
	 * Checks the touch screen and keyboard, then responds
	 */
	private void getInput() {
		// Loop through each touch input
		for (int i = 0; Gdx.input.isTouched(i); i++) {
			touchPos = new Vector3(); // 3d vector used for camera.unproject
			touchPos.set(Gdx.input.getX(i), Gdx.input.getY(i), 0);
			camera.unproject(touchPos); // Make the touch input into camera coords
			
			if (resetBall.isTouched(touchPos.x, touchPos.y)) {
				// Move the ball to its starting position and stop it
				ball.setPosition(2, 3);
				ball.stop();
			} else if (rotate.isTouched(touchPos.x, touchPos.y)) {
				// Update the rotation image
				rotate.updateTouch(touchPos.x, touchPos.y);
				
				// Sync the paddle with the rotation tool
				leftPad.setRotation(rotate.getRotation());
			} else if (movement.isTouched(touchPos.x, touchPos.y)) {
				// Move the paddle
				movement.updateTouch(touchPos.x, touchPos.y, leftPad);
			} /*else if (touchPos.x < 8) {
				// Move the paddle
				// Offset y to ease android use
				leftPad.moveToward(new Vector2(touchPos.x, touchPos.y + 1.2f));
			}*/ else { // Otherwise don't do anything
				movement.stopMovingBall();
				movement.repositionBall();
				leftPad.stopMoving();
			}
			//movement.repositionBall();
		}
		
		// If no input, stop moving
		if (!Gdx.input.isTouched()) {
			movement.stopMovingBall();
			movement.repositionBall();
			leftPad.stopMoving();
		}
		
		// Check for keyboard input
		if (Gdx.input.isKeyPressed(Keys.A)) {
			leftPad.rotateCounterClockwise();
			
			// Sync the changing rotation with touch rotation
			rotate.setRotation(leftPad.getRotation());
		} else if (Gdx.input.isKeyPressed(Keys.D)) {
			leftPad.rotateClockwise();
			
			// Sync the changing rotation with touch rotation
			rotate.setRotation(leftPad.getRotation());
		} else {
			leftPad.stopRotating();
		}
	}
}