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
		spriteBall.setSize(0.40f, 0.40f);
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
		
		// Calculate the vector from the tool's center to the touch point
		Vector2 dir = touch.sub(new Vector2(sprite.getX() + sprite.getOriginX(),
				sprite.getY() + sprite.getOriginY()));
		
		touch = new Vector2(x, y);
		
		if (inEllipse(dir)) {
			// Moves the inside circle
			moveBallTo(touch);
			
			pad.setVelocity(dir);
		}
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
	
	/**
	 * Use ellipse equation to check if the touch is in the ellipse inscribed
	 * in the bounding rectangle. Ellipse: x^2/a^2 + y^2/b^2 = 1
	 * @param dir The direction from the center
	 * @return True if the vector is in the ellispe and false if it isn't
	 */
	private boolean inEllipse(Vector2 dir) {
		float a = rect.getWidth() / 2 - spriteBall.getWidth() / 2;
		float b = rect.getHeight() / 2 - spriteBall.getHeight() / 2;
		
		return dir.x * dir.x / (a * a) + dir.y * dir.y / (b * b) < 1;
	}
}
