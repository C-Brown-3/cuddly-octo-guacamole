import java.awt.Dimension;

import javax.swing.JFrame;

public class FaceViewer {
	
	private static final Dimension FACE_VIEWER_SIZE = new Dimension(900,600);

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		frame.setSize(FACE_VIEWER_SIZE);
		frame.setTitle("I see faces!");

		frame.add(new FaceComponent());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
