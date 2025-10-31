import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.Timer;

import Evie.Platform;
import Nate.Enemy;


public class Component extends JComponent{
	Enemy test=new Enemy();
	Platform plat=new Platform( 100, 100, 200, 50);
	
	Timer timer;  

	public Component() {
		
		timer = new Timer(30, e -> {
			test.update(plat);
			test.move();
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
		new Level(g2d);
		test.draw(g2d);
	}
	
	

	

}
