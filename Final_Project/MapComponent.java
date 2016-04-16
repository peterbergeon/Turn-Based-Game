import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JComponent;
/**
 * Write a description of class MapComponent here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MapComponent
{
    private int width;
    private int height;
    private Box[][] map;
    private Box currentBox;
    
    public MapComponent(int w, int h){
        width = w;
        height = h;
        map = new Box[50][50];
        
}
