package Trevor;
import javax.swing.JFrame;

public class Viewer {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Viewer");
		frame.setSize(600, 400);
		//frame.pack(); // 1) compute preferred size
		frame.setLocationRelativeTo(null); // 2) center on screen
		
		frame.add(new Component());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
