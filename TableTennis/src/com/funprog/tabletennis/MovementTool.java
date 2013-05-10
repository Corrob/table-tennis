package com.funprog.tabletennis;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * A movement tool that has the ability to move the image
 * toward the place the user has touched.
 */
public class MovementTool extends ControlTool {
	private Sprite spriteBall;
	
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
		spriteBall.setOrigin(spriteBall.getWidth() / 2, spriteBall.getHeight() / 2);
		spriteBall.setPosition(sprite.getX() + sprite.getOriginX() - spriteBall.getOriginX(), 
				sprite.getY() + sprite.getOriginY() - spriteBall.getOriginY());
	}
	
	/**
	 * Calls the paddle to move and updates the tool based
	 * on user input.
	 * @param x The x value of the touch
	 * @param y The y value of the touch
	 * @param pad The paddle to be moved
	 * @param worldWidth The width of the world
	 * @param worldHeight The height of the world
	 */
	public void updateTouch(float x, float y, Paddle pad, float worldWidth, float worldHeight) {
		Vector2 touch = new Vector2(x, y);
		
		// Moves the inside circle
		moveBallTo(touch);
		
		// Calculate the vector from the tool's center to the touch point
		Vector2 dir = touch.sub(new Vector2(sprite.getX() + sprite.getOriginX(),
				sprite.getY() + sprite.getOriginY()));
		
		pad.setVelocity(dir);
	}
	
	/**
	 * Moves the inside circle to the point.
	 * @param pos The point for the circle to move to
	 */
	public void moveBallTo(Vector2 pos) {
		if (sprite.getBoundingRectangle().contains(pos.x, pos.y)) {
			spriteBall.setPosition(pos.x - spriteBall.getOriginX(),
					pos.y - spriteBall.getOriginY());
		}
	}
	
	/**
	 * Returns the inside circle to the origin of the tool.
	 */
	public void repositionBall() {
		spriteBall.setPosition(sprite.getX() + sprite.getOriginX() - spriteBall.getOriginX(), 
				sprite.getY() + sprite.getOriginY() - spriteBall.getOriginY());
	}
	
	/**
	 * Includes the tool(both circles) in the SpriteBatch
	 * @param batch The batch in which to add the tool
	 */
	@Override
	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
		spriteBall.draw(batch);
	}
}
