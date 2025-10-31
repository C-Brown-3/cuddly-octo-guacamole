

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.List;

public class Player {
    private int x, y;
    private int width, height;
    private int velocityY = 0;
    private int speed = 5;
    private boolean jumping = false;
    private final int gravity = 1;
    private final int jumpPower = -20;
    private final int groundY = 550;
    private BufferedImage sprite;

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

    public void jump() {
        if (!jumping) {
            jumping = true;
            velocityY = jumpPower;
        }
    }

    public void moveLeft() { x -= speed; }
    public void moveRight() { x += speed; }

    public void draw(Graphics g) {
        if (sprite != null)
            g.drawImage(sprite, x, y, width, height, null);
        else {
            g.setColor(Color.BLUE);
            g.fillRect(x, y, width, height);
        }
    }
}
