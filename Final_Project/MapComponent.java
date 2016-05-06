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
public class MapComponent extends JComponent
{
    private int width;
    private int height;
    private int currentRow;
    private int currentCol;
    private Tile[][] map;
    private Tile currentTile;

    public MapComponent(int w, int h){
        width = w;
        height = h;
        map = new Tile[51][51];
        int randomGreen = 0;
        int randomRed = 0;
        int randomBlue = 0;
        currentCol = 25;
        currentRow = 25;
        for(int row = 0; row < 51; row++){
            for(int col = 0; col < 51; col++){
                randomGreen = (int)(Math.random() * 255 + 1);
                randomBlue = (int)(Math.random() * 255 + 1);
                randomRed = (int)(Math.random() * 255 + 1);

                if(row == 25 && col == 25){
                    map[row][col] = currentTile = new Tile(255 - 2 * (row + col),255 - 2 * (row + col),255 - 2 * (row + col),(width/2) - 30, (height/2) - 30);
                    currentTile.addHero(new Character());
                }
                else{

                    map[row][col] = new Tile(255 - 2 * (row + col),255 - 2 * (row + col),255 - 2 * (row + col),
                        ((width/2) + 60 * (row - 25) - 30), ((height/2) + 60 * (col - 25) - 30));
                }
            }
        }

    }

    public void paintComponent(Graphics g) {
        Graphics2D graphics2 = (Graphics2D)g;
        for(int row = 0; row < 50; row++){
            for(int col = 0; col < 50; col++){
                map[row][col].draw(graphics2);
            }
        }
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

        if(currentRow + xTile > -1 && currentRow + xTile < 50 && currentCol + yTile > -1 && currentCol + yTile < 50){            
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
