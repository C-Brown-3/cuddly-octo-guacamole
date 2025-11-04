package polishedVersion;
import java.awt.event.*;

/**
 * Component (hopefully to be renamed GameComponent --Carson) controls the game logic. 
 * 
 * Component is responsible for draw calls to all drawable game elements, controlling the timer, and capturing key inputs.
 * 
 */

public class GameComponent implements KeyListener{

	private boolean leftPressed = false;
	private boolean rightPressed = false;
	private Hud hud;
	private CollectableModel collectableModel;
	private Player player;
	private EnemyModel enemyModel;
	private LevelModel levelModel;
	private Camera camera;
	 
	public GameComponent(Hud hud, CollectableModel collectableModel, Player player, EnemyModel enemyModel, LevelModel levelModel, Camera camera) {
		 this.hud = hud;
		 this.collectableModel = collectableModel;
		 this.player = player;
		 this.enemyModel = enemyModel;
		 this.levelModel = levelModel;
		 this.camera = camera;
	}
		
	//keyboard controls
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) leftPressed = true;
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) rightPressed = true;
		if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP) player.jump();
	}

	   
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
	    if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) leftPressed = false;
	    if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) rightPressed = false;
	}

	public void keyTyped(KeyEvent e) {}
	
	/**
	 * All game logic which happens once per frame should be put in tick(),
	 * which is called by GamePanel's tick function ~60 times per second
	 */
	public void tick() {
		// TODO Auto-generated method stub
		if (levelModel.getLevelID() == -1) {
			System.out.println("Hell on earth is here.");
			loadLevel(1);
		}
		camera.tick();
		player.tick();
	}
	
	public void loadLevel(int id) {
		this.levelModel.loadLevel(1);
		player.setCoords(levelModel.getSpawnCoords());
	}
	

}
