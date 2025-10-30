package Trevor;

import java.awt.Graphics2D;

public class level {
	private int levelNum=1;//int to determine what level we are on
	public level(Graphics2D g2) {
		levelOne(g2);
	}
	
	public level(int levelNum,Graphics2D g2) {
		this.levelNum=levelNum;
		if(levelNum==1){//checks the provided level number to initialize that stage
			levelOne(g2);
		}
	}
	public void levelOne(Graphics2D g2) { //Creates the first level of the game
		new tile(0,350,600,50).draw(g2);
		
	}
	
}
