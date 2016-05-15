import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Graphics2D;
/**
 * Write a description of class Tile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tile
{
    private Character thing;
    private int r;
    private int c;
    private int color;
    private int terrain;
    private int distance;
    private boolean moveable;
    public Tile(int row, int col, int color, int terrain){
        r = row;
        c = col;
        this.color = color;
        this.terrain = terrain * 2;
        moveable = false;
        distance = 100;
    }

    public void addCharacter(Character h){
        thing = h;
        terrain += 1000;
        h.setHome(this);
    }

    public void removeCharacter(){
        thing = null;
        terrain -= 1000;
    }

    public Character getCharacter(){
        return thing;
    }

    public int getColor(){
        return color;
    }

    public int getRow(){
        return r;
    }

    public int getCol(){
        return c;
    }

    public void isMoveable(boolean b){
        moveable = b;
    }

    public int getTerrain(){
        return terrain;
    }

    public boolean getMoveable(){
        return moveable;
    }

    public int getDistance(){
        return distance;
    }

    public void setDistance(int n){
        distance = n;
    }

    public void draw(Graphics2D g2, BufferedImage img, int x, int y){
        g2.drawImage(img,x,y,null);
        if(getMoveable()){
            float thickness = 4;
            Stroke oldStroke = g2.getStroke();
            g2.setStroke(new BasicStroke(thickness));
            g2.setColor(Color.green);
            g2.drawRect(x + 2, y + 2, 56, 56);
            g2.setStroke(oldStroke);
        }
                if(thing != null){
            thing.draw(g2,x,y);
            g2.drawString("Current Row : " + ("" + r), 0, 100);
            g2.drawString("Current Col : " + ("" + c), 0, 200);
        }
    }
}

