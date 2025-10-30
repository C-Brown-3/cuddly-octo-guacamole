package Evie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private Player player;
    private Platform platform;
    private boolean leftPressed, rightPressed;

    public GamePanel() {
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.WHITE);

        player = new Player(100, 500);
        platform = new Platform(300, 350, 200, 20); // middle platform

        timer = new Timer(16, this); // ~60 FPS
        timer.start();

        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (leftPressed) player.moveLeft();
        if (rightPressed) player.moveRight();
        player.update(platform);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        platform.draw(g);
        player.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) leftPressed = true;
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) rightPressed = true;
        if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP) player.jump();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) leftPressed = false;
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) rightPressed = false;
    }

    @Override public void keyTyped(KeyEvent e) {}
}
