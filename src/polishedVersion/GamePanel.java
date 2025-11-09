package polishedVersion;


import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


/**
 * The  GamePanel class represents the main drawing surface and game loop
 * for the platform. It handles rendering the Level and  Player.
 * 
 *  Keyboard input is currently handled by another component class, so the
 * KeyListener implementation is commented out for now.
 * 
 * This class uses a Swing  Timer to update and repaint the screen
 * approximately 60 times per second.
 * 
 * @author Evie Hipwood 
 * See CSSE220 Final Project Document to see resources used
 */
public class GamePanel extends JPanel implements ActionListener, ComponentListener{ //KeyListener is in Component now
    private Timer timer;
    private Hud hud;
    private CollectableModel collectableModel;
    private Player player;
    private EnemyModel enemyModel;
    private LevelModel levelModel;
    private Camera camera;
    private GameComponent gameComponent;
    
    //private boolean leftPressed, rightPressed;

    
    /**
     * Constructs a new  GamePanel, initializes the level and player,
     * and starts the update timer.
     */
    public GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);

        levelModel = new LevelModel();
        hud = new Hud();
        collectableModel = new CollectableModel(levelModel);
        
        player = new Player(0, 0, levelModel, null);
        enemyModel = new EnemyModel(levelModel);
        camera = new Camera(player, levelModel, 400, 400);
        gameComponent = new GameComponent(hud, collectableModel, player, enemyModel, levelModel, camera);
        
        timer = new Timer(16, e -> tick()); // 1/16 ms ~ 60 fps
        timer.start();

        addKeyListener(gameComponent);
        setFocusable(true);
        requestFocusInWindow();
    }

    /**
     * Tick should call all functions which need to happen once per frame
     * -All game logic should exist in GameComponent.tick(), while here is just draw logic
     */
    private void tick() {
		// TODO Auto-generated method stub
    	camera.setScreenWidth(getWidth());
		camera.setScreenHeight(getHeight());
    	
    	gameComponent.tick();
    	
    	updateDrawCoordinates();
		
		this.repaint();
	}

	private void updateDrawCoordinates() {
		// TODO Auto-generated method stub
		camera.calculateAndSetDrawXY(player);
		for (Tile tile: levelModel.getTiles()) {
			camera.calculateAndSetDrawXY(tile);
		}
		for(Enemy enemy: enemyModel.getEnemies()) {
			camera.calculateAndSetDrawXY(enemy);
		}
		for(Collectable collectable: collectableModel.getCollectables()) {
			camera.calculateAndSetDrawXY(collectable);
		}
		
	}

	/**
     * Draws the current level and player on the panel.
     *
     * @param g the  Graphics context for rendering
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
        levelModel.draw(g2);
        enemyModel.draw(g2);
        collectableModel.draw(g2);
        hud.draw(g2);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		camera.setScreenWidth(getWidth());
		camera.setScreenHeight(getHeight());
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
