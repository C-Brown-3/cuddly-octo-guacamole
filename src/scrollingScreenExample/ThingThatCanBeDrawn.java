package scrollingScreenExample;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class ThingThatCanBeDrawn extends Drawable {

	private double width;
	private double height;
	private Color color;

	public ThingThatCanBeDrawn(double width, double height, Color color, double x, double y) {
		super();
		this.width = width;
		this.height = height;
		this.color = color;
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics2D g2) {
		Rectangle2D.Double tile = new Rectangle2D.Double(drawX, drawY, width, height); 
		g2.setColor(color); //Sets the color
        g2.fill(tile);//Fills the tile with a color to make it easier to see;
		g2.draw(tile);//draws the tile on the screen
	}
}
