package Trevor;


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
public class Player {
    private int x, y;
    private int width, height;
    private int velocityY = 0;
    private int speed = 5;
    private boolean jumping = false;
    private final int gravity = 1;
    private final int jumpPower = -20;
    private final int groundY = 744;
    private BufferedImage sprite;

    private boolean facingRight = true;
    
    
    /**
     * Constructs a new Player at the specified starting coordinates.
     *
     * @param x the initial x-coordinate of the player
     * @param y the initial y-coordinate of the player
     */
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        width = 64;
        height = 64;

        try {
            sprite = ImageIO.read(getClass().getResource("cat.png"));
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
    public void update(List<Tile> tiles) {
        int prevY = y;
        y += velocityY;
        velocityY += gravity;

        Rectangle playerBounds = new Rectangle(x, y, width, height);

        // Check collisions with all tiles
        for (Tile tile : tiles) {
            Rectangle tileBounds = tile.getBounds();

            // only check if moving downward
            if (velocityY >= 0 && playerBounds.intersects(tileBounds)) {
                int playerBottomPrev = prevY + height;
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
    public void moveLeft() {
        x -= speed;
        facingRight = false; //turn left
    }

    public void moveRight() {
        x += speed;
        facingRight = true; //turn right
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
