package code;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ProjectSettings {
    public static Dimension DIM = new Dimension(1000, 800);

    public static int SQUARE_LENGTH = 25;

    public static int LEFT_KEY = KeyEvent.VK_LEFT;
    public static int RIGHT_KEY = KeyEvent.VK_RIGHT;
    public static int UP_KEY = KeyEvent.VK_UP;
    public static int DOWN_KEY = KeyEvent.VK_DOWN;
    public static int ENTER_KEY = KeyEvent.VK_ENTER;

    public static Canvas CANVAS;

    public static void setCanvas(Canvas canvas){
        ProjectSettings.CANVAS = canvas;
    }

    public static void addKeyHandler(KeyListener handler){
        CANVAS.addKeyListener(handler);
    }
}
