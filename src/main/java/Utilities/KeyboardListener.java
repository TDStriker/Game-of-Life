package Utilities;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardListener extends KeyAdapter {
    int input = 0;

    @Override
    public void keyPressed(KeyEvent event) {
        input = event.getKeyCode();
    }

    public int getInput() {
        return input;
    }
}
