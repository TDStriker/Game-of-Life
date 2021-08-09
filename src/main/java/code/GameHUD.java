package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameHUD implements ActionListener {
    private boolean isRunning;
    private boolean isReset;

    JButton playButton;
    JButton pauseButton;
    JButton resetButton;

    JSlider refreshRate;

    ImageIcon buttonBackground;

    public GameHUD(JFrame frame){

        this.isRunning = false;
        this.isReset = false;

        buttonBackground = new ImageIcon("src/main/java/assets/images/Baton.png");

        playButton = new JButton("Play", buttonBackground);
        pauseButton = new JButton("Pause", buttonBackground);
        resetButton = new JButton("Clear", buttonBackground);

        playButton.setHorizontalTextPosition(SwingConstants.CENTER);
        playButton.setForeground(Color.white);
        playButton.setFont(new Font("Comic Sans MS", (Font.BOLD), 16));
        playButton.setBounds(790, 20, 100, 100);
        playButton.setActionCommand("Play");
        playButton.setBackground(Color.red);
        playButton.setFocusable(false);
        playButton.addActionListener(this);
        playButton.setVisible(true);
        frame.add(playButton);

        pauseButton.setHorizontalTextPosition(SwingConstants.CENTER);
        pauseButton.setForeground(Color.white);
        pauseButton.setFont(new Font("Comic Sans MS", (Font.BOLD), 16));
        pauseButton.setBounds(895, 20, 100, 100);
        pauseButton.setActionCommand("Pause");
        pauseButton.setBackground(Color.red);
        pauseButton.setFocusable(false);
        pauseButton.addActionListener(this);
        pauseButton.setVisible(true);
        frame.add(pauseButton);

        resetButton.setHorizontalTextPosition(SwingConstants.CENTER);
        resetButton.setForeground(Color.white);
        resetButton.setFont(new Font("Comic Sans MS", (Font.BOLD), 16));
        resetButton.setBounds(790, 125, 100, 100);
        resetButton.setActionCommand("Clear");
        resetButton.setBackground(Color.red);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);
        resetButton.setVisible(true);
        frame.add(resetButton);

        refreshRate = new JSlider(JSlider.HORIZONTAL, 0, 1000, 350);
        refreshRate.setBounds(790, 225, 205,50);
        refreshRate.setMajorTickSpacing(200);
        refreshRate.setMinorTickSpacing(50);
        refreshRate.setPaintTicks(true);
        refreshRate.setPaintLabels(true);
        frame.add(refreshRate);
    }

    public boolean isRunning(){
        return isRunning;
    }

    public boolean isReset(){
        boolean reset = isReset;
        isReset = false;
        return reset;
    }

    public int getRefreshRate(){
        return refreshRate.getValue();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if("Play".equals(e.getActionCommand())) {
            System.out.println("Running");
            isRunning = true;
        }else if("Pause".equals(e.getActionCommand())){
            System.out.println("Paused");
            isRunning = false;
        }else if("Clear".equals(e.getActionCommand())){
            System.out.println("Cleared");
            isReset = true;
        }
    }
//IMPORTANT LORE: BIG PURPLE L - Why pieces join together when the game ends

}
