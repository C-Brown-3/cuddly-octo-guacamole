package Nate;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This is the HUD class
 * 
 * @author Nate Nielsen
 * See CSSE220 Final Project Document for Resources Used
 */

public class HUD {
	private int lives;
	private int score;
	private int level;
	
	
	private BufferedImage sprite;
	private boolean spriteLoaded = false;
	
	public HUD() {
		this.lives=3;
		this.score=0;
		this.level=1;
		
		try {
			sprite=ImageIO.read(Enemy.class.getResource("resources/heart.png"));
			spriteLoaded=true;
		} catch (IOException e) {
			spriteLoaded = false;
		} catch (IllegalArgumentException e) {
			spriteLoaded = false;
		}
		
		
		
	}
	
	public boolean gameOver() {
		return this.lives <=0;
	}
	public int getScore() {
		return this.score;
	}
	public int getLevel() {
		return this.level;
	}
	public void updateLevel(int newLevel) {
		this.level=newLevel;
	}
	public void incrementScore(int newScore) {
		this.score+=newScore;
	}
	
	public void decrementLives() {
		this.lives-=1;
	}
	
	public void draw(Graphics2D g2) {
		g2.setFont(new Font("Stencil", Font.PLAIN, 36));
		if(this.lives <=0) {
			Rectangle rect=new Rectangle(0,0,1200,1200);
			g2.setColor(Color.BLACK);
			g2.draw(rect);
			g2.fill(rect);
			g2.setColor(Color.RED);
			g2.drawString("GAME OVER ", 400, 400);
		}else {
			
			if(spriteLoaded) {
				g2.drawString("Lives: ", 10, 30);
    		for (int i = 0; i < this.lives; i++) {
    			g2.drawImage(sprite, 120+i*50, -10, 64, 64, null);
			}
			}else {
				g2.drawString("Lives: "+this.lives, 10, 30);
			}
    		g2.drawString("Score: "+this.score, 300, 30);
    		g2.drawString("Level: "+this.level, 500, 30);
		}
	}
        
}
