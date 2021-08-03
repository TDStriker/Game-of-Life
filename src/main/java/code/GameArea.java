package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;

public class GameArea implements GameObject, ActionListener, MouseListener {
    boolean[][] grid;

    boolean running = false;

    private int border = 4;
    private int heightOffset = 7;

    private JButton playButton;
    private JButton pauseButton;

    public GameArea(JFrame frame){
        grid = new boolean[30][30];

        playButton = new JButton("Play");
        pauseButton = new JButton("Pause");

        playButton.setHorizontalTextPosition(SwingConstants.CENTER);
        playButton.setForeground(Color.white);
        playButton.setFont(new Font("Comic Sans MS", (Font.BOLD), 16));
        //TODO: Get actual x & y vals
        playButton.setBounds(grid.length * ProjectSettings.SQUARE_LENGTH + 50, 50, 120, 80);
        playButton.setActionCommand("play");
        playButton.setBackground(Color.red);
        playButton.setFocusable(false);
        playButton.addActionListener(this);
        playButton.setVisible(false);
        frame.add(playButton);

        pauseButton.setHorizontalTextPosition(SwingConstants.CENTER);
        pauseButton.setForeground(Color.white);
        pauseButton.setFont(new Font("Comic Sans MS", (Font.BOLD), 16));
        //TODO: Get actual x & y val
        pauseButton.setBounds(grid.length * ProjectSettings.SQUARE_LENGTH + 50, 150, 120, 80);
        pauseButton.setActionCommand("pause");
        pauseButton.setBackground(Color.red);
        pauseButton.setFocusable(false);
        pauseButton.addActionListener(this);
        pauseButton.setVisible(false);
        frame.add(pauseButton);
    }

    @Override
    public void update(int timePassed) {
        playButton.setVisible(true);
        pauseButton.setVisible(true);
        if(running){
            for(int i = 0; i < grid.length; i++){
                for(int j = 0; j < grid.length; j++){
                    if(grid[j][i]){

                    }else{

                    }
                }
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        AffineTransform old = g.getTransform();

        g.translate(border*2, heightOffset);
        g.setStroke(new BasicStroke (border));
        g.drawRect(0, 0, ((ProjectSettings.SQUARE_LENGTH* grid.length) + 2 * border), ((ProjectSettings.SQUARE_LENGTH*grid.length)+ 2 * border));
        g.translate(border,border);

        g.setStroke(new BasicStroke (border/4));

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                g.setColor(grid[j][i] ? Color.yellow : Color.black);
                g.fillRect(j*ProjectSettings.SQUARE_LENGTH,i*ProjectSettings.SQUARE_LENGTH,ProjectSettings.SQUARE_LENGTH,ProjectSettings.SQUARE_LENGTH);

                g.setColor(Color.white);
                g.drawRect(j*ProjectSettings.SQUARE_LENGTH,i*ProjectSettings.SQUARE_LENGTH,ProjectSettings.SQUARE_LENGTH,ProjectSettings.SQUARE_LENGTH);
            }
        }

        g.setTransform(old);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if("Play".equals(e.getActionCommand())) {
            System.out.println("Running");
            running = true;
        }else if("Pause".equals(e.getActionCommand())){
            System.out.println("Paused");
            running = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = (e.getX() - (border*3))/ProjectSettings.SQUARE_LENGTH;
        int y = (e.getY() - (border*3))/ProjectSettings.SQUARE_LENGTH;

        if(x < grid.length && y < grid.length){
            grid[y][x] = !grid[y][x];
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
