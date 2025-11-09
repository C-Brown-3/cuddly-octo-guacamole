package polishedVersion;


import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;



/**
 * The Enemy class represents enemy in the game. The enemy has collision with tiles and is moved by gravity. 
 * Enemy also has a method to move for edge to edge on a platform. Enemy is drawn to frame and is given a vacuum sprite.
 * 
 * 
 * 
 * @author Nate Nielsen
 * See CSSE220 Final Project Document for Resources Used
 */

public class Enemy extends Entity{
	
	private final static double enemySpeed = 3.0;
	
	
	private static int gravity = 3;
	
	private BufferedImage sprite;
	private boolean spriteLoaded = false;
	
	/**
     * Constructs the enemy class
     * 
     * @param x is the starting x coordinate of enemy
     * @param y is the starting y coordinate of enemy
     */
	public Enemy(double x, double y) {
		super(x, y, null);
		this.height = 64;
		this.width = 64;
		this.dx = enemySpeed; 
		
		
		try {
			sprite=ImageIO.read(Enemy.class.getResource("vacuum.png"));
			spriteLoaded=true;
		} catch (IOException e) {
			
			spriteLoaded = false;
		} catch (IllegalArgumentException e) {
			spriteLoaded = false;
		}
	}
	
	
	/**
     * Draws the enemy sprite on the screen, flipping it horizontally if 
     * facing left. If the sprite is not loaded, a red rectangle is drawn instead.
     *
     * @param g the graphics context to draw the enemy
     */
	public void draw(Graphics2D g2) {
		g2.translate(drawX, drawY);
		if (spriteLoaded) {
			//flips the sprite depending on the movement direction
			
			if(this.dx < 0) {
				g2.drawImage(sprite, (int)this.width, 0, (int)this.width * -1, (int)this.height, null);
			} else {
				g2.drawImage(sprite, (int)this.width, 0, (int)this.width, (int)this.height, null);
			} 
			
		} else {
        	//back up if sprite is not loaded
			Rectangle2D.Double rect = new Rectangle2D.Double(0, 0, width, height);
			g2.setColor(Color.RED);
			g2.fill(rect);
			g2.draw(rect);
		}
		
		g2.translate(-drawX, -drawY);
	}
	/**
     * Moves the enemy for edge to edge on a platform by checking if it was shifted over would it be floating in mid air
     *
     *@param g2 is the graphics for the viewing frame
     */
	public void moveToEdge() {
		boolean intersects=false;
		Rectangle2D.Double Bounds;
		
		//The bounds are shifted because that way enemy doesn't go past the edge until it isn't on the platform
		//The y+5 just allows the .intersects function to work by checking if the enemy on the tile
		if(dx>0) {
			Bounds = new Rectangle2D.Double(x+128, y+5, width, height);
		}else {
			Bounds = new Rectangle2D.Double(x-64, y+5, width, height);
		}
		
		
		
		for (Tile tile : this.levelModel.getTiles()) {
			Rectangle2D.Double tileBounds = tile.getBounds();
			if(Bounds.intersects(tileBounds)) {
		        	intersects=true;
		        }
		}
		if(intersects) {
			this.x=this.x+this.dx;
			
		}else{
			//switches movement direction
			this.dx=this.dx*(-1);
		}
		
        
	}
	
	/**
	 * Everything the enemy does once per frame
	 */
	public void tick() {
		// TODO Auto-generated method stub
		this.moveToEdge();
	}
}
