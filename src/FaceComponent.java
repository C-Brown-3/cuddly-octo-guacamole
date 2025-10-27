import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class FaceComponent extends JComponent {

	@Override
	protected void paintComponent(Graphics graphics) {
		Graphics2D g2 = (Graphics2D) graphics;
		Face face = new Face(50,50, 50, 0.0, Color.red, Color.blue, Color.black, Color.green);
		face.drawOn(g2);
		
		Face left = new Face(100, 200, 100, 0.0, Color.YELLOW, Color.BLACK,Color.RED,Color.BLUE);
		left.drawOn(g2);
		Face center = new Face(250, 200, 50, 0.0, Color.black, Color.red, Color.blue, Color.blue);
		center.drawOn(g2);
		Face right = new Face(400, 200, 100, 0.0, Color.black, Color.green, Color.yellow, Color.red);
		right.drawOn(g2);
		Face far = new Face(600, 200, 50, 0.0, Color.black, Color.blue, Color.green, Color.yellow);
		far.drawOn(g2);
		
		for (int i =0; i < 7; i++) {
			Face rotate = new Face();
			rotate.translate(100, 375);
			rotate.translate(i * 80, i * 20);
			rotate.rotate(i*Math.PI/6);
			rotate.drawOn(g2);
		}
	}
	
}
