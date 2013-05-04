package com.funprog.tabletennis;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/*
* The Paddle class combines a body and sprite to form
* a table tennis paddle. It also provides methods to
* move.
*/
public class Paddle {
	Body body;
	/*
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
		
		// Add the body to the world
		body = world.createBody(bodyDef);
		
		// Define the shape and characteristics of the paddle
		PolygonShape paddleShape = new PolygonShape();
		paddleShape.setAsBox(0.05f, 0.5f);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = paddleShape;
		fixtureDef.density = 0.9f;
		fixtureDef.friction = 0.8f;
		fixtureDef.restitution = 0.1f;
		
		body.createFixture(fixtureDef);
		
		// Clean up
		paddleShape.dispose();
	}
	
	public void moveToward(Vector2 point) {
		Vector2 dir = point.sub(body.getWorldCenter());
		dir = dir.scl(3f / dir.len());
		//body.applyForce(dir, body.getWorldCenter(), true);
		body.setLinearVelocity(dir);
	}
	
	public void stop() {
		body.setAwake(false);
	}
}
