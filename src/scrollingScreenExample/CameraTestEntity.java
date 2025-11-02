package scrollingScreenExample;

public class CameraTestEntity extends Entity{

	public CameraTestEntity(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public void incrementX(int dx) {
		this.x += dx;
	}
	
	public void incrementY(int dx) {
		this.x += dx;
	}
}
