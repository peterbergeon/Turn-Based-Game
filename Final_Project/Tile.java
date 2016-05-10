import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
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
    public Tile(int row, int col, int color){
        r = row;
        c = col;
        this.color = color;
        terrain = color / 10;
    }

    public void addCharacter(Character h){
        thing = h;
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

    public void draw(Graphics2D graphics2, BufferedImage img, int x, int y){
        graphics2.drawImage(img,x,y,null);
        if(thing != null){
            thing.draw(graphics2,x,y);
        }
    }
}

