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
    private BufferedImage road;
    private BufferedImage roadV;
    private BufferedImage roadH;
    private BufferedImage sideW;
    private BufferedImage grass1;
    private BufferedImage grass2;
    private BufferedImage grass3;
    //     private BufferedImage sideWH;
    //     private BufferedImage sideWV;
    //     private BufferedImage sideWLtT;
    //     private BufferedImage sideWRtT;
    //     private BufferedImage sideWLtD;
    //     private BufferedImage sideWRtD;
    //     private BufferedImage sideW;
    private BufferedImage wall;
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
            wall = ImageIO.read(new File("Wall.png"));
        } catch (IOException e) {
        }

        try {
            road = ImageIO.read(new File("Road.png"));
        } catch (IOException e) {
        }

        try {
            roadV = ImageIO.read(new File("RoadV.png"));
        } catch (IOException e) {
        }

        try {
            roadH = ImageIO.read(new File("RoadH.png"));
        } catch (IOException e) {
        }

        try {
            sideW = ImageIO.read(new File("SideWalk4.png"));
        } catch (IOException e) {
        }

        try {
            grass1 = ImageIO.read(new File("Grass1.png"));
        } catch (IOException e) {
        }

        try {
            grass2 = ImageIO.read(new File("Grass2.png"));
        } catch (IOException e) {
        }

        try {
            grass3 = ImageIO.read(new File("Grass3.png"));
        } catch (IOException e) {
        }
        //         try {
        //             sideWH = ImageIO.read(new File("SideWalkH.png"));
        //         } catch (IOException e) {
        //         }
        // 
        //         try {
        //             sideWRtT = ImageIO.read(new File("SideWalkRtD.png"));
        //         } catch (IOException e) {
        //         }
        // 
        //         try {
        //             sideWRtD = ImageIO.read(new File("SideWalkRtB.png"));
        //         } catch (IOException e) {
        //         }
        // 
        //         try {
        //             sideWLtT = ImageIO.read(new File("SideWalkLtT.png"));
        //         } catch (IOException e) {
        //         }
        // 
        //         try {
        //             sideWLtD = ImageIO.read(new File("SideWalkLtD.png"));
        //         } catch (IOException e) {
        //         }
        int grass = 0;
        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){ 
                int randomNum = (int)(Math.random() * 2) + 1;
                int passable = (int)(Math.random() * 10);
                if(r < 20 || r > row - 20 || c < 20 || c > col - 20){
                    map[r][c] = new Tile(r,c, 100, 100);
                }
                else if(r % 50 < 5 || c % 50 < 5){
                    if(r % 50 == 2){
                        map[r][c] = new Tile(r, c, 1, 1);
                    }
                    else if(c % 50 == 2){
                        map[r][c] = new Tile(r, c, 2, 1);
                    }
                    else if(((r % 50 == 0 || r % 50 == 4) && (c % 50 > 3 || c % 50 == 0)) || ((c % 50 == 0 || c % 50 == 4) && (r % 50 > 3 || r % 50 == 0))){
                        map[r][c] = new Tile(r, c, 3, 1);
                    }
                    else {
                        map[r][c] = new Tile(r, c, 0, 1);
                    }
                }
                else{
                    grass = (int)((Math.random() * 3) + 10);
                    map[r][c] = new Tile(r,c, grass, 2);
                }

                if(r == row / 2 && c == col / 2){
                    currentTile = map[r][c];
                    currentTile.addCharacter(new Character("JOAT"));
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
        fixDistance();
        for(int r = rstart; r < rend; r++){
            for(int c = cstart; c < cend; c++){
                x = currentTile.getRow() - map[r][c].getRow();
                y = currentTile.getCol() - map[r][c].getCol();
                if(map[r][c].getColor() == 0){
                    map[r][c].draw(graphics2,road,(width/2) - 30 - (60 * x), (height/2) - 30 - (60 * y));
                }
                else if(map[r][c].getColor() == 1){
                    map[r][c].draw(graphics2,roadV,(width/2) - 30 - (60 * x), (height/2) - 30 - (60 * y));
                }
                else if(map[r][c].getColor() == 2){
                    map[r][c].draw(graphics2,roadH,(width/2) - 30 - (60 * x), (height/2) - 30 - (60 * y));
                }
                else if(map[r][c].getColor() == 3){
                    map[r][c].draw(graphics2,sideW,(width/2) - 30 - (60 * x), (height/2) - 30 - (60 * y));
                }              
                else if(map[r][c].getColor() == 10){
                    map[r][c].draw(graphics2,grass1,(width/2) - 30 - (60 * x), (height/2) - 30 - (60 * y));
                }
                else if(map[r][c].getColor() == 11){
                    map[r][c].draw(graphics2,grass2,(width/2) - 30 - (60 * x), (height/2) - 30 - (60 * y));
                }
                else if(map[r][c].getColor() == 12){
                    map[r][c].draw(graphics2,grass3,(width/2) - 30 - (60 * x), (height/2) - 30 - (60 * y));
                }
                //                 else if(map[r][c].getColor() == 4){
                //                     map[r][c].draw(graphics2,sideWH,(width/2) - 30 - (60 * x), (height/2) - 30 - (60 * y));
                //                 }
                //                 else if(map[r][c].getColor() == 5){
                //                     map[r][c].draw(graphics2,sideWRtT,(width/2) - 30 - (60 * x), (height/2) - 30 - (60 * y));
                //                 }
                //                 else if(map[r][c].getColor() == 6){
                //                     map[r][c].draw(graphics2,sideWRtD,(width/2) - 30 - (60 * x), (height/2) - 30 - (60 * y));
                //                 }
                //                 else if(map[r][c].getColor() == 7){
                //                     map[r][c].draw(graphics2,sideWLtT,(width/2) - 30 - (60 * x), (height/2) - 30 - (60 * y));
                //                 }
                //                 else if(map[r][c].getColor() == 8){
                //                     map[r][c].draw(graphics2,sideWLtD,(width/2) - 30 - (60 * x), (height/2) - 30 - (60 * y));
                //                 }
                else{
                    map[r][c].draw(graphics2,wall,(width/2) - 30 - (60 * x), (height/2) - 30 - (60 * y));
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
        currentTile.getCol() + yTile < col && map[currentTile.getRow() + xTile][currentTile.getCol() + yTile].getMoveable()){
            Character hero = currentTile.getCharacter();
            currentTile.removeCharacter();
            currentTile = map[currentTile.getRow() + xTile][currentTile.getCol() + yTile];
            currentTile.addCharacter(hero);
        }
    }

    public void fixDistance(){
        int move = currentTile.getCharacter().getMove();
        int istart = currentTile.getRow() - 15;
        if(istart < 0) istart = 0;
        int iend = currentTile.getRow() + 15;
        if(iend > row) iend = row;
        int kstart = currentTile.getCol() - 15;
        if(kstart < 0) kstart = 0;
        int kend = currentTile.getCol() + 15;
        if(kend > col) kend = col;

        for(int i = istart; i < iend; i++){
            for(int k = kstart; k < kend; k++){
                map[i][k].setDistance(100);
            }
        }
        currentTile.setDistance(0);
        for(int d = 1; d <= move / 2; d++){
            for(int r = currentTile.getRow() - d; r <= currentTile.getRow() + d; r++){
                for(int c = currentTile.getCol() - d; c <= currentTile.getCol() + d; c++){
                    if(r == currentTile.getRow() - d){
                        getDistance("top",map[r][c]);
                    }
                    else if(r == currentTile.getRow() + d){
                        getDistance("bot",map[r][c]);
                    }
                    else if(c == currentTile.getCol() - d){
                        getDistance("left",map[r][c]);
                    }
                    else if(c == currentTile.getCol() + d){
                        getDistance("right",map[r][c]);
                    }
                    if(map[r][c].getDistance() <= move) map[r][c].isMoveable(true);
                    else map[r][c].isMoveable(false);
                }
            }
        }
        for(int d = 1; d <= move; d++){
            for(int r = currentTile.getRow() - d; r <= currentTile.getRow() + d; r++){
                for(int c = currentTile.getCol() - d; c <= currentTile.getCol() + d; c++){
                    if(r == currentTile.getRow() - d){
                        getDistance("top",map[r][c]);
                    }
                    else if(r == currentTile.getRow() + d){
                        getDistance("bot",map[r][c]);
                    }
                    else if(c == currentTile.getCol() - d){
                        getDistance("left",map[r][c]);
                    }
                    else if(c == currentTile.getCol() + d){
                        getDistance("right",map[r][c]);
                    }
                    if(map[r][c].getDistance() <= move) map[r][c].isMoveable(true);
                    else map[r][c].isMoveable(false);
                }
            }
        }
        currentTile.setDistance(100);
    }

    public void getDistance(String str, Tile other){
        if(str.equals("top")){
            if(map[other.getRow() + 1][other.getCol() + 1].getDistance() + other.getTerrain() < other.getDistance()){
                other.setDistance(map[other.getRow() + 1][other.getCol() + 1].getDistance() + other.getTerrain());
            }
            if(map[other.getRow() + 1][other.getCol()].getDistance() + other.getTerrain() < other.getDistance()){
                other.setDistance(map[other.getRow() + 1][other.getCol()].getDistance() + other.getTerrain());
            }
            if(map[other.getRow() + 1][other.getCol() - 1].getDistance() + other.getTerrain() < other.getDistance()){
                other.setDistance(map[other.getRow() + 1][other.getCol() - 1].getDistance() + other.getTerrain());
            }
        }
        else if(str.equals("bot")){
            if(map[other.getRow() - 1][other.getCol() + 1].getDistance() + other.getTerrain() < other.getDistance()){
                other.setDistance(map[other.getRow() - 1][other.getCol() + 1].getDistance() + other.getTerrain());
            }
            if(map[other.getRow() - 1][other.getCol()].getDistance() + other.getTerrain() < other.getDistance()){
                other.setDistance(map[other.getRow() - 1][other.getCol()].getDistance() + other.getTerrain());
            }
            if(map[other.getRow() - 1][other.getCol() - 1].getDistance() + other.getTerrain() < other.getDistance()){
                other.setDistance(map[other.getRow() - 1][other.getCol() - 1].getDistance() + other.getTerrain());
            }
        }
        else if(str.equals("left")){
            if(map[other.getRow() + 1][other.getCol() + 1].getDistance() + other.getTerrain() < other.getDistance()){
                other.setDistance(map[other.getRow() + 1][other.getCol() + 1].getDistance() + other.getTerrain());
            }
            if(map[other.getRow()][other.getCol() + 1].getDistance() + other.getTerrain() < other.getDistance()){
                other.setDistance(map[other.getRow()][other.getCol() + 1].getDistance() + other.getTerrain());
            }
            if(map[other.getRow() - 1][other.getCol() + 1].getDistance() + other.getTerrain() < other.getDistance()){
                other.setDistance(map[other.getRow() - 1][other.getCol() + 1].getDistance() + other.getTerrain());
            }
        }
        else if(str.equals("right")){
            if(map[other.getRow() + 1][other.getCol() - 1].getDistance() + other.getTerrain() < other.getDistance()){
                other.setDistance(map[other.getRow() + 1][other.getCol() - 1].getDistance() + other.getTerrain());
            }
            if(map[other.getRow()][other.getCol() - 1].getDistance() + other.getTerrain() < other.getDistance()){
                other.setDistance(map[other.getRow()][other.getCol() - 1].getDistance() + other.getTerrain());
            }
            if(map[other.getRow() - 1][other.getCol() - 1].getDistance() + other.getTerrain() < other.getDistance()){
                other.setDistance(map[other.getRow() - 1][other.getCol() - 1].getDistance() + other.getTerrain());
            }
        }
    }
}
