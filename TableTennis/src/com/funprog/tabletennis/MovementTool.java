package com.funprog.tabletennis;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

/**
 * A movement tool that has the ability to move the image
 * toward the place the user has touched.
 */
public class MovementTool extends ControlTool {
	private Sprite spriteBall;
	private Body body;
	
	/**
	 * Constructs the tool with the image at the position
	 * @param world The world in which to add the movement ball
	 * @param imageOuter The image of the outer circle for tool
	 * @param positionOuter The position of the outer circle for
	 * tool including width and height
	 * @param imageInner The image of the inner circle for tool
	 */
	public MovementTool(World world, Texture imageOuter, Rectangle positionOuter,
			Texture imageInner) {
		super(imageOuter, positionOuter);
		
		// Create the sprite for the inside circle with the given image and size
		spriteBall = new Sprite(imageInner);
		spriteBall.setSize(0.30f, 0.30f);
	
		// Put the sprite's origin in the middle to ease rotation
		spriteBall.setOrigin(spriteBall.getWidth() / 2, spriteBall.getHeight() / 2);
		
		// Create the body definition
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.KinematicBody;
		bodyDef.position.set(positionOuter.getX() + positionOuter.getWidth() / 2,
				positionOuter.getY() + positionOuter.getHeight() / 2);
		
		// Add the body to the world
		body = world.createBody(bodyDef);
		
		// Define the shape and characteristics of the ball
		CircleShape circle = new CircleShape();
		circle.setRadius(0.15f);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 0.5f;
		fixtureDef.friction = 0.2f;
		fixtureDef.restitution = 0;
	
		body.createFixture(fixtureDef);
		
		// Clean up
		circle.dispose();
		
		body.setUserData(spriteBall);
	}
	
	/**
	 * Calls the paddle to move and updates the tool based
	 * on user input.
	 * @param x The x value of the touch
	 * @param y The y value of the touch
	 * @param pad The paddle to be moved
	 */
	public void updateTouch(float x, float y, Paddle pad) {
		// Calculate the vector from the tool's center to the touch point
		Vector2 touch = new Vector2(x, y);
		Vector2 dir = touch.sub(new Vector2(sprite.getX() + sprite.getOriginX(),
				sprite.getY() + sprite.getOriginY()));
		
		// Moves the inside circle
		moveBallToward(dir);
		
		// Determines speed of paddle based on input of movement tool
		float speed = 1.0f;
		if (dir.len2() > 250) {
			speed = 1.5f;
		} else {
			speed = 0.6f;
		}
		
		// Moves the paddle
		Vector2 padDir = new Vector2(dir.x, dir.y);
		padDir = padDir.add(pad.getWorldCenterPos());
		
		// Aligns the input of movement tool with the movement of paddle based
		// on the quadrant in which the input was received 
		if (x < sprite.getX() + sprite.getOriginX() && 
				y < sprite.getY() + sprite.getOriginY()) {
			pad.moveToward(new Vector2(padDir.x - x / 3, padDir.y - y / 3), speed);
		} else if (x < sprite.getX() + sprite.getOriginX() && 
				y > sprite.getY() + sprite.getOriginY()) {
			pad.moveToward(new Vector2(padDir.x - x / 3, padDir.y + y / 3), speed);
		} else if (x > sprite.getX() + sprite.getOriginX() && 
				y < sprite.getY() + sprite.getOriginY()) {
			pad.moveToward(new Vector2(padDir.x + x / 3, padDir.y - y / 3), speed);
		} else if (x > sprite.getX() + sprite.getOriginX() && 
				y > sprite.getY() + sprite.getOriginY()) {
			pad.moveToward(new Vector2(padDir.x + x / 3, padDir.y + y / 3), speed);
		} else {
			pad.moveToward(padDir, speed);
		}
	}
	
	/**
	 * Moves the inside circle toward the point.
	 * @param point The point for the circle to move to
	 */
	public void moveBallToward(Vector2 dir) {
		float distAway = dir.len();
		repositionBall();
		// Check if the inside circle is far enough from the point
		if (distAway > 0.1f && !isOutOfBounds()) {
			dir = dir.scl(30);
			body.setLinearVelocity(dir);
		}
		else {
			stopMovingBall();
			repositionBall();
		}
	}
	
	/**
	 * Stops the circle from moving.
	 */
	public void stopMovingBall() {
		body.setLinearVelocity(Vector2.Zero);
	}
	
	/**
	 * Returns the inside circle to the origin of the tool.
	 */
	public void repositionBall() {
		body.setTransform(sprite.getX() + sprite.getOriginX(),
				sprite.getY() + sprite.getOriginY(), body.getAngle());
	}
	
	/**
	 * Checks if the inside circle is out of bounds of the outer circle.
	 * @return Whether or not the inside circle is outside bounds
	 */
	public boolean isOutOfBounds() {
		return (body.getWorldCenter().x > sprite.getX() + sprite.getWidth()
				&& body.getWorldCenter().x < sprite.getX()) 
				|| (body.getWorldCenter().y > sprite.getY() + sprite.getHeight()
						&& body.getWorldCenter().y < sprite.getY());
	}
	
	/**
	 * Returns the length of the linear velocity of the inside circle. 
	 * @return The length of the linear velocity of the inside circle
	 */
	public float getHowFast() {
		return body.getLinearVelocity().len();
	}
}
