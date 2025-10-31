package Trevor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.Timer;

import Evie.Player;
import Trevor.level;


public class Component extends JComponent{
	
	Timer timer;  

	public Component() {
		
		timer = new Timer(30, e -> {
	          
	          repaint();
	      });
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		new level(g2d);
	}
	
	

}
