import java.awt.Graphics;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.Color;
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
    private BufferedImage greenTile;
    private BufferedImage purpleTile;
    private BufferedImage blueTile;
    private BufferedImage redTile;
    private int row;
    private int col;

    public MapComponent(int w, int h, int rows, int cols){
        width = w;
        height = h;
        row = rows;
        col = cols;
        map = new Tile[row][col];
        currentCol = col / 2;
        currentRow = row / 2;
        try {
            greenTile = ImageIO.read(new File("Green Tile.png"));
        } catch (IOException e) {
        }

        try {
            blueTile = ImageIO.read(new File("Blue Tile.png"));
        } catch (IOException e) {
        }

        try {
            redTile = ImageIO.read(new File("Red Tile.png"));
        } catch (IOException e) {
        }

        try {
            purpleTile = ImageIO.read(new File("Purple Tile.png"));
        } catch (IOException e) {
        }

        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){ 
                int randomNum = (int)(Math.random() * 10) + 20;
                int passable = (int)(Math.random() * 10);
                if(r == row / 2 && c == col / 2){
                    map[r][c] = currentTile = new Tile(r, c, 60);
                    currentTile.addCharacter(new Character("JOAT"));
                }
                else if(r % 50 < 3 || c % 50 < 3){
                    map[r][c] = new Tile(r, c, 60);
                }
                else if(passable == 0){
                    map[r][c] = new Tile(r, c, 1000);
                }
                else{
                    map[r][c] = new Tile(r,c,randomNum);
                }
            }
        }

    }

    public void paintComponent(Graphics g) {
        Graphics2D graphics2 = (Graphics2D)g;
        int rstart = currentTile.getRow() - 15;
        if(rstart < 0) rstart = 0;
        int rend = currentTile.getRow() + 15;
        if(rend > row) rend = row;
        int cstart = currentTile.getCol() - 15;
        if(cstart < 0) cstart = 0;
        int cend = currentTile.getCol() + 15;
        if(cend > col) cend = col;

        int x = 0;
        int y = 0;
        for(int r = rstart; r < rend; r++){
            for(int c = cstart; c < cend; c++){
                x = currentTile.getRow() - map[r][c].getRow();
                y = currentTile.getCol() - map[r][c].getCol();
                if(map[r][c].getColor() == 60){
                    map[r][c].draw(graphics2,purpleTile,(width/2) - 30 - (60 * x), (height/2) - 30 - (60 * y));
                }
                else if(map[r][c].getColor() >= 1000){
                    map[r][c].draw(graphics2,blueTile,(width/2) - 30 - (60 * x), (height/2) - 30 - (60 * y));
                }                   
                else{
                    map[r][c].draw(graphics2,greenTile,(width/2) - 30 - (60 * x), (height/2) - 30 - (60 * y));
                }
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
        if(currentTile.getRow() + xTile > -1 && currentTile.getRow() + xTile < row && currentTile.getCol() + yTile > -1 && 
        currentTile.getCol() + yTile < col && map[currentTile.getRow() + xTile][currentTile.getCol() + yTile].getColor() < 1000){
            Character hero = currentTile.getCharacter();
            currentTile.addCharacter(null);
            currentTile = map[currentTile.getRow() + xTile][currentTile.getCol() + yTile];
            currentTile.addCharacter(hero);
        }
    }
}
