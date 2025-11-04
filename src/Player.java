

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
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
public class Player extends Entity {
    
   
   
    private final int gravity = 1;
    private final int jumpPower = -20;
    private BufferedImage sprite;

    private boolean facingRight = true;
    
    
    /**
     * Constructs a new Player at the specified starting coordinates.
     *
     * @param x the initial x-coordinate of the player
     * @param y the initial y-coordinate of the player
     */
    public Player(int x, int y) {
    	super(x,y);
        
        width = 64;
        height = 64;

        try {
            sprite = ImageIO.read(getClass().getResource("cat.png"));
        } catch (IllegalArgumentException e) {
            sprite = null;
        } catch (IOException e) {
        	sprite = null;
        }
    }

    /**
     * Updates the player's position, gravity, and collision detection
     * with the provided list of tiles.
     *
     * @param tiles a list of  objects that represent platforms or ground
     */
    public void gravity() {
    	prevY = y;
        prevX = x;
        y += velocityY;
        velocityY += gravity;
        
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
    public void moveLeft() {
        x -= speed;
        facingRight = false; //turn left
    }

    public void moveRight() {
        x += speed;
        facingRight = true; //turn right
    }
    
    /*
     * Collisions!!!
     */
    public void collide(List<Tile> tiles, List<Enemy> enemies, List<Collectable> collectables, HUD hud) {
        //int prevX = x;
        //int prevY = y;
        
        // tile collisions are happening in Entity
        Rectangle playerBounds = new Rectangle (x ,y, width, height);
        
        		
        // Enemy collisions
        for (Enemy enemy : enemies) {
            if (playerBounds.intersects(enemy.getBounds())) {
                hud.decrementLives();
            }
        }
        // Collectable collisions
    	 for (int i = collectables.size() - 1; i >= 0; i--) {
    	        Collectable item = collectables.get(i);
    	        if (playerBounds.intersects(item.getBounds())) {
    	            hud.incrementScore(10);
    	            collectables.remove(i); 
    	            System.out.println( hud.getScore());
    	        }
    	    }
        }
       
            
    

    /**
     * Draws the player sprite on the screen, flipping it horizontally if 
     * facing left. If the sprite is not loaded, a blue rectangle is drawn instead.
     *
     * @param g the graphics context to draw the player
     */
    public void draw(Graphics g) {
        if (sprite != null) {
            Graphics2D g2d = (Graphics2D) g;

            if (facingRight) {
                g2d.drawImage(sprite, x, y, width, height, null);
            } else {
               
                g2d.drawImage(sprite, x + width, y, -width, height, null);
            }
        } else {
            g.setColor(Color.BLUE);
            g.fillRect(x, y, width, height);
        }
    }
}
