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
    private Enemy thing;
    private int terrain;
    private int fortification;
    private Color color;
    private Character hero;
    private int x;
    private int y;
    private final int width = 60;
    private final int height = 60;
    public Tile(int r, int b, int g, int x, int y){
        this(r,g,b,1,0,x,y);
    }
    
    public Tile(int r, int b, int g, int x, int y, String str){
        this(r,g,b,1,0,x,y);
    }

    public Tile(int r, int b, int g, int t, int x, int y){
        this(r,g,b,t,0,x,y);
    }

    public Tile(int r, int b, int g, int t, int f, int x, int y){
        color = new Color(r,g,b);
        terrain = 1;
        fortification = 0;
        this.x = x;
        this.y = y;
    }

    public Color getColor(){
        return color;
    }

    public int getFortification(){
        return fortification;
    }

    public int getTerrain(){
        return terrain;
    }

    public void changeColor(int r, int b, int g){
        color = new Color(r,g,b);
    }

    public void changeFortification(int f){
        fortification = f;
    }

    public void changeTerrain(int t){
        terrain = t;
    }

    public void addEnemy(Enemy e){
        thing = e;
    }

    public void addHero(Character h){
        hero = h;
    }

    public Character getHero(){
        return hero;
    }

    public void draw(Graphics2D graphics2){
        graphics2.setColor(color);
        graphics2.fillRect(x, y, width, height);
        if(hero != null){
            hero.draw(graphics2,x,y);
        }

    }

    public void shift(int dx, int dy){
        x -= dx * 60;
        y -= dy * 60;
    }

}

