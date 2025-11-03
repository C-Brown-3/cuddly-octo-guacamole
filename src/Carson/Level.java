package Carson;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Class: Level
 * @author Trevor Goad
 * <br>Purpose: Used to create the set level design
 */
public class Level {
	private ArrayList<Tile> tiles;
	private ArrayList<Enemy> enemies;
	private double spawnX;
	private double spawnY;
	//private Tile stair;
	private int id;//int to determine what level we are on
	/**
	 * initializes the base level
	 * @param spawnY2 
	 * @param spawnX2 
	 * @param enemies 
	 * @param tiles2 
	 */
	public Level(ArrayList<Tile> tiles, ArrayList<Enemy> enemies, double spawnX, double spawnY) {
		this.tiles = tiles;
		this.enemies = enemies;
		this.spawnX = spawnX;
		this.spawnY = spawnY;
	}
	
	/**
	 * Draws the physical aspects of the level, but not entities or other components in the level
	 * Those are handled by their respective TopLevelClass implementations
	 * @param g2
	 */
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		if (!tiles.isEmpty()) for (Tile tile : tiles) {
			tile.draw(g2);
		}
	}
	
	/**
	 * Gets the tiles from the level
	 * @return ArrayList<Tile> filled with all tiles.
	 */
	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	
	/**
	 * This function creates a deep copy of the ArrayList containing the initial enemies of a level.
	 * 	I do this assuming that keeping the original level data without having to reread a txt file is helpful --Carson
	 * @return
	 */
	public ArrayList<Enemy> cloneEnemies() {
		ArrayList<Enemy> clonedEnemies = new ArrayList<>();
		for (Enemy enemy : enemies) {
			clonedEnemies.add(new Enemy(enemy.x, enemy.y));
		}
		return enemies;
	}
	
	public double[] getSpawnCoords() {
		double[] coords = {spawnX, spawnY};
		return coords;
	}

	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}
}