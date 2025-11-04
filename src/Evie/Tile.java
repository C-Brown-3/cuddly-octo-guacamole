package Evie;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
/**
 * Class: Tile
 * @author Trevor Goad 
 * <br>Purpose: Used to create tiles for the level class to use
 */
public class Tile {
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	/**
	 * Tiles is the blocks used by level to create the stage
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Tile(int x,int y,int  width,int height) {
		this.x=x; //X position of the tile
		this.y=y; //y position of the tile
		this.width=width;//width of the tile
		this.height=height;//Height of the tile
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
	 public Rectangle getBounds() {
	        return new Rectangle(x, y, width, height);
	    }
	 /**
	  * draws the tile with given params on the screen
	  * @param g2
	  */
	public void draw(Graphics2D g2) {
		Rectangle2D.Double tile = new Rectangle2D.Double(x, y, width, height); 
		g2.setColor(color); //Sets the color
        g2.fill(tile);//Fills the tile with a color to make it easier to see;
		g2.draw(tile);//draws the tile on the screen
	}
	/**
	 * gets the top of the tile and returns it
	 */
    public int getTop() {
        return y;
    }
}