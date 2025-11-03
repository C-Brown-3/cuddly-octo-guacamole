

/**
 * Drawable is an abstract class which should be implemented by classes that can be on screen, either directly or from the inheritance chain
 */
public abstract class Drawable {
	int drawX;
	int drawY;
	double x;
	double y;
	
	public Drawable() {
		this.drawX = 0;
		this.drawY = 0;
	}
	
	public void updateDrawXY(double x, double y) {
		this.drawX = (int) x;
		this.drawY = (int) y;
	}
	
}
