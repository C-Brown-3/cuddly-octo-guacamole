package polishedVersion;

import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;



public class Collectable extends Entity {
	
	
	private BufferedImage sprite;
	private boolean spriteLoaded = false;
	
	public Collectable(double x, double y) {
		super(x, y, null);	
		this.width=64;
		this.height=64;
		try {
			sprite=ImageIO.read(Collectable.class.getResource("yarn.png"));
			spriteLoaded=true;
		} catch (IOException e) {
			spriteLoaded = false;
		} catch (IllegalArgumentException e) {
			spriteLoaded = false;
		}
		
	}
	
	public void draw(Graphics2D g2) {
		if (spriteLoaded) {
			g2.translate(drawX, drawY);
			g2.drawImage(sprite, 0, 0, (int)width, (int)height, null);
			g2.translate(-drawX, -drawY);
		} else {
        	//back up if sprite is not loaded	
    		g2.setColor(Color.YELLOW);
    		g2.fillOval((int) drawX, (int) drawY,64,64);
        }
	
		
		
	}
	public void tick() {
		
	}
}
