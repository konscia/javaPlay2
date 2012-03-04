/*
 * TileInfo
 */

package javaPlay;

import java.awt.Point;

/**
 * @author VisionLab/PUC-Rio
 */
public class TileInfo
{
    public int id;
    public Point min;
    public Point max;

    public TileInfo()
    {
       min = new Point();
       max = new Point();
    }
}
