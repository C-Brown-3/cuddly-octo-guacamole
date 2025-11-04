import javax.swing.JComponent;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.*;



/**
 * Component (hopefully to be renamed GameComponent --Carson) controls the game logic. 
 * 
 * Component is responsible for draw calls to all drawable game elements, controlling the timer, and capturing key inputs.
 * 
 */

public class Component extends JComponent implements KeyListener{
	Enemy one=new Enemy(500, 200, 64, 64);
	Enemy two=new Enemy(150, 450, 64, 64);
	Enemy three=new Enemy(750, 450, 64, 64);
	Collectable yarn = new Collectable(500, 280, 64, 64);
	Player player = new Player(50, 600); 
	Level level=new Level();
	HUD hud = new HUD();
	Timer timer;  

	 private boolean leftPressed = false;
	 private boolean rightPressed = false;
	 
	public Component() {
		 setFocusable(true);
	     addKeyListener(this);
		timer = new Timer(30, e -> {
			
			
			
			
			
			// tiles for entity
			player.gravity();
			one.gravity();
			two.gravity();
			three.gravity();
			for (Tile tile : level.getTiles()) {
		            player.update(tile,1200);
		            one.moveToEdge(tile);
					two.moveToEdge(tile);
					three.moveToEdge(tile);
					one.update(tile, 1200);
					two.update(tile, 1200);
					three.update(tile, 1200);
			  }
			
		    
			if (leftPressed) player.moveLeft();
	        if (rightPressed) player.moveRight();
			repaint();
	      });
	}
	
	 public void start() { timer.start(); }     // NEW
	 public void stop()  { timer.stop(); }   
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		//new Level(g2d);
		
		one.draw(g2d);
		two.draw(g2d);
		three.draw(g2d);
		yarn.draw(g2d);
		player.draw(g2d); 
		level.levelDraw(g2d);
		hud.draw(g2d);
		
		
	}
	//keyboard controls
	 public void keyPressed(KeyEvent e) {
	        int key = e.getKeyCode();
	        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) leftPressed = true;
	        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) rightPressed = true;
	        if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP) { 
	        	player.jump();
	        }
	    }

	   
	    public void keyReleased(KeyEvent e) {
	        int key = e.getKeyCode();
	        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) leftPressed = false;
	        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) rightPressed = false;
	    }

	    public void keyTyped(KeyEvent e) {}
	

	

}
