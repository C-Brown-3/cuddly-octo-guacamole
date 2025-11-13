package polishedVersion;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * LevelModel creates easy access to the active level of the game as well as provides features
 * for loading new levels and reading level description txt files.
 */
public class LevelModel implements TopLevelClass{
	private Level activeLevel;
	private double[] levelBoundaries;
	private String tileSetFilePath = "../resources/jungleTileset.png";
	private BufferedImage tileSet;
	
	public double[] getLevelBoundaries() {
		if (activeLevel == null) return null;
		return levelBoundaries;
	}

	public LevelModel() {
		// TODO Auto-generated constructor stub
		try {
			tileSet = ImageIO.read(this.getClass().getResource(tileSetFilePath));
		} catch (IOException e) {
			System.out.println("Tileset image load error.A");
		} catch (IllegalArgumentException e) {
			System.out.println("Tileset image load error.B");
		}
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		if (activeLevel == null) {
			return;
		}
		activeLevel.draw(g2);
	}
	
	public int getLevelID() {
		if (activeLevel == null) {
			return -1;
		}
		return activeLevel.getId();
	}
	
	public ArrayList<Tile> getTiles() {
		return activeLevel.getTiles();
	}

	/**
	 * Attempts to update the activeLevel variable to the level id provided. 
	 * Sets the level to null on a failure.
	 * @param id
	 * @return 
	 */
	public boolean loadLevel(int id) {
		File levelFile = new File("src/levelData/level" + id + ".txt");
		System.out.println("src/levelData/level" + id + ".txt");
		if (!levelFile.exists()) {
			//Replace with a void level probably
			activeLevel = null;
			return false;
		}
		
		try (BufferedReader in = new BufferedReader(new FileReader(levelFile))) {
			//Think of i and j as x and y
			ArrayList<Tile> tiles = new ArrayList<>();
			ArrayList<Enemy> enemies = new ArrayList<>();
			ArrayList<Collectable> item = new ArrayList<>();
			double spawnX = 0, spawnY = 0;
			String line;
			int lasti = 0;
			int lastj = 0;
			int j = -1;
			int i = 0;
			while ((line = in.readLine()) !=  null) {
				j += 1;
				lastj = j;
				for (i = 0; i < line.length(); i ++) {
					lasti = i;
					char c = line.charAt(i);
					switch (c) { //192 x 208 is top left of the main tiles
						case '*':		//A regular tile
							tiles.add(makeTile(i, j, 0));
							break;
						case '-':		//A tile with a top edge
//							System.out.println("HERE");
							tiles.add(makeTile(i, j, 1));
							break;
						case ']':
							tiles.add(makeTile(i, j, 2));
							break;
						case '|':
							tiles.add(makeTile(i, j, 3));
							break;
						case ';':
							tiles.add(makeTile(i, j, 4));
							break;
						case '_':
							tiles.add(makeTile(i, j, 5));
							break;
						case ':':
							tiles.add(makeTile(i, j, 6));
							break;
						case 'L':
							tiles.add(makeTile(i, j, 7));
							break;
						case '[':
							tiles.add(makeTile(i, j, 8));
							break;
						case '{':
							tiles.add(makeTile(i, j, 9));
							break;
						case '}':
							tiles.add(makeTile(i, j, 10));
							break;
						case '<':
							tiles.add(makeTile(i, j, 11));
							break;
						case '=':
							tiles.add(makeTile(i, j, 12));
							break;
						case '>':
							tiles.add(makeTile(i, j, 13));
							break;
						case '.':
							tiles.add(makeTile(i, j, 14));
							break;
						case ',':
							tiles.add(makeTile(i, j, 15));
							break;
						case '@':
							spawnX = i*Tile.DEFAULT_SIZE;
							spawnY = j*Tile.DEFAULT_SIZE; 
//							System.out.println(spawnX +"|"+ spawnY);
							break;
						case '!':
							enemies.add(new Enemy((double)i*Tile.DEFAULT_SIZE,(double)j*Tile.DEFAULT_SIZE));
							break;
						case '$':
							item.add(new Collectable((double)i*Tile.DEFAULT_SIZE,(double)j*Tile.DEFAULT_SIZE));
							break;
						case ' ':
							break;
						default:
							//Character not recognized -- print to sysout, but otherwise continue
							System.out.println("Level level" + id + "data contained character: " + line.charAt(i) + ", which is not implemented.");
					}
				}
			}
			double[] bounds = {lasti * 64.0, lastj * 64.0};
			this.levelBoundaries = bounds;
			this.activeLevel = new Level(tiles, enemies, item, spawnX, spawnY, id);
		} catch (IOException e) {
			System.out.println("Level loading failure.");
			return false;
		}
		return true;
	}

	public double[] getSpawnCoords() {
		// TODO Auto-generated method stub
		return activeLevel.getSpawnCoords();
	}

	public ArrayList<Enemy> getEnemies() {
		// TODO Auto-generated method stub
		return activeLevel.cloneEnemies();
	}
	public ArrayList<Collectable> getCollectables(){
		// TODO Auto-generated method stub
		return activeLevel.cloneCollectables();
	}
	
	/**
	 * Helper function to make tiles while loading the level
	 * @param i
	 * @param j
	 * @param subImageOption
	 */
	private Tile makeTile(int i, int j, int subImageOption) {
		BufferedImage img;
		if(tileSet == null) {
			img = null;
		} else {
			switch (subImageOption) {
			//  812
			//  703
			//  654
			case 0: //No grass
				img = null;
				break;
			case 1: //Grass on the top ONLY
				img = tileSet.getSubimage(224, 272, 32, 32);
				break;
			case 2:
				img = tileSet.getSubimage(16*7, 32, 32, 32);
				break;
			case 3:
				img = tileSet.getSubimage(16*12, 16*15, 32, 32);
				break;
			case 4:
				img = tileSet.getSubimage(16*7, 5*16, 32, 32);
				break;
			case 5:
				img = tileSet.getSubimage(16*14, 16*13, 32, 32);
				break;
			case 6:
				img = tileSet.getSubimage(16*4, 16*5, 32, 32);
				break;
			case 7:
				img = tileSet.getSubimage(16*16, 16*15, 32, 32);
				break;
			case 8:
				img = tileSet.getSubimage(16*4, 16*2, 32, 32);
				break;
			case 9: //Top right corner has grass
				img = tileSet.getSubimage(12*16, 16*17, 32, 32);
				break;
			case 10: //Top left corner has grass
				img = tileSet.getSubimage(16*16, 16*17, 32, 32);
				break;
			case 11:
				img = tileSet.getSubimage(16*8, 16*8, 32, 32);
				break;
			case 12:
				img = tileSet.getSubimage(16*9, 16*8, 32, 32);
				break;
			case 13:
				img = tileSet.getSubimage(16*13, 16*8, 32, 32);
				break;
			case 14: //Bottom right corner has grass
				img = tileSet.getSubimage(16*12, 16*13, 32, 32);
				break;
			case 15: //Bottom left corner has grass
				img = tileSet.getSubimage(16*16, 16*13, 32, 32);
				break;
			default:
				img = null;
				break;
			}
		}
		Tile tile = new Tile(i * Tile.DEFAULT_SIZE, j * Tile.DEFAULT_SIZE, img);
		return tile;
	}
	//Tile Image Option Definitions
	
//	private static int 
}
