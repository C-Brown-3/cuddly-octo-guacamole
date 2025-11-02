package scrollingScreenExample;

public class GameComponent {

	private CameraTestEntity cameraTestEntity;
	private Camera camera;
	private ThingThatCanBeDrawn thing;

	public GameComponent(CameraTestEntity cameraTestEntity, Camera camera, ThingThatCanBeDrawn thing) {
		// TODO Auto-generated constructor stub
		this.cameraTestEntity = cameraTestEntity;
		this.camera = camera;
		this.thing = thing;
	}

	public void tick(boolean leftPressed, boolean rightPressed) {
		// TODO Auto-generated method stub
		if (leftPressed) {
			cameraTestEntity.incrementX(-1);
		}
		if(rightPressed) {
			cameraTestEntity.incrementX(1);
		}
		camera.move();
		System.out.println(cameraTestEntity.x + "|" + camera.x + "|" + thing.drawX);
	}

}
