

import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The Enemy class represents enemies in the game.
 * 
 * The enemy is affected by gravity and interacts/collides with platform. 
 * Enemy also moves back and forth on a platform and has a spite the flips
 * depending on it's movement direction
 * 
 * @author Nate Nielsen
 * See CSSE220 Final Project Document for Resources Used
 */




public class Enemy extends Entity {
	
	
	private BufferedImage sprite;
	private boolean spriteLoaded = false;
	
	public Enemy() {
		super(110,110);
		this.x=110;
		this.y=100;
		this.height=64;
		this.width=64;
		
		this.speed=20;
		this.velocityY=0;
		
		
		try {
			sprite=ImageIO.read(Enemy.class.getResource("resources/vacuum.png"));
			spriteLoaded=true;
		} catch (IOException e) {
			spriteLoaded = false;
		} catch (IllegalArgumentException e) {
			spriteLoaded = false;
		}
		
	}
	public Enemy(int x, int y, int height, int width) {
		super(x,y);
		this.x=x;
		this.y=y;
		this.height=height;
		this.width=width;
		
		this.speed=6;
		this.velocityY=0;
		
		try {
			sprite=ImageIO.read(Enemy.class.getResource("resources/vacuum.png"));
			spriteLoaded=true;
		} catch (IOException e) {
			spriteLoaded = false;
		} catch (IllegalArgumentException e) {
			spriteLoaded = false;
		}
		
	}
	//moves the enemy for edge to edge of the platform it is on
	public void moveToEdge(Tile tile) {
		Rectangle Bounds = new Rectangle(x, y, width, height);
		Rectangle tileBounds = tile.getBounds();
        if(Bounds.intersects(tileBounds)) {
        //checks if the enemy is at the edge of a platform and switchs direction if it is
        	if(tileBounds.getMaxX()<this.x+this.speed+this.width || tileBounds.getMinX()>this.x+this.speed) {
        		this.speed=this.speed*(-1);
        	}else {
        		this.x=this.x+this.speed;
        	}
        }
	}

	
	public void checkCollision() {
		
	}
	public void gravity() {
    	prevY = y;
        prevX = x;
        y += velocityY;
        velocityY += gravity;
    }
	public void draw(Graphics2D g2) {
		if (spriteLoaded)
			//flips the sprite depending on the movement direction
			if(this.speed<0)
				g2.drawImage(sprite, this.x + this.width, this.y, -1*this.width, this.height, null);
			else {
				g2.drawImage(sprite, x, y, width, height, null);
			}
            
        else {
        	//back up if sprite is not loaded
        	Rectangle rect=new Rectangle(x,y,width,height);
    		g2.setColor(Color.RED);
    		g2.fill(rect);
    		g2.draw(rect);
        }
		
		
	}
}
