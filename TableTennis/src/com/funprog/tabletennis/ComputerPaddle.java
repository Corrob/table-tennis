package com.funprog.tabletennis;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * A paddle that also has the ability to predict the ball and move to hit it.
 */
public class ComputerPaddle extends Paddle {
	private float xGoal;
	
	public ComputerPaddle(World world, Vector2 position, Texture texture, float xGoal) {
		super(world, position, texture);
		
		this.xGoal = xGoal;
		
		setRotation(-0.2f);
	}
	
	public void defendPosition(Ball ball) {
		Body body = ball.getBody();
	
		if (body.getLinearVelocity().x != 0) {
			float yToApproach = intersectWithXEquals(xGoal, body.getPosition().x, 
					body.getPosition().y, body.getLinearVelocity().y / body.getLinearVelocity().x);
			
			moveToward(new Vector2(xGoal, yToApproach), 0.3f);
		}
	}
	
	private float intersectWithXEquals(float x, float x1, float y1, float m) {
		return m * (x - x1) + y1;
	}
}