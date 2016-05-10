import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;
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

    public void draw(Graphics2D graphics2, BufferedImage img, int x, int y){
        graphics2.drawImage(img,x,y,null);
        if(thing != null){
            thing.draw(graphics2,x,y);
        }
        if(getMoveable()){
            Ellipse2D.Double circle = new Ellipse2D.Double(x + 25,y + 25,10,10);
            graphics2.setColor(Color.green);
            graphics2.fill(circle);
        }
    }
}

