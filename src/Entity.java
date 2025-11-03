

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public abstract class Entity extends Drawable {
	double dx;
	double dy;
	double d2x;
	double d2y;
	private int x, y;
	private int width = 64;
	private int height = 64;
	private int velocityY = 0;
	private int speed = 5;
    private boolean jumping = false;
    private final int gravity = 1;
    private final int groundY = 744;
    
	public Entity(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		dx = 0;
		dy = 0;
		d2x = 0;
		d2y = 0;
	}
	
	public void move() {
		x += dx;
		dx += d2x;
		y += dy;
		dy += d2y;
	}
	
	
	
	protected BufferedImage bufferImage(String filePath) {
		try {
			BufferedImage bufferedImage = ImageIO.read(Collectable.class.getResource(filePath));
			return bufferedImage;
		} catch (IOException e) {
			return null;
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	 /**
     * Updates the player's position, gravity, and collision detection
     * with the provided list of tiles.
     *
     * @param tiles a list of  objects that represent platforms or ground
     */
    public void update(List<Tile> tiles, int screenWidth) {
        int prevY = y;
        int prevX = x;
        y += velocityY;
        
        // updating entity 
        Rectangle Bounds = new Rectangle(x, y, width, height);

        // Check collisions with tile
            if (Bounds.intersects(tileBounds)) {
                Rectangle intersection = Bounds.intersection(tileBounds);

                // Determine smallest overlap direction
                if (intersection.width < intersection.height) {
                    // Horizontal collision
                    if (prevX < tile    Bounds.x) {
                        x -= intersection.width; // hit from left
                    } else {
                        x += intersection.width; // hit from right
                    }
                } else {
                    // Vertical collision
                    if (prevY + height <= tileBounds.y) {
                        // Landed on top of tile
                        y -= intersection.height;
                        jumping = false;
                        velocityY = 0;
                    } else if (prevY >= tileBounds.y + tileBounds.height) {
                        // Hit head 
                        y += intersection.height;
                        velocityY = 0;
                    }
                }
                // Update bounds after correction
               Bounds = new Rectangle(x, y, width, height);
            
        }
        

        // ground collision
        if (y + height >= groundY) {
            y = groundY - height;
            jumping = false;
            velocityY = 0;
        }
    }
}