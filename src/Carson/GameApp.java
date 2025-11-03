package Carson;
import javax.swing.JFrame;


public class GameApp{
	/**
	 * The Viewer Class is the entry point for the program which instances Component and attaches it to a JFrame.
	 *	It is responsible for all window management decisions and logic, such as the title of the program. 
	 *
	 */
	
	private final JFrame frame = new JFrame("Minigun Grandma 2: The Cat's Scratch");
	private final GamePanel panel = new GamePanel();

	/**
     * Constructs the main game window and attaches the {@link GamePanel}.
     * The window is configured but not made visible yet.
     */
	public GameApp() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
//        frame.setSize(1200, 1200); //Used in default package, but by swapping to GamePanel, which has a preferred dimension, this should work better 
        frame.pack();                  // Fit to preferred component sizes
        frame.setLocationRelativeTo(null); // Center on screen
		
	}
	
	/**
     * Displays the game window on screen.
     */
	public void show() {
		// TODO Auto-generated method stub
		frame.setVisible(true);
	}

}
