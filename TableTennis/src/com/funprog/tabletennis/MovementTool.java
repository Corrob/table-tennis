package com.funprog.tabletennis;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MovementTool extends ControlTool {

	/**
	 * Constructs the tool with the image at the position
	 * @param image The image of the tool
	 * @param position The position of the tool including
	 * width and height
	 */
	public MovementTool(Texture image, Rectangle position) {
		super(image, position);
	}
	
	/**
	 * Updates the too based on user input by rotating the image
	 * so that its top is always facing the touch
	 * @param x The x value of the touch
	 * @param y The y value of the touch
	 */
	public void updateTouch(float x, float y) {
		// Calculate the vector from the sprite's center to the touch point
		Vector2 touch = new Vector2(x, y);
		touch = touch.sub(sprite.getX() + sprite.getOriginX(),
				sprite.getY() + sprite.getOriginY());	
	}
		

}
