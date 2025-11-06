package Carson;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
/**
 * Class: Tile
 * @author Trevor Goad
 * <br>Purpose: Used to create tiles for the level class to use
 */
public class Tile extends Drawable{
	public static final int DEFAULT_SIZE = 64;
	private int width = 64;
	private int height = 64;
	private Color color;
	
	/**
	 * Tile Constructor using only Position Elements
	 * --Carson
	 */
	public Tile(double x, double y) {
		super();
		this.x = x;
		this.y = y;
		this.color = Color.black;
	}
	/**
	 * Tiles is the blocks used by level to create the stage
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Tile(double x, double y, int width, int height) {
		this.x = x; //X position of the tile
		this.y = y; //y position of the tile
		this.width = width;//width of the tile
		this.height = height;//Height of the tile
	}
	/**
	 * creates a tile with set color instead of a base color
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param color
	 */
	public Tile(int x,int y,int  width,int height, Color color) {
		this.x=x; //X position of the tile
		this.y=y; //y position of the tile
		this.width=width;//width of the tile
		this.height=height;//Height of the tile
		this.color = color;//sets a color for the tile
	}
	/**
	 * gets the bounds of the tiles
	 * @return rectangle with provided bounds
	 */
	 public Rectangle2D.Double getBounds() {
	        return new Rectangle2D.Double(x, y, width, height);
	    }
	 /**
	  * draws the tile with given params on the screen
	  * @param g2
	  */
	public void draw(Graphics2D g2) {
//		System.out.println(this.x + ", " + this.y + "||" + this.drawX + ", " + this.drawY);
		Rectangle2D.Double tile = new Rectangle2D.Double(this.drawX, this.drawY, width, height); 
		g2.setColor(color); //Sets the color
        g2.fill(tile);//Fills the tile with a color to make it easier to see;
		g2.draw(tile);//draws the tile on the screen
	}
	/**
	 * gets the top of the tile and returns it
	 */
    public int getTop() {
        return (int) y;
    }
}