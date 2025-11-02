package Carson;


import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    //private Player player;
    private Level level;
    private boolean leftPressed, rightPressed;
    private int screenWidth;
    private int screenHeigth;
    private GameComponent gameComponent;
    private CameraTestEntity cameraTestEntity;
    private Camera camera;
    private ThingThatCanBeDrawn thing;

    public GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);

        cameraTestEntity = new CameraTestEntity(0.0, 0.0);
        camera = new Camera(cameraTestEntity, this.getWidth(), this.getHeight());
    	thing = new ThingThatCanBeDrawn(64, 64, Color.red, 0, 0);
        
        gameComponent = new GameComponent(cameraTestEntity, camera, thing);
        
        this.timer = new Timer(30, e -> tick());
        timer.start();

        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }
    
    // Make Animations happen at the same time, update all models
    private void tick() {
    	this.updateDrawCoordinates();
    	this.gameComponent.tick(leftPressed, rightPressed);
    	this.paintComponent(getGraphics());
    }

	private void updateDrawCoordinates() {
    	camera.calculateAndSetDrawXY(thing);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (leftPressed) cameraTestEntity.incrementX(-5);
        if (rightPressed) cameraTestEntity.incrementY(5);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        thing.draw(g2);
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) leftPressed = true;
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) rightPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) leftPressed = false;
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) rightPressed = false;
    }

    @Override public void keyTyped(KeyEvent e) {}
}
