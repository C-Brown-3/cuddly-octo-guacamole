package Nate;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

import Evie.Platform;
import Evie.Player;
import Trevor.tile;

public class Enemy {
	private int x;
	private int y;
	private int height;
	private int width;
	private Color color;
	private static int gravity=3;
	private int velocityX;
	private int velocityY;
	
	
	public Enemy() {
		this.x=100;
		this.y=100;
		this.height=50;
		this.width=50;
		this.color=Color.RED;
		this.velocityX=3;
		this.velocityY=0;
		
	}
	public Enemy(int x, int y, int height, int widith, Color color) {
		this.x=x;
		this.y=y;
		this.height=height;
		this.width=widith;
		this.color=color;
	}
	
	public void moveToEdge() {
		this.x=this.x+this.velocityX;
	}
	public void chase(Player player) {
		//if(player.getY()==this.y) {
		//	velocityX=velocityX*(player.getX()-this.x)/Math.abs(player.getX()-this.x);
		//}
	}
	public void update(tile tile) {
        int prevY = y; // track previous position
        y += velocityY;
        velocityY += gravity;

        Rectangle enemyBounds = new Rectangle(x, y, width, height);
        Rectangle platformBounds = tile.getBounds();

        // Check collision only while falling
        if (velocityY >= 0 && enemyBounds.intersects(platformBounds)) {
            int playerBottomPrev = prevY + height;
            int tileTop = tile.getTop();

            // Player was above platform last frame, now intersecting = landed
            if (playerBottomPrev <= tileTop) {
                y = tileTop - height;
                velocityY = 0;
            }
        }
     // Ground collision
        if (y + height >= 600) {
            y = 600 - height;
            velocityY = 0;
        }
    }
	public void draw(Graphics2D g2) {
		Rectangle rect=new Rectangle(x,y,width,height);
		g2.setColor(this.color);
		g2.fill(rect);
		g2.draw(rect);
		
	}
}
