/*
 * Sprite
 */

package javaPlay;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * @author VisionLab/PUC-Rio
 */
public class Sprite 
{    
    private Image image;
    private int animFrameCount;
    private int currAnimFrame;
    private int animFrameWidth;
    private int animFrameHeight;
    private int MAX_COUNT = 50;

    public Sprite(String filename, int animFrameCount, int animFrameWidth,
            int animFrameHeight) throws Exception
    {
        image = Toolkit.getDefaultToolkit().getImage(filename);

        int count = 0;

        while(image.getWidth(null) == -1)
        {
            Thread.sleep(1);
            count++;

            if(count == MAX_COUNT)
            {
                throw new Exception("image could not be loaded");
            }
        }

        this.animFrameCount = animFrameCount;
        this.animFrameWidth = animFrameWidth;
        this.animFrameHeight = animFrameHeight;
        
        this.currAnimFrame = 0;
    }

    public void setCurrAnimFrame(int frame)
    {
        currAnimFrame = frame;
    }

    public void draw(Graphics g, int x, int y)
    {
        GameCanvas canvas = GameEngine.getInstance().getGameCanvas();

        int xpos = canvas.getRenderScreenStartX() + x;
        int ypos = canvas.getRenderScreenStartY() + y;

        g.drawImage(image, xpos, ypos, xpos + animFrameWidth, ypos + animFrameHeight,
                currAnimFrame * animFrameWidth, 0, (currAnimFrame + 1) * animFrameWidth, animFrameHeight, null);
    }

    private Sprite(Image image, int animFrameCount,
            int currAnimFrame, int animFrameWidth, int animFrameHeight)
    {
        this.image = image;
        this.animFrameCount = animFrameCount;
        this.currAnimFrame = currAnimFrame;
        this.animFrameWidth = animFrameWidth;
        this.animFrameHeight = animFrameHeight;
    }

    public Sprite clone()
    {
        return new Sprite(image, animFrameCount, currAnimFrame,
                animFrameWidth, animFrameHeight);
    }
}
