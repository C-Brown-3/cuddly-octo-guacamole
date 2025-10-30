package Trevor;

import java.awt.Color;
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
		new tile(0,750,3000,150,Color.GREEN).draw(g2);
		new tile(350,400,400,75,Color.DARK_GRAY).draw(g2);
		
	}
	
}
