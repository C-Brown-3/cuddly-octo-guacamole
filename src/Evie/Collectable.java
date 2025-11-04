package Evie;


import java.awt.Color;
import java.awt.Graphics2D;


import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * The Collectable class represents collectables in the game.
 * 
 * The collectable is drawn on the screen and has sprite
 * 
 * @author Nate Nielsen
 * See CSSE220 Final Project Document for Resources Used
 */



public class Collectable extends Entity {
	
	
	
	
	private BufferedImage sprite;
	private boolean spriteLoaded = false;
	
	public Collectable() {
		super(200,200);
		this.height=64;
		this.width=64;
		
		
		
		try {
			sprite=ImageIO.read(Collectable.class.getResource("resources/yarn.png"));
			spriteLoaded=true;
		} catch (IOException e) {
			spriteLoaded = false;
		} catch (IllegalArgumentException e) {
			spriteLoaded = false;
		}
		
	}
	public Collectable(int x, int y, int height, int width) {
		super (x,y);
		this.height=height;
		this.width=width;
		
		try {
			sprite=ImageIO.read(Collectable.class.getResource("resources/yarn.png"));
			spriteLoaded=true;
		} catch (IOException e) {
			spriteLoaded = false;
		} catch (IllegalArgumentException e) {
			spriteLoaded = false;
		}
		
	}
	
	
	public void draw(Graphics2D g2) {
		if (spriteLoaded)	
			g2.drawImage(sprite, x, y, width, height, null);
        else {
        	//back up if sprite is not loaded
        	
        	
        		
    		g2.setColor(Color.YELLOW);
    		g2.fillOval(x,y,64,64);
    		
        }
		
		
	}
}
