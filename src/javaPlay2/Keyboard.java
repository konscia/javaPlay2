/*
 * Keyboard
 */

package javaPlay;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * @author VisionLab/PUC-Rio
 */
public class Keyboard implements KeyListener
{
    private Hashtable keysPressed;

    public static final int UP_KEY = 38;
    public static final int LEFT_KEY = 37;
    public static final int RIGHT_KEY = 39;
    public static final int DOWN_KEY = 40;
    public static final int ESCAPE_KEY = 27;
    public static final int SPACE_KEY = 32;
    public static final int ENTER_KEY = 10;

    public Keyboard()
    {
        keysPressed = new Hashtable();
    }

    public synchronized boolean keyDown(int keyCode)
    {		
        return keysPressed.contains(keyCode);
    }

    public void keyTyped(KeyEvent e)
    {
        
    }

    public synchronized void keyPressed(KeyEvent e)
    {		
        if(keysPressed.contains(e.getKeyCode()) == false)
        {
            keysPressed.put(e.getKeyCode(), e.getKeyCode());            
        }
    }

    public synchronized void keyReleased(KeyEvent e)
    {     
        keysPressed.remove(e.getKeyCode());        
    }
}
