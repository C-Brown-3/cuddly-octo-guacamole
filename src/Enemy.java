

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




public class Enemy {
	private int x;
	private int y;
	private int height;
	private int width;
	
	private static int gravity=3;
	private int velocityX;
	private int velocityY;
	
	private BufferedImage sprite;
	private boolean spriteLoaded = false;
	
	public Enemy() {
		this.x=110;
		this.y=100;
		this.height=64;
		this.width=64;
		
		this.velocityX=3;
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
		this.x=x;
		this.y=y;
		this.height=height;
		this.width=width;
		
		this.velocityX=3;
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
	
	public void moveToEdge(Tile tile) {
        Rectangle platformBounds = tile.getBounds();
        //checks if the enemy is at the edge of a platform and switchs direction if it is
        if(platformBounds.getMaxX()<this.x+this.velocityX+this.width || platformBounds.getMinX()>this.x+this.velocityX) {
        	this.velocityX=this.velocityX*(-1);
        }else {
        	this.x=this.x+this.velocityX;
        }
		
	}

	public void update(Tile tile) {
        int prevY = y; // track previous position
        y += velocityY;
        velocityY += gravity;

        Rectangle enemyBounds = new Rectangle(x, y, width, height);
        Rectangle platformBounds = tile.getBounds();

        // Check collision only while falling
        if (velocityY >= 0 && enemyBounds.intersects(platformBounds)) {
            int playerBottomPrev = prevY + height;
            int tileTop = tile.getTop();

            // Enemy was above platform last frame, now intersecting = landed
            if (playerBottomPrev <= tileTop) {
                y = tileTop - height;
                velocityY = 0;
            }
        }
     // Ground collision
        if (y + height >= 700) {
            y = 700 - height;
            velocityY = 0;
        }
    }
	public void draw(Graphics2D g2) {
		if (spriteLoaded)
			//flips the sprite depending on the movement direction
			if(this.velocityX<0)
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
