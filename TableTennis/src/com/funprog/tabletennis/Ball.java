package com.funprog.tabletennis;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
* The Ball class maintains a sprite and body for a ball
* and provides information and methods for a table tennis
* ball.
*/
public class Ball {
	private static final float RADIUS = 0.1f;
	private Body body;
	private Sprite sprite;
	
	/**
	* The constructor that requires the world in which to
	* add the ball and the position where it starts.
	*
	* @param world The world to add the ball
	* @param position The position where the ball starts
	*/
	public Ball(World world, Vector2 position, Texture texture) {
		// Create the body definition
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(position.x, position.y);
		
		// Add the body to the world
		body = world.createBody(bodyDef);
		
		// Define the shape and characteristics of the ball
		CircleShape circle = new CircleShape();
		circle.setRadius(0.1f);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 0.2f;
		fixtureDef.friction = 0.2f;
		fixtureDef.restitution = 0.8f;
	
		body.createFixture(fixtureDef);
		
		// Clean up
		circle.dispose();
		
		sprite = new Sprite(texture);
		
		sprite.setSize(RADIUS * 2, RADIUS * 2);
		// Put the sprite's origin in the middle to ease rotation
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		
		body.setUserData(sprite);
	}
	
	/**
	 * Stops the ball from moving.
	 */
	public void stop() {
		body.setAwake(false);
	}
	
	/**
	 * Sets the position of the ball to x and y.
	 * @param x The x position (left-right)
	 * @param y The y position (up-down)
	 */
	public void setPosition(float x, float y) {
		body.setTransform(x, y, body.getAngle());
	}
	
	/**
	 * The body that describes the ball's motion
	 * @return the body of the ball
	 */
	public Body getBody() {
		return body;
	}
}
