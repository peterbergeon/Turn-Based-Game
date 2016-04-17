import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Write a description of class Box here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Box
{
    private Enemy thing;
    private int terrain;
    private int fortification;
    private Color color;
    private String type;
    private Character hero;
    private int x;
    private int y;
    private final int width = 60;
    private final int height = 60;
        public Box(int r, int b, int g, int x, int y){
        this(r,g,b,1,0,"normal",x,y);
    }

    public Box(int r, int b, int g, int t, int x, int y){
        this(r,g,b,t,0,"normal",x,y);
    }

    public Box(int r, int b, int g, int t, int f, int x, int y){
        this(r,g,b,t,f,"normal",x,y);
    }

    public Box(int r, int b, int g, int t, int f,String str, int x, int y){
        terrain = t;
        fortification = f;
        color = new Color(r,g,b);
        type = str;
        thing = null;
        hero = null;
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

    public void changeType(String str){
        type = str;
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

