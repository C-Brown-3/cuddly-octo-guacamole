package polishedVersion;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Hud represents the hud of the game. It displays the current level, lives and score. It also checks for when 
 * there is a game over and prompts the player to reset.
 * 
 * @author Nate Nielsen
 * See CSSE220 Final Project Document for Resources Used
 */


public class Hud extends Drawable{
	private int lives;
	private int score;
	private int level;
	
	private BufferedImage sprite;
	private boolean spriteLoaded = false;
	
	
	/**
     * Constructs the hud class
     * 
     */
	public Hud() {
		this.lives=3;
		this.score=0;
		this.level=1;
		
		
		try {
			sprite=ImageIO.read(Hud.class.getResource("heart.png"));
			spriteLoaded=true;
		} catch (IOException e) {
			
			spriteLoaded = false;
		} catch (IllegalArgumentException e) {
			spriteLoaded = false;
		}
		
	}
	
	/*
	public boolean gameOver(){
		return lives<=0;
	}
	public int getScore() {
		return this.score;
	}
	*/
	
	public int getLevel() {
		return this.level;
	}
	
	public boolean winLevel() {
		return score>=30;
	}
	/**
     * Setter method for the level
     */
	public void updateLevel(int newLevel) {
		this.level=newLevel;
	}
	/**
     * Increments the score
     *@param newScore is the amount the score will be increased by
     */
	public void incrementScore(int newScore) {
		this.score+=newScore;
	}
	/**
     * Decrements the number of lives by one
     */
	public void decrementLives() {
		this.lives-=1;
	}
	
	/**
     * Resets the hud
     *@param level the hud will be reset to
     */
	public void reset(int level) {
		this.lives=3;
		this.score=0;
		this.level=level;
	}
	/**
     * Draws the hud on the screen. Checks if the game is over and if it is
     * puts a black rectangle to cover the screen and prompts the player to reset. 
     * If the sprite is not loaded, the lives is represented by a number.
     *
     * @param g the graphics context to draw the player
     */
	public void draw(Graphics2D g2) {
		
		g2.setFont(new Font("Stencil", Font.PLAIN, 36));
		if(this.level==-1) {
			Rectangle rect=new Rectangle(0,0,2000,2000);
			g2.setColor(Color.BLACK);
			g2.draw(rect);
			g2.fill(rect);
			g2.setColor(Color.GREEN);
			g2.drawString("You win! ", 250, 200);
			g2.drawString("Press \"ENTER\" to continue", 150, 300);
		}else if(this.lives <=0) {
			Rectangle rect=new Rectangle(0,0,2000,2000);
			g2.setColor(Color.BLACK);
			g2.draw(rect);
			g2.fill(rect);
			g2.setColor(Color.RED);
			g2.drawString("game over ", 250, 200);
			g2.drawString("Press \"ENTER\" to continue", 150, 300);
			g2.drawString("Press \"U\" to continue to next level", 150, 400);
			g2.drawString("Press \"D\" to go to previous level", 150, 500);
			
		}else {
			g2.setColor(Color.GREEN);
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
