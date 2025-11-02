import javax.swing.JFrame;


public class Viewer{
	
	
	
	
	public Viewer() {
		
	}
	public static void main(String[] args) {
		Component comp=new Component();
		JFrame frame = new JFrame("Viewer");
		frame.setSize(1200, 1200);
		//frame.pack(); // 1) compute preferred size
		frame.setLocationRelativeTo(null); // 2) center on screen
		
		frame.add(comp);
		comp.start();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
