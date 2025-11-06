package Carson;

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
	 */
	public void loadLevel(int id) {
		File levelFile = new File("src/levelData/level" + id + ".txt");
		if (!levelFile.exists()) {
			//Replace with a void level probably
			activeLevel = null;
//			return;
		}
		
		try (BufferedReader in = new BufferedReader(new FileReader(levelFile))) {
			//Think of i and j as x and y
			ArrayList<Tile> tiles = new ArrayList<>();
			ArrayList<Enemy> enemies = new ArrayList<>();
			ArrayList<Collectable> item = new ArrayList<>();
			double spawnX = 0, spawnY = 0;
			String line;
			int j = -1;
			while ((line = in.readLine()) !=  null) {
				j += 1;
				for (int i = 0; i < line.length(); i ++) {
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
//							item.add(new Collectable((double)i*Tile.DEFAULT_SIZE,(double)j*Tile.DEFAULT_SIZE));
							break;
						default:
							//Character not recognized -- print to sysout, but otherwise continue
							System.out.println("Level level" + id + "data contained character: " + line.charAt(i) + ", which is not implemented.");
					}
				}
			}
			
			this.activeLevel = new Level(tiles, enemies, spawnX, spawnY);
		} catch (IOException e) {
			System.out.println("Level loading failure.");
		}
		
	}

	public double[] getSpawnCoords() {
		// TODO Auto-generated method stub
		return activeLevel.getSpawnCoords();
	}

	public ArrayList<Enemy> getEnemies() {
		// TODO Auto-generated method stub
		return activeLevel.cloneEnemies();
	}
}
