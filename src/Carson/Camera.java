package Carson;

public class Camera extends Entity{
	/**
	 * The Camera is as wide and tall as the screen, and it's x and y coordinates are the top left of the rectangle of stuff
	 * being displayed. Cameras are passed entities and calculate and set the draw x and y coordinates for entities.
	 * 
	 * It is assumed that the level coordinates exist with (0,0) representing the top left coordinate of the level. 
	 */
	private Entity entityWatched;
	private Level level;
	private int screenWidth;
	private int screenHeight;
	private static final double X_SCROLL_BOUNDARY = 64.0;
	private static final double Y_UPPER_SCROLL_BOUNDARY = 64.0;
	private static final double Y_LOWER_SCROLL_BOUNDARY = 64.0;
	
	public Camera (Entity entityWatched, int screenWidth, int screenHeight) {
		super(0, 0);
		this.entityWatched = entityWatched;
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
	}

	public Entity getEntityWatched() {
		return entityWatched;
	}

	public void setEntityWatched(Entity entityWatched) {
		this.entityWatched = entityWatched;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	
	public void calculateAndSetDrawXY(Entity entity) {
		double drawX = entity.x - this.x;
		double drawY = entity.y - this.y;
		entity.updateDrawXY(drawX, drawY);
	}

	@Override
	/**
	 * Make the camera follow the watched entity, allowing for some wiggle room in which the camera will not scroll
	 */
	public void move() {

		if (this.x + this.screenWidth - X_SCROLL_BOUNDARY < this.entityWatched.x) {
			//See if entity is too far to the right of the camera 
			this.x = this.entityWatched.x - this.screenWidth + X_SCROLL_BOUNDARY;
		} else if(this.x + X_SCROLL_BOUNDARY > this.entityWatched.x) {
			//See if entity is too far to the left of the camera
			this.x = this.entityWatched.x - X_SCROLL_BOUNDARY;
		}
		
		
	}
	
	
}
