package com.funprog.tabletennis;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
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
	private static final float SPEED = 8; // meters per second
	private static final float ANG_SPEED = 3; // radians per second
	private static final float WIDTH = 0.35f;
	private static final float HEIGHT = 1.3f;
	
	private Body body;
	private Sprite sprite;
	
	/**
	* Constructor that creates a body and sprite for the paddle
	*
	* @param world The world in which to add the paddle
	* @param position The position for the paddle to start
	*/
	public Paddle(World world, Vector2 position, Texture texture) {
		// Create the body definition
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.KinematicBody;
		bodyDef.position.set(position.x, position.y);
		bodyDef.angle = 0.2f; // Offset the angle to allow hitting the ball up
		
		// Add the body to the world
		body = world.createBody(bodyDef);
		
		// Define the shape and characteristics of the paddle
		PolygonShape paddleShape = new PolygonShape();
		paddleShape.setAsBox(WIDTH / 2, HEIGHT / 2);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = paddleShape;
		fixtureDef.density = 0.9f;
		fixtureDef.friction = 0.5f;
		fixtureDef.restitution = 0.7f;
		
		body.createFixture(fixtureDef);
		
		// Clean up
		paddleShape.dispose();
		
		sprite = new Sprite(texture);
		
		sprite.setSize(WIDTH * 2, HEIGHT);
		// Put the sprite's origin in the middle to ease rotation
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		
		body.setUserData(sprite);
	}
	
	/**
	 * Moves the paddle toward the point
	 * @param point The point for the paddle to move
	 * @param speed The factor that affects the speed of movement
	 */
	public void moveToward(Vector2 point, float speed) {
		Vector2 dir = point.sub(body.getWorldCenter());
		float distAway = dir.len();
		
		// Check if the paddle is far enough from the point
		if (distAway > 0.001f) {
			dir = dir.scl(SPEED / dir.len());
			body.setLinearVelocity(dir.scl(speed));
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
	
	/**
	 * Returns the current angle of the paddle
	 * @return The angle in radians
	 */
	public float getRotation() {
		return body.getAngle();
	}
	
	/**
	 * Sets the rotation of the paddle
	 * @param angle The angle to set the paddle to in radians
	 */
	public void setRotation(float angle) {
		body.setTransform(body.getPosition(), angle);
	}
	
	/**
	 * Returns the position of the paddle in the world.
	 * @return The position of the paddle in the world.
	 */
	public Vector2 getWorldCenterPos() {
		return body.getWorldCenter();
	}
	
	public void setVelocity(Vector2 velocity) {
		body.setLinearVelocity(velocity.scl(SPEED));
	}
	
	/**
	 * Moves the paddle to the passed position.
	 * @param The position the paddle will move to.
	 */
	public void setPosition(float x, float y) {
		body.setTransform(x, y, body.getAngle());
	}
	
	/**
	 * Prevents the paddle from leaving the rectangle
	 * @param rect The rectangle to keep the paddle within
	 */
	public void constrainTo(Rectangle rect) {
		float padX = getWorldCenterPos().x;
		float padY = getWorldCenterPos().y;
		float velX = body.getLinearVelocity().x;
		float velY = body.getLinearVelocity().y;
		
		if (padX < rect.x && velX < 0) {
			body.setLinearVelocity(0, body.getLinearVelocity().y);
		}
		
		if (padX > rect.x + rect.width && velX > 0) { 
			body.setLinearVelocity(0, body.getLinearVelocity().y);
		}
		
		if (padY > rect.y + rect.height && velY > 0) {
			body.setLinearVelocity(body.getLinearVelocity().x, 0);
		}
		
		if (padY < rect.y && velY < 0) {
			body.setLinearVelocity(body.getLinearVelocity().x, 0);
		}
	}
}
