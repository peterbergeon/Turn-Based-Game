import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
/**
 * Write a description of class Wall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wall extends Tiles
{
    private int CRIR; //current row in room
    private int CCIR; //current collum in room
    private int width;
    private int height;
    private String roomType;
    private String tileType;
    BufferedImage img;
    private int x;
    private int y;
    
    public Wall(int i,int k,String roomType,BufferedImage img2){
        super(i,k,roomType,img2);        
        tileType = "Wall";
    }
    
    public String getTileType(){
        return "Wall";
    }
    
    public void action(){
        
    }
}
