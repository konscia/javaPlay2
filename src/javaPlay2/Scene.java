/*
 * Scene
 */

package javaPlay;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author VisionLab/PUC-Rio
 */
public class Scene
{
    private Image backDrop;
    private Image[] tiles;
    private ArrayList tileLayer;
    private ArrayList overlays;
    private int drawStartX = 0;
    private int drawStartY = 0;
    private final int MAX_SLEEP_COUNT = 30;

    public void loadFromFile(String sceneFile) throws InterruptedException, FileNotFoundException, IOException, Exception
    {
        tileLayer = new ArrayList();
        overlays = new ArrayList();

        BufferedReader input = new BufferedReader(new FileReader(new File(sceneFile)));

        //first read the number of tile images
        String line = input.readLine();

        int numOfTileImages = Integer.parseInt(line, 10);

        tiles = new Image[numOfTileImages];

        int count;

        for(int i = 0 ; i < numOfTileImages ; i++)
        {
            //read each tile image name
            line = input.readLine();

            tiles[i] = Toolkit.getDefaultToolkit().getImage(line);

            count = 0;
            while(tiles[i].getWidth(null) == -1)
            {
                Thread.sleep(1);

                count++;

                if(count == MAX_SLEEP_COUNT)
                {
                    throw new Exception("image could not be loaded");
                }
            }
        }

        //now read the tile set map until the final
        //character is found "%"
        String endTileSet = "%";

        line = input.readLine();

        while(line.equals(endTileSet) != true)
        {
            ArrayList tileLine = new ArrayList();

            String[] tileIndices = line.split(",");

            for(int i = 0 ; i < tileIndices.length ; i++)
            {
                tileLine.add(Integer.parseInt(tileIndices[i]));
            }

            tileLayer.add(tileLine);

            line = input.readLine();
        }

        //now read the backdrop file
        line = input.readLine();

        backDrop = Toolkit.getDefaultToolkit().getImage(line);

        count = 0;
        while(backDrop.getWidth(null) == -1)
        {
            Thread.sleep(1);

            count++;

            if(count == MAX_SLEEP_COUNT)
            {
                throw new Exception("image could not be loaded");
            }
        }
    }

    public void addOverlay(GameObject overlay)
    {
        overlays.add(overlay);
    }

    public void removeOverlay(GameObject overlay)
    {
        overlays.remove(overlay);
    }

    public void setDrawStartPos(int drawStartX, int drawStartY)
    {
        this.drawStartX = drawStartX;
        this.drawStartY = drawStartY;
    }

    public void draw(Graphics g)
    {
        //first clear the scene
        GameCanvas canvas = GameEngine.getInstance().getGameCanvas();

        g.setColor(Color.BLACK);

        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        //first draw the backdrop
        int startDrawX = canvas.getRenderScreenStartX() - drawStartX;
        int startDrawY = canvas.getRenderScreenStartY() - drawStartY;

        g.drawImage(backDrop, startDrawX, startDrawY, null);

        //now draw the tile set
        int tileWidth = tiles[0].getWidth(null);
        int tileHeight = tiles[0].getHeight(null);

        int line = 0;
        int drawY = startDrawY;

        do
        {
            ArrayList tileLine = (ArrayList)tileLayer.get(line);

            int drawX = startDrawX;

            for(int c = 0 ; c < tileLine.size() ; c++, drawX += tileWidth)
            {
                int idx = (Integer)tileLine.get(c);

                if(idx == 0)
                {
                    continue;
                }

                g.drawImage(tiles[idx-1], drawX, drawY, null);
            }

            drawY += tileHeight;
            line++;
        
        }while(line < tileLayer.size());

        //finally draw the overlays
        for(int i = 0 ; i < overlays.size() ; i++)
        {
            GameObject element = (GameObject)overlays.get(i);

            element.draw(g);
        }
    }

    public Vector getTilesFromRect(Point min, Point max)
    {
        Vector v = new Vector();

        GameCanvas canvas = GameEngine.getInstance().getGameCanvas();
        
        int startDrawX = canvas.getRenderScreenStartX() - drawStartX;
        int startDrawY = canvas.getRenderScreenStartY() - drawStartY;
        
        int tileWidth = tiles[0].getWidth(null);
        int tileHeight = tiles[0].getHeight(null);

        int line = 0;
        int drawY = startDrawY;

        do
        {
            ArrayList tileLine = (ArrayList)tileLayer.get(line);

            int drawX = startDrawX;

            for(int c = 0 ; c < tileLine.size() ; c++, drawX += tileWidth)
            {
                TileInfo tile = new TileInfo();

                tile.id = (Integer)tileLine.get(c);
                tile.min.x = drawX - canvas.getRenderScreenStartX();
                tile.min.y = drawY - canvas.getRenderScreenStartY();
                tile.max.x = drawX - canvas.getRenderScreenStartX() + tileWidth - 1;
                tile.max.y = drawY - canvas.getRenderScreenStartY() + tileHeight - 1;

                if((min.x > tile.max.x) || (max.x < tile.min.x))
                {
                    continue;
                }
                if((min.y > tile.max.y) || (max.y < tile.min.y))
                {
                    continue;
                }
                
                v.add(tile);
            }

            drawY += tileHeight;
            line++;

        }while(line < tileLayer.size());

        return v;
    }

    public void step(int timeElapsed)
    {
        for(int i = 0 ; i < overlays.size() ; i++)
        {
            GameObject element = (GameObject)overlays.get(i);

            element.step(timeElapsed);
        }
    }
}
