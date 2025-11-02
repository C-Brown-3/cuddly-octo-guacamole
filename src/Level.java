import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;


public class Level {
	private Tile ground;
	private Tile platform1;
	private Tile platform2;
	private Tile platform3;
	//private Tile stair;
	private int levelNum=1;//int to determine what level we are on
	
	public Level() {
		levelOne(); //Calls level 1
	}
	
	public Level(int levelNum) {
		this.levelNum=levelNum;
		if(levelNum==1){//checks the provided level number to initialize that stage
			levelOne();
		}
	}
	public void levelOne() { //Creates the first level of the game
		ground = new Tile(0,744,3200,160,Color.GREEN); //Creates the floor in which the size is a multiple of 32 for scaling with character
		platform1 = new Tile(400,350,32*12,64,Color.DARK_GRAY);//creates a platform with scale to the character
		platform2 = new Tile(100,550,32*12,64,Color.DARK_GRAY);//creates a platform with scale to the character
		platform3 = new Tile(700,550,32*12,64,Color.DARK_GRAY);//creates a platform with scale to the character
//		for(int k =0;k<=11;k++) { //creates a set of stairs that connects to the platform
//			stair = new Tile(714+(32*k),394+(k*32),32*4,352-(k*32),Color.BLUE);
//		}
		//Scaling is based on size 32X32
	}
	public int spawnX() {
		return 64*2;
	}
	public int spawnY() {
		return 650;
	}
	public ArrayList getTiles() {
		ArrayList<Tile> tiles = new ArrayList<>();
		tiles.add(ground);
		tiles.add(platform1);
		tiles.add(platform2);
		tiles.add(platform3);
		return tiles;
	}
	public void levelDraw(Graphics2D g2) {
		ground.draw(g2);
		platform1.draw(g2);
		platform2.draw(g2);
		platform3.draw(g2);
//		for(int k =0;k<=11;k++) { //creates a set of stairs that connects to the platform
//			Tile stair = new Tile(714+(32*k),394+(k*32),32*4,352-(k*32),Color.BLUE);
//			stair.draw(g2);
//		}
	}
}