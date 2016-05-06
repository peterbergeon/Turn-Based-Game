import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JComponent;
/**
 * Write a description of class Dungeon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DungeonComponent extends JComponent
{
    private Floor[] dungeon;
    private Floor currentFloor;
    private int height;
    private int width;
    public DungeonComponent(int w, int h, int d1, int d2, int d3){
        currentFloor = dungeon[0];
        width = w;
        height = h;
        floor = new Floor[d3];
        for(int i = 0; i < d3; i++){
            dungeon[i] = new Floor(w,h,d1,d2,i);
        }
    }

    public void paintComponent(Graphics g) {
        Graphics2D graphics2 = (Graphics2D)g;
        for(int i = 0; i < d3; i++){
            if(i == currentFloor){
                dungeon.draw(graphics2);
            }
        }
    }
    
    public Floor getFloor(){
        return currentFloor();
    }
    
    public void changeFloor(int n){
        currentFloor = dungeon[n];
    }

    public void click(int mouseX, int mouseY){
        int xCenter = (width/2) - 30;
        int yCenter = (height/2) - 30;
        int xShift = mouseX - xCenter;
        int yShift = mouseY - yCenter;
        int xTile = 0;
        int yTile = 0;
        if(xShift < 0){
            xTile = (int)((xShift - 60) / 60);
        }
        else if(xShift >= 0){
            xTile = (int)((xShift) / 60);
        }
        if(yShift < 0){
            yTile = (int)((yShift - 60) / 60);
        }
        else if(yShift >= 0){
            yTile = (int)((yShift) / 60);
        }

        if(currentRow + xTile > -1 && currentRow + xTile < 50 && currentCol + yTile > -1 && currentCol + yTile < 50 && ){            
            for(int row = 0; row < 50; row++){
                for(int col = 0; col < 50; col++){
                    map[row][col].shift(xTile,yTile);
                }
            }
            Character hero = currentTile.getHero();
            currentTile.addHero(null);
            currentTile = map[currentRow += xTile][currentCol += yTile];
            currentTile.addHero(hero);
        }
    }
}


