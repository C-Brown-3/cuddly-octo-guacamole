package Carson;


import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.Rectangle;
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
	private final static String resourcePath = "resources/vacuum.png";
	private final static double enemySpeed = 3.0;
	private int height;
	private int width;
	
	private static int gravity = 3;
	
	private BufferedImage sprite;
	private boolean spriteLoaded = false;
	
	public Enemy(double x, double y) {
		super(x, y);
		this.height = 64;
		this.width = 64;
		this.dx = enemySpeed; 
		
		sprite = this.bufferImage(resourcePath);
	}

	public void draw(Graphics2D g2) {
		if (spriteLoaded)
			//flips the sprite depending on the movement direction
			if(this.dx < 0)
				g2.drawImage(sprite, this.drawX + this.width, this.drawY, this.width * -1, this.height, null);
			else {
				g2.drawImage(sprite, this.drawX, this.drawY, this.width, this.height, null);
			}
            
        else {
        	//back up if sprite is not loaded
        	Rectangle rect = new Rectangle(drawX, drawY, width, height);
    		g2.setColor(Color.RED);
    		g2.fill(rect);
    		g2.draw(rect);
        }
	}
	
	public void tick() {
		// TODO Auto-generated method stub
		
	}
}
