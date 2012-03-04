/*
 * GameStateController
 */

package javaPlay;

import java.awt.*;

/**
 * @author VisionLab/PUC-Rio
 */
public interface GameStateController
{
    public void load();
    public void unload();
    public void start();
    public void step(long timeElapsed);
    public void draw(Graphics g);
    public void stop();
}
