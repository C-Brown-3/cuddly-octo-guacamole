package Carson;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.*;

import Evie.Platform;




public class Component extends JComponent implements KeyListener{
	Enemy test=new Enemy();
	Collectable yarn = new Collectable();
	Tile tile=new Tile( 100, 200, 200, 50);
	Player player = new Player(50, 400); 
	Timer timer;  

	 private boolean leftPressed = false;
	 private boolean rightPressed = false;
	 
	public Component() {
		 setFocusable(true);
	     addKeyListener(this);
		timer = new Timer(30, e -> {
			test.moveToEdge(tile);
			test.update(tile);
			
			 player.update(java.util.List.of(tile));
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
		
		test.draw(g2d);
		tile.draw(g2d);
		yarn.draw(g2d);
		player.draw(g2d); 
		
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
	

	

}
