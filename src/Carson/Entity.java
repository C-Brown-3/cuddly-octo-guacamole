package Carson;

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
	
	public Entity(double x, double y) {
		super();
		this.x = x;
		this.y = y;
		dx = 0;
		dy = 0;
		d2x = 0;
		d2y = 0;
	}
	
	public void move() {
		x += dx;
		dx += d2x;
		y += dy;
		dy += d2y;
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
}