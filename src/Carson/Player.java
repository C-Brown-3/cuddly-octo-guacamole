package Carson;


import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
public class Player extends Entity implements TopLevelClass {
	
    private final int gravity = 1;
    private final int jumpPower = -25;
    private BufferedImage sprite;
    public HashMap<String, Boolean> hash;

    private boolean facingRight = true;
    private boolean spriteLoaded=false;
    
    private long lastHitTime = 0;
    private long invulnerabilityDuration = 2000; // 2 seconds grace period after each enemy hit 
    private boolean isInvulnerable = false;
    
    
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

        
        try {
			sprite=ImageIO.read(Player.class.getResource("cat.png"));
			spriteLoaded=true;
		} catch (IOException e) {
			
			spriteLoaded = false;
		} catch (IllegalArgumentException e) {
			spriteLoaded = false;
		}
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
    
    
    /**
     * The player movement required different collision logic to avoid going the wrong way through platforms
     */
    @Override
    public void move() {
    	
    	boolean[] xyCollisions = testCollisionWithOffset(0, 0, dx, dy, true);
//    	System.out.println(jumping + "|" + dy);
    	
    	if (xyCollisions[0]) {
    		dx = 0;
    		d2x = d2x/2;
    	}
    	if (xyCollisions[1]) {
    		dy = 0;
    		d2y = d2y/2;
    	}
    	
    	if(dy != 0) {
    		//Assumption that the player is falling if dy isn't 0
    		jumping = true;
    	}
    	
		dx += d2x;
		dy += d2y + gravity;
		
		super.applyFriction();
//		super.updateCollision();
		
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
    	
    }


    /**
     * Draws the player sprite on the screen, flipping it horizontally if 
     * facing left. If the sprite is not loaded, a blue rectangle is drawn instead.
     *
     * @param g the graphics context to draw the player
     */
    public void draw(Graphics2D g2) {
    	g2.translate(drawX, drawY);
        if (spriteLoaded) {
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
	 * Collide checks to see if the player has hit any enemies or collectables
	 * @param enemies -- A reference to the EnemyModel
	 * @param collectables -- A reference to the CollectableModel
	 * @param downPressed -- If the key for collecting a collectable is being pressed
	 * @param hud -- a reference to the Hud, which has the player data
	 */
	public void collide(EnemyModel enemies, CollectableModel collectables,boolean downPressed, Hud hud) {
		   
        
  	  Rectangle2D.Double playerBounds = new Rectangle2D.Double(x,y,width,height);
        long currentTime = System.currentTimeMillis();
      		
      // Enemy collisions- each enemy hit looses player a life, you have a 2 second grace period before you can be hit again
        for (Enemy enemy : enemies.getEnemies()) {
      	  Rectangle2D.Double enemyBounds = enemy.getBounds();
      	  //enemyBounds.x, enemyBounds.y, enemyBounds.width, enemyBounds.height
            if (playerBounds.intersects(enemyBounds)) {
                if (currentTime - lastHitTime > invulnerabilityDuration) {
                    hud.decrementLives();
                    lastHitTime = currentTime;
                    

                    

                    System.out.println("Player hit by enemy.");
                }
            
        }
        }
      // Collectable collisions- Each yarn piece is only worth 10 points, cannot keep standing on yarn for more points
      if(collectables.getCollectables()!=null) {
    	  ArrayList<Collectable> newCollectable= new ArrayList<Collectable>();
      	for (Collectable collectable : collectables.getCollectables()) {
      		if (playerBounds.intersects(collectable.getBounds()) && downPressed) {
      			if (hud != null && collectables.getCollectables()!=null) 
      				hud.incrementScore(10);
      				System.out.println("Collected yarn!");
      		}else {
      			newCollectable.add(collectable);
      		}
      	}
      	collectables.setCollectableList(newCollectable);
      }
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
