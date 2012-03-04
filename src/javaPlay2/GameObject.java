/*
 * GameObject
 */

package javaPlay;

import java.awt.Graphics;

/**
 * @author VisionLab/PUC-Rio
 */
public abstract class GameObject
{
    public int x;
    public int y;

    public abstract void step(long timeElapsed);
    public abstract void draw(Graphics g);
}
