import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.Timer;


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
		this.drawSquare(g2d);
	}
	
	private void drawSquare(Graphics2D g2d) {
		Rectangle rect=new Rectangle(10,10,10,10);
		g2d.draw(rect);
	}

	

}
