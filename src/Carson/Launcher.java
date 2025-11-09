package Carson;

import javax.swing.SwingUtilities;
/*
 * Launcher class serves as the start point for the game.
 */
public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(()-> new GameApp().show());
	}

}
