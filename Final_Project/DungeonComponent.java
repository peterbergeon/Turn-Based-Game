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
    private int level;
    private Hero you;
    public DungeonComponent(int w, int h, int d1, int d2, int d3, Hero you){
        this.you = you;
        width = w;
        height = h;
        level = d3;
        dungeon = new Floor[d3];
        for(int i = 0; i < d3; i++){
            dungeon[i] = new Floor(w, h, d1,d2,i,you);
        }
        currentFloor = dungeon[0];
    }

    public void paintComponent(Graphics g) {
        Graphics2D graphics2 = (Graphics2D)g;
        currentFloor.draw(graphics2);        
    }

    public Floor getFloor(){
        return currentFloor;
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
        Tiles currentTile = currentFloor.getCurrentRoom().getCurrentTiles();
        if(currentTile.getCRIR() + xTile > -1 && currentTile.getCRIR() + xTile < currentFloor.getCurrentRoom().getWidth() && 
        currentTile.getCCIR() + yTile > -1 && currentTile.getCCIR() + yTile <  currentFloor.getCurrentRoom().getHeight() && 
        !currentFloor.getCurrentRoom().getRoom()[currentTile.getCRIR() + xTile][currentTile.getCCIR() + yTile].getType().equals("Wall")){            
            Character hero = currentTile.getCharacter();
            currentTile.addHero(null);
            currentTile = currentFloor.getCurrentRoom().getRoom()[(currentTile.getCRIR() + xTile)][(currentTile.getCCIR() + yTile)];
            currentTile.addHero(you);
        }
        
        
    }
}

