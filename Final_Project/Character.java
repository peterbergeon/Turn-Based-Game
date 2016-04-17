
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
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
        Ellipse2D.Double circle = new Ellipse2D.Double(x + 5, y + 5, 50, 50);
        g.setColor(Color.ORANGE);
        g.fill(circle);
    }
}
