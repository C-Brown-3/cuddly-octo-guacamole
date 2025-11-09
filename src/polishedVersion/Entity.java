package polishedVersion;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Entity extends Drawable {
	double width;
	double height;
	double dx;
	double dy;
	double d2x;
	double d2y;
	protected double prevX, prevY;
    protected boolean jumping = false;
    protected final double gravity = 1;
    protected double minDX = 0.2;
    protected LevelModel levelModel;
    
	public Entity(double x, double y, LevelModel levelModel) {
		super();
		this.x = x;
		this.y = y;
		dx = 0;
		dy = 0;
		d2x = 0;
		d2y = 0;
		this.levelModel = levelModel;
	}
	
	/**
	 * Provides Horizontal Friction to slow down the entity by the friction value
	 * Entities which have constant velocities should probably have 0 for friction coefficients
	 */
	private void applyFriction() {
		if (Math.abs(dx) < minDX) {
			dx = 0;
		}
	}
	
	public void move() {
		x += dx;
		dx += d2x;
		y += dy;
		dy += d2y + gravity;
		
		applyFriction();
		updateCollision();
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
	
	public Rectangle2D.Double getBounds(){
		return new Rectangle2D.Double(x,y,width,height);
	}
	
	public void setLevelModel(LevelModel levelModel) {
		this.levelModel = levelModel;
	}
	 /**
     * Updates the player's position, gravity, and collision detection
     * with the provided list of tiles.
     *
     * @param tiles a list of  objects that represent platforms or ground
     */
    private void updateCollision() {
    	
    	for (Tile tile : this.levelModel.getTiles()) {
    	Rectangle2D.Double tileBounds = tile.getBounds();
        
        // updating entity 
        Rectangle2D.Double Bounds = new Rectangle2D.Double(x, y, width, height);

        // Check collisions with tile
            if (Bounds.intersects(tileBounds)) {
                Rectangle2D intersection = Bounds.createIntersection(tileBounds);

                // Determine smallest overlap direction
                if (intersection.getWidth() < intersection.getHeight()) {
                    // Horizontal collision
                    if (prevX < tileBounds.x) {
                        x -= intersection.getWidth(); // hit from left
                    } else {
                        x += intersection.getWidth(); // hit from right
                    }
                } else {
                    // Vertical collision
                    if (prevY + height <= tileBounds.y) {
                        // Landed on top of tile
                        y -= intersection.getHeight();
                        jumping = false;
                        dy = 0;
                    } else if (prevY >= tileBounds.y + tileBounds.height) {
                        // Hit head 
                        y += intersection.getHeight();
                        dy = 0;
                    }
                }
                // Update bounds after correction
               //Bounds = new Rectangle(x, y, width, height);    
            }
    	}
        // ground collision
//        if (y + height >= groundY) {
//            y = groundY - height;
//            jumping = false;
//            dy = 0;
//        }
    }
    
    /**
     * This function is here with the goal of letting an entity test if it would collide with a tile,
     * and provides the option of having the tested collision change the position of the entity.
     * @param offsetX
     * @param offsetY
     */
    private boolean[] testCollisionWithOffset(int offsetX, int offsetY, boolean affectPosition) {
    	
		return null;
	}
}