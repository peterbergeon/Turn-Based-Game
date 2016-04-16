
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
    
    public void changeType(String str){
        type = str;
    }
    
    public void addEnemy(Enemy e){
        thing = e;
    }
    
    public void addHero(Character h){
        hero = h;
    }
}
