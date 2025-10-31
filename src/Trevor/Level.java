package Trevor;
import java.awt.Color;
import java.awt.Graphics2D;


public class Level {
	private Tile ground;
	private Tile platform;
	private Tile spawn=new Tile(0,744,64,-32);
	private Tile stair;
	
	private int levelNum=1;//int to determine what level we are on
	public Level(Graphics2D g2) {
		levelOne(g2);
	}
	
	public Level(int levelNum,Graphics2D g2) {
		this.levelNum=levelNum;
		if(levelNum==1){//checks the provided level number to initialize that stage
			levelOne(g2);
		}
	}
	public void levelOne(Graphics2D g2) { //Creates the first level of the game
		Tile ground = new Tile(0,744,3200,160,Color.GREEN); //Creates the floor in which the size is a multiple of 32 for scaling with character
		ground.draw(g2);
		Tile platform = new Tile(330,394,32*12,64,Color.DARK_GRAY);//creates a platform with scale to the character
		platform.draw(g2);
		for(int k =0;k<=11;k++) { //creates a set of stairs that connects to the platform
			Tile stair = new Tile(714+(32*k),394+(k*32),32*4,352-(k*32),Color.BLUE);
			stair.draw(g2);
		}
		//Scaling is based on size 32X32
	}
	public Tile getTile(String name) {
		if(name.equals("ground")) {
			return ground;
		}
		if(name.equals("spawn")) {
			return spawn;
		}
		if(name.equals("platform")) {
			return platform;
		}
		if(name.equals("stair")) {
			return stair;
		}
		return new Tile(0,744,64,-32);
	}
}
