import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
/**
 * Write a description of class Hero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hero extends Character
{
    private int level;
    private int hp;
    private int mp;
    private int atk;
    private int df;
    BufferedImage img;
    String type; 
    boolean isHero;

    public Hero(int l, int h, int m, int a, int d, String t){
        super(l,h,m,a,d,true);
        type = t;
        try {
            img = ImageIO.read(new File(t+ ".png"));
        } catch (IOException e) {
        }
    }

    public void draw(Graphics2D g, int x, int y){
        g.drawImage(img,x,y,null);
    }
}
