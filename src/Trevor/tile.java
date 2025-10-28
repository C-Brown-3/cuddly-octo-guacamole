package Trevor;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class tile {
	private int x;
	private int y;
	private int width;
	private int height;
	public tile(int x,int y,int  width,int height) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	public void draw(Graphics2D g2) {
		Rectangle2D.Double tile = new Rectangle2D.Double(x, y, width, height);
		g2.draw(tile);
	}
}
