package Carson;

import javax.swing.JFrame;

public class GameApp {
	private final JFrame frame = new JFrame("Template");
	private final GamePanel panel = new GamePanel();
	
	/**
     * Constructs the main game window and attaches the {@link GamePanel}.
     * The window is configured but not made visible yet.
     */
	public GameApp() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();                  // Fit to preferred component sizes
        frame.setLocationRelativeTo(null); // Center on screen
		
	}

	/**
     * Displays the game window on screen.
     */
	public void show() {
		frame.setVisible(true);

	}

}