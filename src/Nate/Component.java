package Nate;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;






public class Component extends JComponent {
	private HUD hud;
	 
	public Component() {
		 this.hud=new HUD();
	}
	
	 
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		//new Level(g2d);
		
		this.hud.draw(g2d);
		
		
	}
	
	

}
