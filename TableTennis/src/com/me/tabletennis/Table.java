package com.me.tabletennis;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/*
 * The table class creates a static body in the world
 * at the defined location.
 */
public class Table {
	/*
	 * Construct that will add the table to the world with the specified
	 * dimensions.
	 * 
	 * @param world The world where the table is to be added
	 * @param center Then center of the table
	 * @param width The width of the table
	 * @param height The height of the table
	 */
	public Table(World world, Vector2 center, float width, float height) {
		// Create the body defintion of the table
		BodyDef groundBodyDef =new BodyDef();  
		groundBodyDef.position.set(center);  

		// Add the body to the world
		Body groundBody = world.createBody(groundBodyDef);  

		// Define the shape of the table
		PolygonShape groundBox = new PolygonShape();  
		groundBox.setAsBox(width / 2, height / 2);
		groundBody.createFixture(groundBox, 0.0f); 
		
		// Clean up
		groundBox.dispose();
	}
}
