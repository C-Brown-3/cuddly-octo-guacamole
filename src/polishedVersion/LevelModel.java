package polishedVersion;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * LevelModel creates easy access to the active level of the game as well as provides features
 * for loading new levels and reading level description txt files.
 */
public class LevelModel implements TopLevelClass{
	private Level activeLevel;
	private double[] levelBoundaries;
	
	public double[] getLevelBoundaries() {
		return levelBoundaries;
	}

	public LevelModel() {
		// TODO Auto-generated constructor stub
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
					switch (c) {
						case '*':		//A regular tile
							tiles.add(new Tile(Tile.DEFAULT_SIZE * i, Tile.DEFAULT_SIZE * j));
							break;
							
						case '@':
							spawnX = i*Tile.DEFAULT_SIZE;
							spawnY = j*Tile.DEFAULT_SIZE; 
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
}
