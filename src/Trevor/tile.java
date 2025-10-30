package Trevor;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class tile {
	private int x;
	private int y;
	private int width;
	private int height;
	public tile(int x,int y,int  width,int height) {
		this.x=x; //X position of the tile
		this.y=y; //y position of the tile
		this.width=width;//width of the tile
		this.height=height;//Height of the tile
	}
	public void draw(Graphics2D g2) {
		Rectangle2D.Double tile = new Rectangle2D.Double(x, y, width, height); 
		g2.draw(tile);//draws the tile on the screen
	}
}
