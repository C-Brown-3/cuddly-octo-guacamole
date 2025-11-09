package Nate;


import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;



/**
 * The Enemy class represents enemies in the game.
 * 
 * 
 * 
 * @author Nate Nielsen
 * See CSSE220 Final Project Document for Resources Used
 */

public class Enemy extends Entity{
	private final static String resourcePath = "/src/resources/vacuum.png";
	private final static double enemySpeed = 3.0;
	
	
	private BufferedImage sprite;
	private boolean spriteLoaded = false;
	
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

	public void draw(Graphics2D g2) {
		g2.translate(drawX, drawY);
		if (spriteLoaded) {
			//flips the sprite depending on the movement direction
			
			if(this.dx < 0) {
				g2.drawImage(sprite, (int)this.width, 0, (int)this.width * -1, (int) this.height, null);
			} else {
				g2.drawImage(sprite, (int)this.width, 0, (int)this.width, (int) this.height, null);
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
	
	public void moveToEdge() {
		boolean intersects=false;
		Rectangle2D.Double Bounds;
		
		if(dx>0) {
			Bounds = new Rectangle2D.Double(x+128, y+5, width, height);
		}else {
			Bounds = new Rectangle2D.Double(x-64, y+5, width, height);
		}
		
		
		
		for (Tile tile : this.levelModel.getTiles()) {
			Rectangle2D.Double tileBounds = tile.getBounds();
			if(Bounds.intersects(tileBounds)) {
		        //checks if the enemy is at the edge of a platform and switchs direction if it is
		        	intersects=true;
		        }
		}
		if(intersects) {
			this.x=this.x+this.dx;
			
		}else{
			this.dx=this.dx*(-1);
		}
		
        
	}
	
	public void tick() {
		// TODO Auto-generated method stub
		moveToEdge();
	}
}
