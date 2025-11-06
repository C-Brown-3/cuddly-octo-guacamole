package Trevor;
import java.awt.event.*;
import java.util.HashMap;
/**
 * Component (hopefully to be renamed GameComponent --Carson) controls the game logic. 
 * 
 * Component is responsible for draw calls to all drawable game elements, controlling the timer, and capturing key inputs.
 * 
 */

public class GameComponent implements KeyListener{


	private Hud hud;
	private CollectableModel collectableModel;
	private Player player;
	private EnemyModel enemyModel;
	private LevelModel levelModel;
	private Camera camera;
	private HashMap<String,Boolean> keys = new HashMap<>();
	private int id =1;
	
	public GameComponent(Hud hud, CollectableModel collectableModel, Player player, EnemyModel enemyModel, LevelModel levelModel, Camera camera) {
		 this.hud = hud;
		 this.collectableModel = collectableModel;
		 this.player = player;
		 this.enemyModel = enemyModel;
		 this.levelModel = levelModel;
		 this.camera = camera;
		 this.keys.put("rightArrowPressed",false);
		 this.keys.put("leftArrowPressed",false);		
		 this.player.hash = this.keys;
	}
		
	//keyboard controls
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) keys.put("leftArrowPressed", true);
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) keys.put("rightArrowPressed", true);
		if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP) player.jump();
		if (key == KeyEvent.VK_Q) id--;
		if (key == KeyEvent.VK_E) id++;	
	}

	   
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
	    if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) keys.put("leftArrowPressed", false);
	    if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) keys.put("rightArrowPressed", false);
	}

	public void keyTyped(KeyEvent e) {}
	
	/**
	 * All game logic which happens once per frame should be put in tick(),
	 * which is called by GamePanel's tick function ~60 times per second
	 */
	public void incrementLevel() {
		id++;
	}
	public void tick() {
		// TODO Auto-generated method stub
		if (levelModel.getLevelID() == -1||id == 0) {
			loadLevel(1);
		}
		if(levelModel.getLevelID() != id) {
			loadLevel(id);
		}
		
		player.tick();
		camera.tick();
	}
	
	public void loadLevel(int id) {
		this.levelModel.loadLevel(1);
		player.setCoords(levelModel.getSpawnCoords());
		enemyModel.setEnemyList(levelModel.getEnemies());
	}
	

}
