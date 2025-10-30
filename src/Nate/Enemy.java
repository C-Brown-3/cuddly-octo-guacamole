package Nate;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

public class Enemy {
	private int x;
	private int y;
	private int height;
	private int widith;
	private Color color;
	
	public Enemy() {
		this.x=100;
		this.y=100;
		this.height=50;
		this.widith=50;
		this.color=Color.RED;
		
	}
	public Enemy(int x, int y) {
		
	}
	public void move() {
		this.x=this.x+10;
	}
	public void draw(Graphics2D g2) {
		Rectangle rect=new Rectangle(x,y,widith,height);
		g2.setColor(this.color);
		g2.fill(rect);
		g2.draw(rect);
		
	}
}
