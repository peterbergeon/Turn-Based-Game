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
    private int maxhp;
    private int dmg;
    private int mv;
    private int range;
    String type;
    BufferedImage img;
    private Tile home;
    public Character(String str, int move){
        home = null;
        hp = 100;
        type = str;
        range = 1;
        dmg = 10;
        mv = move * 6;
        try {
            img = ImageIO.read(new File(str + ".png"));
        } catch (IOException e) {
        }
    }

    public Character(String str, int move, int health, int damage, int r){
        maxhp = health;
        hp = maxhp;
        type = str;
        dmg = damage;
        mv = move * 6;
        range = r;
        try {
            img = ImageIO.read(new File(str + ".png"));
        } catch (IOException e) {
        }
    }
    
    public int getRange(){
        return range;
    }
    
    public String getType(){
        return type;
    }

    public void setHome(Tile h){
        home = h;
    }

    public Tile getHome(){
        return home;
    }

    public int getMove(){
        return mv;
    }

    public void attack(Character c){
        c.damage(dmg);
    }

    public void damage(int x){
        hp -= x;
    }

    public int getHp(){
        return hp;
    }

    public void draw(Graphics2D g, int x, int y){
        g.drawImage(img,x,y,null);
    }

}
