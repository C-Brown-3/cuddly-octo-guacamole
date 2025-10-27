import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Face {
	private Color backColor;
	private Color eyeColor;
	private Color mouthColor;
	private Color borderColor;
	
	private double x;
	private double y;
	private double faceRadius;
	private double rotation;
	private double eyeXCenter;
	private double eyeYCenter;
	private double eyeRadius;
	private double mouthRadius;
	
	
	public Face() {
		this.backColor = Color.YELLOW;
		this.eyeColor = Color.BLACK;
		this.mouthColor = Color.BLACK;
		this.borderColor = Color.BLACK;
		
		this.x = 0;
		this.y = 0;
		this.faceRadius = 25;
		this.rotation = 0;
		this.eyeXCenter = (Math.cos(Math.PI*0.25)*faceRadius*0.5);
		this.eyeYCenter = -this.eyeXCenter; //Math goes the same way
		this.eyeRadius = (0.28 * this.faceRadius);
		this.mouthRadius = 0.7*this.faceRadius;
	}
	
	public Face(int x, int y, int radius, double rotation, Color backColor, Color eyeColor, Color mouthColor, Color borderColor) {
		this.backColor = backColor;
		this.eyeColor = eyeColor;
		this.mouthColor = mouthColor;
		this.borderColor = borderColor;
		
		this.x = x;
		this.y = y;
		this.faceRadius = radius;
		this.rotation = rotation;
		this.eyeRadius = (0.28 * this.faceRadius);
		this.eyeXCenter = (Math.cos(Math.PI*0.25)*faceRadius*0.5);
		this.eyeYCenter = -this.eyeXCenter; //Math goes the same way
		this.mouthRadius = 0.7*this.faceRadius;
		
	}
	
	public void drawOn(Graphics2D g2) {
		g2.translate(x, y);
		g2.rotate(rotation);
		Ellipse2D.Double head = new Ellipse2D.Double(-this.faceRadius, -this.faceRadius, this.faceRadius*2,this.faceRadius*2);
		Ellipse2D.Double eyeRight = new Ellipse2D.Double(this.eyeXCenter - this.eyeRadius, this.eyeYCenter - this.eyeRadius, this.eyeRadius*2, this.eyeRadius*2);
		Ellipse2D.Double eyeLeft = new Ellipse2D.Double(-this.eyeXCenter - this.eyeRadius, this.eyeYCenter - this.eyeRadius, this.eyeRadius*2, this.eyeRadius*2);
		Arc2D.Double mouth = new Arc2D.Double(new Rectangle2D.Double(-this.mouthRadius,-this.mouthRadius,2*this.mouthRadius,2*this.mouthRadius), -15, -150, Arc2D.CHORD);
		g2.setColor(backColor);
		g2.fill(head);
		Stroke original = g2.getStroke();
		Stroke thick = new BasicStroke(3);
		g2.setStroke(thick);
		g2.setColor(borderColor);
		g2.draw(head);
		g2.setColor(eyeColor);
		g2.fill(eyeRight);
		g2.fill(eyeLeft);
		g2.setStroke(original);
		g2.setColor(mouthColor);
		g2.draw(mouth);
		
		
		
		g2.rotate(-rotation);
		g2.translate(-x, -y);
	}
	
	public void translate(double x, double y) {
		this.x = this.x + x;
		this.y = this.y + y;
	}
	
	public void rotate(double rad) {
		this.rotation = this.rotation + rad;
	}
}
