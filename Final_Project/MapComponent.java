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
        int randomGreen = 0;
        int randomRed = 0;
        int randomBlue = 0;
        for(int row = 0; row < 50; row++){
            for(int col = 0; col < 50; col++){
                randomGreen = (int)(Math.random() * 255 + 1);
                randomBlue = (int)(Math.random() * 255 + 1);
                randomRed = (int)(Math.random() * 255 + 1);
                map[row][col] = new Box(randomRed,randomBlue,randomGreen);
            }
        }
        
        currentBox = map[(int)(Math.random() * 50)][(int)(Math.random() * 50)];
    }
    
     public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        nextSection();
        currentBox.draw(g2);
    }
    
     public void updateMouse(int mouseX, int mouseY) {
        currentSection.updateMouse(mouseX, mouseY);
    }

    public void click() {
        currentSection.click();
    }
}
