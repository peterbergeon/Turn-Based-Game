import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.Graphics2D;
/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Character
{
    private int hp;
    private int dmg;
    private int mv;
    BufferedImage img;
    public Character(String str){
        hp = 100;
        dmg = 10;
        mv = 6;
        try {
            img = ImageIO.read(new File(str + ".png"));
        } catch (IOException e) {
        }
    }

    public void draw(Graphics2D g, int x, int y){
        g.drawImage(img,x,y,null);
    }
}
