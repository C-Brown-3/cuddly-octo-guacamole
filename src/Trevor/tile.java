package Trevor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class tile {
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	public tile(int x,int y,int  width,int height, Color color) {
		this.x=x; //X position of the tile
		this.y=y; //y position of the tile
		this.width=width;//width of the tile
		this.height=height;//Height of the tile
		this.color = color;//sets a color for the tile
	}
	public void draw(Graphics2D g2) {
		Rectangle2D.Double tile = new Rectangle2D.Double(x, y, width, height); 
		g2.setColor(color);
        g2.fill(tile);//Fills the tile with a color to make it easier to see;
		g2.draw(tile);//draws the tile on the screen
	}
}
