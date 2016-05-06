<<<<<<< HEAD
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
=======

>>>>>>> parent of 14d4e7d... Version 0.1
/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Character
{
    private int level;
    private int hp;
    private int mp;
    private int atk;
    private int df;
<<<<<<< HEAD
    boolean isHero;
    BufferedImage img;
    public Character(int l, int h, int m, int a, int d, boolean isHero){
        level = l;
        hp = h;
        mp = m;
        atk = a;
        df = d;
        img = null;
        this.isHero = isHero;
    }
    
    public boolean hasHero(){
        return isHero;
    }

    public void draw(Graphics2D g, int x, int y){
        g.drawImage(img,x,y,null);
=======
    
    public Character(){
        level = 1;
        race = "human";
        profession = "warrior";
        hp = 100;
        mp = 20;
        atk = 10;
        df = 0;
>>>>>>> parent of 14d4e7d... Version 0.1
    }
}
