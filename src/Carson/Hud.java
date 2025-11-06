package Carson;



import java.awt.Font;
import java.awt.Graphics2D;


public class Hud {
	private int lives;
	private int score;
	private int level;
	
	public Hud() {
		this.lives=3;
		this.score=0;
		this.level=1;
		
		
		
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
    	g2.drawString("Lives: "+this.lives, 10, 30);
    	g2.drawString("Score: "+this.score, 200, 30);
    	g2.drawString("Level: "+this.level, 400, 30);
	}
        
}
