import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics2D;
/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Character
{
    private int level;
    private String race;
    private String profession;
    private int hp;
    private int mp;
    private int atk;
    private int df;
    BufferedImage img;
    public Character(){
        level = 1;
        race = "human";
        profession = "warrior";
        hp = 100;
        mp = 20;
        atk = 10;
        df = 0;

    }

    public void draw(Graphics2D g, int x, int y){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("hero.png"));
        } catch (IOException e) {
        }
        g.drawImage(img,x,y,null);
    }
}
