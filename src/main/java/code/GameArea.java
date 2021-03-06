package code;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;

public class GameArea implements GameObject, MouseListener {
    boolean[][] grid;
    boolean[][] prev;

    private final int border = 4;
    private final int heightOffset = 7;

    GameHUD controls;

    public GameArea(GameHUD controls){
        grid = new boolean[64][64];
        prev = new boolean[64][64];

        this.controls = controls;

        ProjectSettings.addMouseHandler(this);
    }

    private int countAdjacent(int x, int y){
        int adjacent = 0;
        for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                int xPos = x + i;
                int yPos = y + j;
                if(xPos < 0){
                    xPos += grid.length;
                }else if (xPos>=grid.length){
                    xPos -= grid.length;
                }
                if(yPos < 0){
                    yPos += grid.length;
                }else if (yPos>=grid.length){
                    yPos -= grid.length;
                }
                if (grid[xPos][yPos]) {
                    adjacent++;
                }
            }
        }
        return adjacent;
    }

    @Override
    public void update(int timePassed) {
        if(controls.isCleared()) {
            grid = new boolean[64][64];
        }else{
            if(controls.isJustStarted()){
                prev = grid;
            }
            if(controls.isRunning()) {
                boolean[][] newGrid = new boolean[64][64];
                for(int i = 0; i < grid.length; i++) {
                    for(int j = 0; j < grid.length; j++) {
                        int adjacent = countAdjacent(i, j);
                        if(grid[i][j] && (adjacent < 3 || adjacent > 4)) {
                            newGrid[i][j] = false;
                        }else if (adjacent == 3) {
                            newGrid[i][j] = true;
                        }else{
                            newGrid[i][j] = grid[i][j];
                        }
                    }
                }
                grid = newGrid;
            }
            if(controls.isReset()){
                grid = prev;
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
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = (e.getX() - (border*3))/ProjectSettings.SQUARE_LENGTH;
        int y = (e.getY() - (border*3))/ProjectSettings.SQUARE_LENGTH;

        if(x < grid.length && y < grid.length){
            grid[x][y] = !grid[x][y];
            System.out.println("thing should happen");
        }else{
            System.out.println("whoops");
        }
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
