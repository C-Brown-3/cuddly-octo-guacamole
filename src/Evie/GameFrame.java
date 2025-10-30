package Evie;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("Platform Jump Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePanel panel = new GamePanel();
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        panel.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameFrame::new);
    }
}
