package com.funprog.tabletennis;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * A class that provides a GUI control that can draw an
 * image and check if it is touched.
 */
public class ControlTool {
	Sprite sprite;
	Rectangle rect;
	
	/**
	 * Creates the tool at the given position with the image
	 * @param image The image of the tool
	 * @param position The position of the tool including 
	 * width and height
	 */
	public ControlTool(Texture image, Rectangle position) {
		// Save the rectangle to check if the tool is being touched
		rect = position;
		
		// Create the sprite with the given image and size
		sprite = new Sprite(image);
		sprite.setSize(position.getWidth(), position.getHeight());
		sprite.setPosition(position.getX(), position.getY());
		
		// Put the sprite's origin in the middle to ease rotation
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
	}
	
	/**
	 * Checks if the x and y position is in the tools
	 * rectangle.
	 * @param x The x component to check
	 * @param y The y component to check
	 * @return Whether or not the tool is being touched
	 * at the x and y values
	 */
	public boolean isTouched(float x, float y) {
		return rect.contains(x, y);
	}
	
	/**
	 * Includes the tool in the SpriteBatch
	 * @param batch The batch in which to add the tool
	 */
	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
	}
}
