package Carson;

import java.awt.Graphics2D;

/**
 * TopLevelClass ensures that all classes which GameComponent and GamePanel depend on have 
 * necessary features such as draw and tick functions.
 * 
 *  A few of these classes are EnemyModel, Player, and LevelModel
 */
public interface TopLevelClass {
	public void tick();
	public void draw(Graphics2D g2);
}
