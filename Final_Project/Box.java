
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
    public Box(){
        terrain = 1;
        fortification = 0;
        red = 255;
        blue = 255;
        green = 255;
        type = "normal";
        thing = null;
    }
    
}
