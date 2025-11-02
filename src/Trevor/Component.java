package Trevor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.Timer;

import Evie.Platform;




public class Component extends JComponent{
	Enemy test=new Enemy();
//	Collectable yarn = new Collectable();
	Level level = new Level();
	
	Timer timer;  

	public Component() {
		
		timer = new Timer(30, e -> {
			test.moveToEdge(level.getTiles());
			test.update(level.getTiles());
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
		level.draw(g2d);
//		test.draw(g2d);
//		yarn.draw(g2d);
		
	}
	
	

	

}
