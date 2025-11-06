package polishedVersion;

import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Collectable extends Entity {
	private final int height = 64;
	private final int width = 64;
	
	private BufferedImage sprite;
	private boolean spriteLoaded = false;
	
	public Collectable(double x, double y, LevelModel levelModel) {
		super(x, y, levelModel);		
		
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
			g2.drawImage(sprite, drawX, drawY, width, height, null);
        else {
        	//back up if sprite is not loaded	
    		g2.setColor(Color.YELLOW);
    		g2.fillOval(drawX,drawY,64,64);
        }
		
		
	}
}
