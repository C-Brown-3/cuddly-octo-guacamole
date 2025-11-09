package polishedVersion;

import java.awt.Graphics2D;
import java.util.ArrayList;



public class CollectableModel implements TopLevelClass {

	private ArrayList<Collectable> collectableList;
	private LevelModel levelModel;
	
	public CollectableModel(LevelModel levelModel) {
		// Possibly nothing to do for the initial enemy list.
		this.levelModel = levelModel;
	}
	
	/**
	 * setCollectableList should be called when loading a new level to update the active collectables
	 * 
	 * @param newList - An ArrayList<Collectable> filled with the next level's collectables
	 */
	public void setCollectableList(ArrayList<Collectable> newList) {
		collectableList = newList;
		if (!collectableList.isEmpty()) for (Collectable collectable: collectableList) {
			collectable.setLevelModel(levelModel);
		}
	}
	
	/**
	 * collectableModel.tick() calls all functions related to instances of collectable which need to happen
	 * each frame of the game. Note that drawing is taken care of elsewhere by GamePanel
	 */
	public void tick() {
		if (!collectableList.isEmpty()) for (Collectable collectable: collectableList) {
			collectable.tick();
		}
	}
	
	public void draw(Graphics2D g2) {
		if (collectableList == null) {
			return;
		}
		if (!collectableList.isEmpty()) for (Collectable collectable: collectableList) {
			collectable.draw(g2);
		}
	}

	public ArrayList<Collectable> getCollectables() {
		// TODO Auto-generated method stub
		return collectableList;
	}
	public void remove(Collectable collectable) {
		// TODO Auto-generated method stub
		collectableList.remove(collectable);
	}

}
