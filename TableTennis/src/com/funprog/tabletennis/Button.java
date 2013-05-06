package com.funprog.tabletennis;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * The Button class provides functionality for displaying
 * a texture checking if it was touched or clicked.
 *
 */
public class Button {
	Rectangle rect;
	Texture texture;
	
	/**
	 * Constructs a Button with the corresponding image
	 * at the position and size of the rectangle
	 * @param image
	 * @param position
	 */
	public Button(Texture image, Rectangle position) {
		texture = image;
		rect = position;
	}
	
	/**
	 * Checks if the x and y position is in the buttons
	 * rectangle.
	 * @param x The x component to check
	 * @param y The y component to check
	 * @return Whether or not the button is being touched
	 * at the x and y values
	 */
	public boolean isTouched(float x, float y) {
		return rect.contains(x, y);
	}
	
	/**
	 * Includes the Button in the SpriteBatch
	 * @param batch The batch in which to add the Button
	 */
	public void draw(SpriteBatch batch) {
		batch.draw(texture, rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
	}
}
