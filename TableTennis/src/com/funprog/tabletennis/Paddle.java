package com.funprog.tabletennis;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
* The Paddle class combines a body and sprite to form
* a table tennis paddle. It also provides methods to
* move.
*/
public class Paddle {
	private static final float SPEED = 4; // meters per second
	private static final float ANG_SPEED = 3; // radians per second
	
	private Body body;
	
	/**
	* Constructor that creates a body and sprite for the paddle
	*
	* @param world The world in which to add the paddle
	* @param position The position for the paddle to start
	*/
	public Paddle(World world, Vector2 position) {
		// Create the body definition
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.KinematicBody;
		bodyDef.position.set(position.x, position.y);
		bodyDef.angle = 0.2f; // Offset the angle to allow hitting the ball up
		
		// Add the body to the world
		body = world.createBody(bodyDef);
		
		// Define the shape and characteristics of the paddle
		PolygonShape paddleShape = new PolygonShape();
		paddleShape.setAsBox(0.05f, 0.5f);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = paddleShape;
		fixtureDef.density = 0.9f;
		fixtureDef.friction = 0.5f;
		fixtureDef.restitution = 0.7f;
		
		body.createFixture(fixtureDef);
		
		// Clean up
		paddleShape.dispose();
	}
	
	/**
	 * Moves the paddle toward the point
	 * @param point The point for the paddle to move
	 */
	public void moveToward(Vector2 point) {
		Vector2 dir = point.sub(body.getWorldCenter());
		float distAway = dir.len();
		
		// Check if the paddle is far enough from the point
		if (distAway > 0.1f) {
			dir = dir.scl(SPEED / dir.len());
			body.setLinearVelocity(dir);
		} else { // Otherwise it is close enough
			stopMoving();
		}
	}
	
	/**
	 * Stops the paddle from moving.
	 */
	public void stopMoving() {
		body.setLinearVelocity(Vector2.Zero);
	}
	
	/**
	 * Stops the paddle from rotating.
	 */
	public void stopRotating() {
		body.setAngularVelocity(0);
	}
	
	/**
	 * Rotates the paddle clockwise at a preset angular speed.
	 */
	public void rotateClockwise() {
		body.setAngularVelocity(-ANG_SPEED);
	}
	
	/**
	 * Rotates the paddle counter-clockwise at a preset angular speed.
	 */
	public void rotateCounterClockwise() {
		body.setAngularVelocity(ANG_SPEED);
	}
}
