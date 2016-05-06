
/**
 * Write a description of class Tile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tile
{
    private int terrain;
    private int fortification;
<<<<<<< HEAD:Final_Project/Tile.java
    private Color color;
    private Hero hero;
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
=======
    private int red;
    private int blue;
    private int green;
    private String type;
    private Character hero;
    public Box(){
        terrain = 1;
        fortification = 0;
        red = 255;
        blue = 255;
        green = 255;
        type = "normal";
        thing = null;
        hero = null;
    }

    public Box(int r, int b, int g){
        terrain = 1;
        fortification = 0;
        red = r;
        blue = b;
        green = g;
        type = "normal";
        thing = null;
        hero = null;
    }

    public Box(int r, int b, int g, int t){
        terrain = t;
        fortification = 0;
        red = r;
        blue = b;
        green = g;
        type = "normal";
        thing = null;
        hero = null;
    }

    public Box(int r, int b, int g, int t, int f){
        terrain = t;
        fortification = f;
        red = r;
        blue = b;
        green = g;
        type = "normal";
        thing = null;
        hero = null;
>>>>>>> parent of 14d4e7d... Version 0.1:Final_Project/Box.java
    }

    public Box(int r, int b, int g, int t, int f,String str){
        terrain = t;
        fortification = f;
        red = r;
        blue = b;
        green = g;
        type = str;
        thing = null;
        hero = null;
    }
    
    public int getRed(){
        return red;
    }
    
    public int getBlue(){
        return blue;
    }
    
    public int getGreen(){
        return green;
    }
    
    public int getFortification(){
        return fortification;
    }
    
    public int getTerrain(){
        return terrain;
    }
    
    public void changeColor(int r, int b, int g){
        red = r;
        blue = b;
        green = g;
    }
    
    public void changeFortification(int f){
        fortification = f;
    }
    
    public void changeTerrain(int t){
        terrain = t;
    }
<<<<<<< HEAD:Final_Project/Tile.java

    public void addHero(Hero h){
        hero = h;
    }

    public Hero getHero(){
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
=======
    
    public void changeType(String str){
        type = str;
    }
    
    public void addEnemy(Enemy e){
        thing = e;
    }
    
    public void addHero(Character h){
        hero = h;
    }
>>>>>>> parent of 14d4e7d... Version 0.1:Final_Project/Box.java
}
