package Trevor;


import java.awt.*;
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
public class Player extends Entity implements TopLevelClass{
    private int velocityY = 0;
    private int speed = 5;
    private boolean jumping = false;
    private final int gravity = 1;
    private final int jumpPower = -20;
    private final int groundY = 744;
    private BufferedImage sprite;
    public HashMap<String, Boolean> hash;

    private boolean facingRight = true;
    
    
    /**
     * Constructs a new Player at the specified starting coordinates.
     *
     * @param x the initial x-coordinate of the player
     * @param y the initial y-coordinate of the player
     */
    public Player(int x, int y, HashMap<String, Boolean> hash) {
    	super(0, 0);
        this.x = x;
        this.y = y;
        width = 64;
        height = 64;
        this.hash = hash;

        try {
            sprite = ImageIO.read(getClass().getResource("src/cat.png"));
        } catch (IllegalArgumentException e) {
            sprite = null;
        } catch (IOException e) {
        	sprite = null;
        }
    }

    public Player() {
		// TODO Auto-generated constructor stub
    	super(0,0);
	}

	/**
     * Updates the player's position, gravity, and collision detection
     * with the provided list of tiles.
     *
     * @param tiles a list of  objects that represent platforms or ground
     */
    public void update(List<Tile> tiles) {
        int prevY = drawY;
        y += velocityY;
        velocityY += gravity;

        Rectangle playerBounds = new Rectangle(drawX, drawY, (int) width, (int) height);

        // Check collisions with all tiles
        for (Tile tile : tiles) {
            Rectangle tileBounds = tile.getBounds();

            // only check if moving downward
            if (velocityY >= 0 && playerBounds.intersects(tileBounds)) {
                int playerBottomPrev = prevY + (int) height;
                int tileTop = tile.getTop();

                if (playerBottomPrev <= tileTop) {
                    y = tileTop - height;
                    jumping = false;
                    velocityY = 0;
                }
            }
        }

        // ground collision
        if (y + height >= groundY) {
            y = groundY - height;
            jumping = false;
            velocityY = 0;
        }
    }
    /**
     * Makes the player jump if they are currently not already jumping.
     */
    public void jump() {
        if (!jumping) {
            jumping = true;
            velocityY = jumpPower;
        }
    }
    public void movePlayer() {
    	if(hash.get("leftArrowPressed")) {
            x -= 5;
            facingRight=false; //turn right	
    	}
    	if(hash.get("rightArrowPressed")) {
            x += 5;
            facingRight = true; //turn right	
    	}
    }


    /**
     * Draws the player sprite on the screen, flipping it horizontally if 
     * facing left. If the sprite is not loaded, a blue rectangle is drawn instead.
     *
     * @param g the graphics context to draw the player
     */
    public void draw(Graphics2D g2) {
  
        if (sprite != null) {
            if (facingRight) {
                g2.drawImage(sprite, drawX, drawY, (int) width, (int) height, null);
            } else {
               
                g2.drawImage(sprite, (int) (drawX + width), drawY, (int) -width, (int) height, null);
            }
        } else {
            g2.setColor(Color.YELLOW);
//            System.out.println(drawX + ", " + drawY);
            g2.fillRect(drawX, drawY, (int) width, (int) height);
        }
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
		movePlayer();
		System.out.println(this.x + "||" + this.y);

	}
}
