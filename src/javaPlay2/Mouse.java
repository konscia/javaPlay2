/*
 * Mouse
 */

package javaPlay;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * @author VisionLab/PUC-Rio
 */
public class Mouse implements MouseMotionListener, MouseListener
{
    private Point mousePos;
    private boolean leftButtonPressed;
    private boolean middleButtonPressed;
    private boolean rightButtonPressed;

    public Mouse()
    {
        mousePos = new Point(0, 0);
        leftButtonPressed = false;
        middleButtonPressed = false;
        rightButtonPressed = false;
    }

    public Point getMousePos()
    {
        return mousePos;
    }

    public boolean isLeftButtonPressed()
    {
        return leftButtonPressed;
    }

    public boolean isMiddleButtonPressed()
    {
        return middleButtonPressed;
    }

    public boolean isRightButtonPressed()
    {
        return rightButtonPressed;
    }

    public void mouseClicked(MouseEvent e)
    {
        
    }

    public void mousePressed(MouseEvent e)
    {
        switch(e.getButton())
        {
            case MouseEvent.BUTTON1:
                leftButtonPressed = ((e.getModifiersEx() & MouseEvent.BUTTON1_DOWN_MASK) != 0);
                break;
            case MouseEvent.BUTTON2:
                middleButtonPressed = ((e.getModifiersEx() & MouseEvent.BUTTON2_DOWN_MASK) != 0);
                break;
            case MouseEvent.BUTTON3:
                rightButtonPressed = ((e.getModifiersEx() & MouseEvent.BUTTON3_DOWN_MASK) != 0);
                break;
        }
    }

    public void mouseReleased(MouseEvent e)
    {
        switch(e.getButton())
        {
            case MouseEvent.BUTTON1:
                leftButtonPressed = ((e.getModifiersEx() & MouseEvent.BUTTON1_DOWN_MASK) != 0);
                break;
            case MouseEvent.BUTTON2:
                middleButtonPressed = ((e.getModifiersEx() & MouseEvent.BUTTON2_DOWN_MASK) != 0);
                break;
            case MouseEvent.BUTTON3:
                rightButtonPressed = ((e.getModifiersEx() & MouseEvent.BUTTON3_DOWN_MASK) != 0);
                break;
        }
    }

    public void mouseEntered(MouseEvent e)
    {
        
    }

    public void mouseExited(MouseEvent e)
    {
        
    }

    public void mouseDragged(MouseEvent e)
    {
        
    }

    public void mouseMoved(MouseEvent e)
    {
        mousePos = e.getPoint();
    }

}
