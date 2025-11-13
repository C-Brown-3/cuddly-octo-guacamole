package Carson;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Camera extends Entity implements TopLevelClass{
	/**
	 * The Camera is as wide and tall as the screen, and it's x and y coordinates are the top left of the rectangle of stuff
	 * being displayed. Cameras are passed entities and calculate and set the draw x and y coordinates for entities.
	 * 
	 * It is assumed that the level coordinates exist with (0,0) representing the top left coordinate of the level. 
	 */
	private Entity entityWatched;
	private int screenWidth;
	private int screenHeight;
	private static final double X_SCROLL_BOUNDARY = 0.0;
	private static final double Y_UPPER_SCROLL_BOUNDARY = 0.0;
	private static final double Y_LOWER_SCROLL_BOUNDARY = 0.0;
	private static final Color BG_COLOR = new Color(174, 222, 203);
	private BufferedImage bgLayer1, bgLayer2, bgLayer3, bgLayer4;
	
	public Camera (Entity entityWatched, LevelModel levelModel, int screenWidth, int screenHeight) {
		super(0, 0, null);
		this.entityWatched = entityWatched;
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.levelModel = levelModel;
		
		try {
			bgLayer1 = ImageIO.read(new File("src/resources/plx-2.png"));
			bgLayer2 = ImageIO.read(new File("src/resources/plx-3.png"));
			bgLayer3 = ImageIO.read(new File("src/resources/plx-4.png"));
			bgLayer4 = ImageIO.read(new File("src/resources/plx-5.png"));
		} catch (IOException e) {
			System.out.println("Tileset image load error.A");
		} catch (IllegalArgumentException e) {
			System.out.println("Tileset image load error.B");
		}
	}

	public Entity getEntityWatched() {
		return entityWatched;
	}

	public void setEntityWatched(Entity entityWatched) {
		this.entityWatched = entityWatched;
	}

	public void setScreenHeight(int screenHeight) {
//		System.out.println("Height");
		this.screenHeight = screenHeight;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}
	
	public void calculateAndSetDrawXY(Drawable drawable) {
		double drawX = drawable.x - this.x;
		double drawY = drawable.y - this.y;
		drawable.updateDrawXY(drawX, drawY);
	}

	@Override
	/**
	 * Make the camera follow the watched entity, allowing for some wiggle room in which the camera will not scroll
	 */
	public void move() {
//		System.out.println(screenWidth + " | " + screenHeight);
		this.x = entityWatched.x + entityWatched.width/2 - screenWidth/2;
		this.y = entityWatched.y + entityWatched.height/2 - screenHeight/2;
		
		if (this.levelModel != null) {
			
		
			double levelX = this.levelModel.getLevelBoundaries()[0];
			double levelY = this.levelModel.getLevelBoundaries()[1];		
			if (this.x + screenWidth > levelX) {
				this.x = this.levelModel.getLevelBoundaries()[0] - screenWidth;
			} else if (this.x < 0) {
				this.x = 0;
			}
			
			if (this.y + screenHeight > levelY) {
				this.y = levelY - screenHeight;
			} else if (this.y < 0) {
				this.y = 0;
			}
		
//		System.out.println(levelX + "|" + this.x);
		
		}
	}

	public void tick() {
		// TODO Auto-generated method stub
		move();
	}

	@Override
	/**
	 * Since the camera already knows the screen location relative to the level boundaries,
	 * the camera is responsible for drawing the parallax background
	 */
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		double levelX = this.levelModel.getLevelBoundaries()[0];
		double levelY = this.levelModel.getLevelBoundaries()[1];
		int bgHeight = 216, bgWidth = 384;
		//Make squares for pixels in the background
		double scale = levelY/bgHeight;
		int drawHeight = (int) levelY;
		int drawWidth = (int) Math.round(drawHeight * scale);
		g2.setBackground(BG_COLOR);
		g2.translate(-x/4, -y);
		g2.drawImage(bgLayer1, 0, 0, drawWidth, drawHeight, null);
		g2.translate(-x/4, 0);
		g2.drawImage(bgLayer2, 0, 0, drawWidth, drawHeight, null);
		g2.translate(-x/4, 0);
		g2.drawImage(bgLayer3, 0, 0, drawWidth, drawHeight, null);
		g2.translate(-x/4, 0);
		g2.drawImage(bgLayer4, 0, 0, drawWidth, drawHeight, null);
		g2.translate(x, y);
		
		
	}
	
	
}
