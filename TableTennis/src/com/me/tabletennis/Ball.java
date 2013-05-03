package com.me.tabletennis;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/*
 * The Ball class maintains a sprite and body for a ball
 * and provides information and methods for a table tennis
 * ball.
 */
public class Ball {
	Body body;
	/*
	 * The constructor that requires the world in which to
	 * add the ball and the position where it starts.
	 * 
	 * @param world The world to add the ball
	 * @param position The position where the ball starts
	 */
	public Ball(World world, Vector2 position) {
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
		fixtureDef.density = 0.5f; 
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.8f;

		body.createFixture(fixtureDef);
		
		// Clean up
		circle.dispose();
	}
	
	public void gravity() {
		body.applyForce(new Vector2(0, -0.5f), body.getWorldCenter(), true);
	}
}
