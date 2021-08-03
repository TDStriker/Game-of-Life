package code;

import Utilities.KeyboardListener;

import javax.swing.*;
import java.awt.*;

public class MainClass extends JFrame {
    final Canvas renderySpot;

    Color backColor;

    GameArea game;

    final KeyboardListener keyInput = new KeyboardListener();

    private MainClass(){
        renderySpot = new Canvas();
        renderySpot.setMinimumSize(ProjectSettings.DIM);
        renderySpot.setMaximumSize(ProjectSettings.DIM);
        renderySpot.setPreferredSize(ProjectSettings.DIM);

        this.add(renderySpot);
        this.setResizable(false);
        this.pack();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        SwingUtilities.invokeLater(this::start);
        this.backColor = Color.BLACK;
    }

    private void start(){
        // set the look and feel to the system look and feel

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        renderySpot.setVisible(true);
        // don't allow AWT to paint the canvas since we are
        this.renderySpot.setIgnoreRepaint(true);
        // enable double buffering (the JFrame has to be
        // visible before this can be done)
        this.renderySpot.createBufferStrategy(2);
        this.renderySpot.requestFocus();
        this.renderySpot.addKeyListener(keyInput);
//        this.renderySpot.addMouseListener(mouseInput);

        ProjectSettings.setCanvas(this.renderySpot);

        game = new GameArea(this);

        final Thread gameThread = new Thread(this::gameLoop);
        gameThread.setDaemon(true);
        gameThread.start();

        backColor = Color.black;
    }

    public void gameLoop(){
        while(!false){
            long startTime = System.currentTimeMillis();

            update();

            try {
                Thread.sleep(Math.max(0, 10 - (System.currentTimeMillis() - startTime)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            render();
        }
    }

    public void update(){
        game.update(10);
    }

    public void render(){
        final Graphics2D g = (Graphics2D) renderySpot.getBufferStrategy().getDrawGraphics();
        g.setColor(backColor);

        game.render(g);

        renderySpot.getBufferStrategy().show();
    }

    public static void main(String[] args){
        try {
            MainClass tets = new MainClass();
            tets.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}