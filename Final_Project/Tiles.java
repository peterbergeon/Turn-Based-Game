import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.Graphics2D;
/**
 * Write a description of class Tiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tiles
{
    private int CRIR; //current row in room
    private int CCIR; //current collum in room
    private int width;
    private int height;
    private String roomType;
    private String tileType;
    private Character thing;
    BufferedImage img;
    
    public Tiles(int i,int k,String roomType, BufferedImage img){
        CRIR = i;
        CCIR = k;
        this.roomType = roomType;
        tileType = roomType;
        this.img = img;
    }
    
    public String getType(){
        return tileType;
    }
    
    public int getCRIR(){
        return CRIR;
    }
    
    public int getCCIR(){
        return CCIR;
    }
    
    public Character getCharacter(){
        return thing;        
    }
    
    public void draw(Graphics2D g, int x, int y){
        g.drawImage(img,x,y,null);
        if(thing != null){
            thing.draw(g,x,y);
        }
    }
    
//    public void setXY(int x, int y){
//        this.x = x;
//        this.y = y;
 //   }
       
//    public void shift(int dx, int dy){
//        x -= dx * 60;
  //      y -= dy * 60;
    //}
    
//    public int getX(){
//        return x;
//    }
//        public int getY(){
//        return y;
//    }
    
//    public boolean hasHero(){
//        if(thing.hasHero()) return true;
//        return false;
 //   }
    
    public void addHero(Hero you){
        thing = you;
    }
    
    public void action(){
        
    }
    
    
}
