package Nate;


import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * The Player class represents the main controllable character in the game.
 * 
 * The player can move left or right, jump, and is affected by gravity. It also 
 * interacts with objects for collision detection and supports
 * sprite flipping when changing directions.
 * 
 * @author Evie Hipwood
 * See CSSE220 Final Project Document for Resources Used
 */
public class Player extends Entity implements TopLevelClass {
	private static final String IMAGE_PATH = "cat.png";
    private final int gravity = 1;
    private final int jumpPower = -20;
    private BufferedImage sprite;
    public HashMap<String, Boolean> hash;

    private boolean facingRight = true;
    
    
    /**
     * Constructs a new Player at the specified starting coordinates.
     *
     * @param x the initial x-coordinate of the player
     * @param y the initial y-coordinate of the player
     */
    public Player(int x, int y, LevelModel levelModel, HashMap<String, Boolean> hash) {
    	super(0, 0, levelModel);
        this.x = x;
        this.y = y;
        width = 64;
        height = 64;
        this.hash = hash;

        sprite = bufferImage(IMAGE_PATH);
    }

    /**
     * Updates the player's position, gravity, and collision detection
     * with the provided list of tiles.
     *
     * @param tiles a list of  objects that represent platforms or ground
     */
    public void update(List<Tile> tiles) {
        double prevY = drawY;
        y += dy;
        dy += gravity;

        Rectangle2D.Double playerBounds = new Rectangle2D.Double(x, y, width, height);

        // Check collisions with all tiles
        for (Tile tile : tiles) {
            Rectangle2D.Double tileBounds = tile.getBounds();

            // only check if moving downward
            if (dy >= 0 && playerBounds.intersects(tileBounds)) {
                int playerBottomPrev = (int) prevY + (int) height;
                int tileTop = tile.getTop();

                if (playerBottomPrev <= tileTop) {
                    y = tileTop - height;
                    jumping = false;
                    dy = 0;
                }
            }
        }

        // ground collision
//        if (y + height >= groundY) {
//            y = groundY - height;
//            jumping = false;
//            velocityY = 0;
//        }
    }
    /**
     * Makes the player jump if they are currently not already jumping.
     */
    public void jump() {
        if (!jumping) {
            jumping = true;
            dy = jumpPower;
        }
    }
    
    @Override
    public void move() {
    	
    	
    	//Apply Acceleration in the Direction pressed
    	if(hash.get("leftArrowPressed")) {
            d2x -= 0.5;
            facingRight=false; //turn right	
    	} else if(hash.get("rightArrowPressed")) {
            d2x += 0.5;
            facingRight = true; //turn right	
    	} else {
    		d2x = 0;
    	}
    	
    	int velocityDirection = (int) (dx/Math.abs(dx));
    	int accelerationDirection = (int) (d2x/Math.abs(d2x));
    	
    	//Sets a maximum acceleration for the player
    	if (Math.abs(d2x) >= 1) {
    		d2x = accelerationDirection*1;
    	}
    	
    	//Improves quality of turning around
    	if(velocityDirection != accelerationDirection) {
    		dx = dx/1.414 - 0.5*velocityDirection;
    	}
    	
//    	System.out.println(dx + "||" + d2x + "[]" + velocityDirection + "||" + accelerationDirection);
    	super.move();
    }


    /**
     * Draws the player sprite on the screen, flipping it horizontally if 
     * facing left. If the sprite is not loaded, a blue rectangle is drawn instead.
     *
     * @param g the graphics context to draw the player
     */
    public void draw(Graphics2D g2) {
    	g2.translate(drawX, drawY);
        if (sprite != null) {
            if (facingRight) {
                g2.drawImage(sprite, 0, 0, (int) width, (int) height, null);
            } else {
               
                g2.drawImage(sprite, (int) width, 0, (int) -width, (int) height, null);
            }
        } else {
            g2.setColor(Color.YELLOW);
//            System.out.println(drawX + ", " + drawY);
            g2.fillRect(0, 0, (int) width, (int) height);
        }
        g2.translate(-drawX, -drawY);
    }

	public void setCoords(double[] spawnCoords) {
		// TODO Auto-generated method stub
		this.x = spawnCoords[0];
		this.y = spawnCoords[1];
		
	}

	
	/**
	 * Every thing the player should do once per frame goes in this function
	 */
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		move();
		//System.out.println(this.x + "||" + this.y);

	}
}
