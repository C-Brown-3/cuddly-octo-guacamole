package polishedVersion;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * Entity is an abstract class for all elements in the game which are going to move dynamically
 * --Examples
 * Player
 * Enemy
 * Collectable
 * 
 * Entity provides many of the movement, gravity, and collision detection functions that are required by the game.
 */
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
    
    /**
     * Entity constructor with initial coordinates and a reference to the levelModel to pull tile data from
     * @param x -- double representing the initial x location of the entity, will be changed as entity moves
     * @param y -- double representing the initial y location of the entity, will be changed as entity moves
     * @param levelModel -- the object in charge of the level
     */
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
	protected void applyFriction() {
		if (Math.abs(dx) < minDX) {
			dx = 0;
		}
	}
	
	/**
	 * A generic update to an Entity's coordinates based on the velocity, acceleration, gravity, and collision
	 * that the entity has stored. 
	 */
	public void move() {
		x += dx;
		dx += d2x;
		y += dy;
		dy += d2y + gravity;
		
		applyFriction();
		updateCollision();
	}
	
	/**
	 * Helper function to buffer an image with the exception cases already handled by returning null as the output
	 * @param filePath -- string file path to the resource location
	 * @return returns a BufferedImage if the file is located, or null if not.
	 */
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
	 * Creates and returns a rectangle representation of an entity's boundaries
	 * @return Rectangle2D.Double of size width, height and location x,y
	 */
	public Rectangle2D.Double getBounds(){
		return new Rectangle2D.Double(x,y,width,height);
	}
	
	/**
	 * This function is used to set the reference to the levelModel
	 * @param levelModel
	 */
	public void setLevelModel(LevelModel levelModel) {
		this.levelModel = levelModel;
	}
	
	 /**
     * Updates the player's position, gravity, and collision detection
     * with the provided list of tiles.
     *
     * @param tiles a list of  objects that represent platforms or ground
     */
    protected void updateCollision() {
    	
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
     * @param offsetX -- This value is used to change where collision tests assume the initial entity is for the x direction
     * @param offsetY -- This value is the same as offsetX for the Y direction
     * @param partialDX -- This value is the change in position at the end of a move for the x direction
     * @param partialDY -- This value is the same as partialDX but for the y direction
     * @param affectPosition -- If this value is true, the original x and y coordinates are updated to open space. Otherwise they are unaffected.
     */
    protected boolean[] testCollisionWithOffset(double offsetX, double offsetY, double partialDX, double partialDY,  boolean affectPosition) {
    	//Set up before and after movement
    	double initialX = this.x + offsetX;
    	double initialY = this.y + offsetY;
    	double finalX = initialX + partialDX;
    	double finalY = initialY + partialDY;
    	
    	boolean xCollision = false, yCollision = false;
    	
    	for (Tile tile : this.levelModel.getTiles()) {
    		//Set up tile boundaries
    		Rectangle2D.Double tileBounds = tile.getBounds();
    		double tileLeftX = tileBounds.x;
    		double tileTopY = tileBounds.y;
    		double tileRightX = tileBounds.x + tileBounds.width;
    		double tileBottomY = tileBounds.y + tileBounds.height;
    		
    		//Check x collision first
    		//Starts with seeing if an X collision is possible
    		//ie: is the top of the entity inbetween the tile's top and bottom
    		// or is the bottom of the entity inbetween the tile's top and bottom
    		// the idea for this method comes from what I (Carson) did in 120.
    		if((initialY > tileTopY & initialY < tileBottomY) || (initialY + height > tileTopY & initialY + height < tileBottomY) || (initialY == tileTopY & initialY + height == tileBottomY)){
    			if ((finalX > tileLeftX & finalX < tileRightX) || (finalX + width > tileLeftX & finalX + width < tileRightX)) {
    				xCollision = true;
    				if(affectPosition) {
    					//Check the direction we were going, move to fix collision going the opposite way
    					if(dx >= 0) {
    						//Entity is moving to the right, so it needs to be pushed left
    						x = tileLeftX - width;
    					} else {
    						//Entity is moving to the left, so it needs to be pushed right
    						x = tileRightX ;
    					}
    				}
    			}
    		}
    		
    		//Now Check y collision
    		if((initialX > tileLeftX & initialX < tileRightX) || (initialX + width > tileLeftX & initialX + width < tileRightX) || (initialX == tileLeftX & initialX + width == tileRightX)){
    			if ((finalY > tileTopY & finalY < tileBottomY) || (finalY + height > tileTopY & finalY + height < tileBottomY)) {
    				yCollision = true;
    				if(affectPosition) {
    					//Check the direction we were going, move to fix collision going the opposite way
    					if(dy >= 0) {
    						//Entity is moving down, so it needs to be pushed up
    						y = tileTopY - height;
    						jumping = false;
    					} else {
    						//Entity is moving up, so it needs to be pushed down
    						y = tileBottomY;
    					}
    				}
    			}
    		}
    		
    		
    	}
    	
    	//If no collisions occur, set the x and y to the new x and y values
    	if(affectPosition) {
    		if(!xCollision) {
    			this.x = finalX;
    		}
    		if(!yCollision) {
    			this.y = finalY;
    		}
    	}
    	boolean[] returnArray = {xCollision, yCollision};
		return returnArray;
	}
}