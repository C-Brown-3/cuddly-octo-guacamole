package Carson;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * EnemyModel is responsible for controlling all instances of the Enemy class and unifying calls to Enemy behavior
 */
public class EnemyModel {
	private ArrayList<Enemy> enemyList;
	private LevelModel levelModel;
	
	public EnemyModel(LevelModel levelModel) {
		// Possibly nothing to do for the initial enemy list.
		this.levelModel = levelModel;
	}
	
	/**
	 * setEnemyList should be called when loading a new level to update the active enemies
	 * 
	 * @param newList - An ArrayList<Enemy> filled with the next level's enemies
	 */
	public void setEnemyList(ArrayList<Enemy> newList) {
		enemyList = newList;
		if (!enemyList.isEmpty()) for (Enemy enemy: enemyList) {
			enemy.setLevelModel(levelModel);
		}
	}
	
	/**
	 * EnemyModel.tick() calls all functions related to instances of Enemy which need to happen
	 * each frame of the game. Note that drawing is taken care of elsewhere by GamePanel
	 */
	public void tick() {
		if (!enemyList.isEmpty()) for (Enemy enemy: enemyList) {
			enemy.tick();
		}
	}
	
	public void draw(Graphics2D g2) {
		if (enemyList == null) {
			return;
		}
		if (!enemyList.isEmpty()) for (Enemy enemy: enemyList) {
			enemy.draw(g2);
		}
	}

	public ArrayList<Enemy> getEnemies() {
		// TODO Auto-generated method stub
		return enemyList;
	}

}
