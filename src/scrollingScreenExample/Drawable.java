package scrollingScreenExample;

public abstract class Drawable {
	double drawX;
	double drawY;
	double x;
	double y;
	
	public Drawable() {
		this.drawX = 0;
		this.drawY = 0;
	}
	
	public void updateDrawXY(double x, double y) {
		this.drawX = x;
		this.drawY = y;
	}
	
}
