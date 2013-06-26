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
	
	/**
	 * Creates a computer paddle that with set conditions.
	 * 
	 * @param world the world in which the paddle resides
	 * @param position the starting position of the paddle
	 * @param texture the texture (image) of the paddle
	 * @param xGoal the x position that the paddle strives for
	 */
	public ComputerPaddle(World world, Vector2 position, Texture texture, float xGoal) {
		super(world, position, texture);
		
		this.xGoal = xGoal;
		
		setRotation(-0.2f);
	}
	
	/**
	 * The paddle defends its position by guessing where the ball will go.
	 * 
	 * @param ball the ball that the paddle will try to hit
	 */
	// TODO(Corrob): Add capabilities of predicting if the ball will hit the table and decide
	// to hit the ball or let it pass if it will go out.
	public void defendPosition(Ball ball) {
		Body body = ball.getBody();
	
		if (body.getLinearVelocity().x != 0) {
			float yToApproach = intersectWithXEquals(xGoal, body.getPosition().x, 
					body.getPosition().y, body.getLinearVelocity().y / body.getLinearVelocity().x);
			
			moveToward(new Vector2(xGoal, yToApproach), 0.3f);
		}
	}
	
	/**
	 * Returns the y value in which the line intersects with x = value. Utilizes the
	 * equation y - y1 = m (x - x1) to solve for the unknown. 
	 * (x1, y1) is a point on the line.
	 * m is the slope of the line.
	 * 
	 * @param x the x = value where the line intersects
	 * @param x1 the x value of a point on the line
	 * @param y1 the y value of a point on the line
	 * @param m the slope of the line
	 * @return the y value of the intersection with x = value
	 */
	private float intersectWithXEquals(float x, float x1, float y1, float m) {
		return m * (x - x1) + y1;
	}
}