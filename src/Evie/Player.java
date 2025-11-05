package Evie;


import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Iterator;
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
    
    private long lastHitTime = 0;
    private long invulnerabilityDuration = 2000; // 2 seconds grace period after each enemy hit 
    private boolean isInvulnerable = false;
    private HUD hud;
    
    
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
    public void collide(List<Tile> tiles, List<Enemy> enemies, List<Collectable> collectables,boolean downPressed, HUD hud) {
        //int prevX = x;
        //int prevY = y;
        
        // tile collisions are happening in Entity
        //Rectangle playerBounds = new Rectangle (x ,y, width, height);
    	  Rectangle playerBounds = getBounds();
          long currentTime = System.currentTimeMillis();
        		
        // Enemy collisions- each enemy hit looses player a life, you have a 2 second grace period before you can be hit again
          for (Enemy enemy : enemies) {
              if (playerBounds.intersects(enemy.getBounds())) {
                  if (currentTime - lastHitTime > invulnerabilityDuration) {
                      hud.decrementLives();
                      lastHitTime = currentTime;
                      isInvulnerable = true;

                      

                      System.out.println("Player hit by enemy.");
                  }
              
          }
          }
        // Collectable collisions- Each yarn piece is only worth 10 points, cannot keep standing on yarn for more points
        Iterator<Collectable> iter = collectables.iterator();
        while (iter.hasNext()) {
        	 Collectable item = iter.next();
        	 if (!item.isCollected() && playerBounds.intersects(item.getBounds()) && downPressed) {
                 if (hud != null) 
                hud.incrementScore(10);
                 item.setCollected(true);
                 iter.remove();
                 System.out.println("Collected yarn!");
             }
         }

         if (currentTime - lastHitTime > invulnerabilityDuration) {
             isInvulnerable = false;
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
