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
import java.awt.Color;
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
    private Character you;
    private BufferedImage road;
    private BufferedImage roadV;
    private BufferedImage roadH;
    private BufferedImage sideW;
    private BufferedImage grass1;
    private BufferedImage grass2;
    private BufferedImage grass3;
    private BufferedImage wood;
    private BufferedImage door;
    private BufferedImage mud;
    private ArrayList<Enemy> swarm;
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

    public MapComponent(int w, int h, int rows, int cols, Character you){
        map = new Tile[rows][cols];
        width = w;
        height = h;
        currentRow = w/2;
        currentCol = h/2;
        this.you = you;
        row = rows;
        col = cols;
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

        try {
            wood = ImageIO.read(new File("Wood.png"));
        } catch (IOException e) {
        }

        try {
            door = ImageIO.read(new File("Door.png"));
        } catch (IOException e) {
        }

        try {
            mud = ImageIO.read(new File("Mud.png"));
        } catch (IOException e) {
        }
    }

    public void expand(int m, Tile current, int type){
        int random = (int)(Math.random() * 4);
        int ran = (int)(Math.random() * 10 + 1);
        while((map[current.getRow() - 1][current.getCol()] == null || map[current.getRow() + 1][current.getCol()] == null || 
            map[current.getRow()][current.getCol() + 1] == null || map[current.getRow()][current.getCol() - 1] == null )&& m > 0) {
            if(random == 0){
                if(map[current.getRow() - 1][current.getCol()] == null){
                    map[current.getRow() - 1][current.getCol()] = new Tile(current.getRow() - 1, current.getCol(), type, 4);
                    expand(m - ran, map[current.getRow() - 1][current.getCol()], type);
                }
                else{random++;}
                if(random == 1){
                    if(map[current.getRow() + 1][current.getCol()] == null){
                        map[current.getRow() + 1][current.getCol()] = new Tile(current.getRow() + 1, current.getCol(), type, 4);
                        expand(m - ran, map[current.getRow() - 1][current.getCol()], type);
                    }
                    else{random++;}
                    if(random == 2){
                        if(map[current.getRow()][current.getCol() + 1] == null){
                            map[current.getRow()][current.getCol() + 1] = new Tile(current.getRow(), current.getCol() + 1, type, 4);
                            expand(m - ran, map[current.getRow()][current.getCol() + 1], type);
                        }
                        else{random++;}
                        if(random == 3){
                            if(map[current.getRow()][current.getCol() - 1] == null){
                                map[current.getRow()][current.getCol() - 1] = new Tile(current.getRow(), current.getCol() - 1, type, 4);
                                expand(m - ran, map[current.getRow()][current.getCol() - 1], type);
                            }
                            else{random = 0;}
                        }
                    }
                }
            }
        }
    }

    public void createMap(){
        int grass = 0;
        int randomRow = 0;
        int randomCol = 0;
        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                grass = (int)((Math.random() * 6) + 10);
                if(grass < 13){
                    map[r][c] = new Tile(r,c, grass, 4);
                }
                else{
                    map[r][c] = new Tile(r,c, 17, 7);
                }
            }
        }
    }

    public void createWall(){
        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                if(r < 21 || r > (row - 21) || (c < 21) || (c > col - 21)){
                    map[r][c] = new Tile(r,c, 100, 100);
                }
            }
        }
    }

    public void createRoom(){
        int randomL = 0;
        int randomW = 0;
        Room room = new Room("Simple House", you.getMove());
        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                if(r % 100 == 40 && c % 100 == 40){
                    randomL = (int)(Math.random() * 10 + 11);
                    randomW = (int)(Math.random() * 10 + 11);
                    room.changeRoom(r,c,randomW,randomL);
                    for(int i = 0; i < randomW; i++){
                        for(int k = 0; k < randomL; k++){
                            map[r + i][c + k] = room.getTile(i,k);
                        }
                    }
                }
            }
        }
    }

    public void createRoad(){
        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                if(r % 100 < 5 || c % 100 < 5){
                    if(r % 100 == 2){
                        map[r][c] = new Tile(r, c, 1, 3);
                    }
                    else if(c % 100 == 2){
                        map[r][c] = new Tile(r, c, 2, 3);
                    }
                    else if(((r % 100 == 0 || r % 100 == 4) && (c % 100 > 3 || c % 100 == 0)) || ((c % 100 == 0 || c % 100 == 4) && (r % 100 > 3 || r % 100 == 0))){
                        map[r][c] = new Tile(r, c, 3, 3);
                    }
                    else {
                        map[r][c] = new Tile(r, c, 0, 3);
                    }
                }
            }
        }
    }

    public void createCharacter(){
        map[row/2][col/2].addCharacter(you);
    }

    public boolean empty(){
        for(int i = 0; i < row; i++){
            for(int k = 0; k < col; k++){
                if(map[i][k] == null){
                    return true;
                }
            }
        }
        return false;
    }

    public void paintComponent(Graphics g) {
        Graphics2D graphics2 = (Graphics2D)g;
        if(map[100][100] == null){
            graphics2.setColor(Color.green);
            graphics2.drawRect(500, 500, 500, 40);
            graphics2.setColor(Color.red);
            graphics2.fillRect(501, 501, 498, 38);            
        }
        else{
            int rstart = you.getHome().getRow() - 20;
            if(rstart < 0) rstart = 0;
            int rend = you.getHome().getRow() + 20;
            if(rend > row) rend = row;
            int cstart = you.getHome().getCol() - 20;
            if(cstart < 0) cstart = 0;
            int cend = you.getHome().getCol() + 20;
            if(cend > col) cend = col;
            int x = 0;
            int y = 0;
            for(int r = rstart; r < rend; r++){
                for(int c = cstart; c < cend; c++){
                    x = you.getHome().getRow() - map[r][c].getRow();
                    y = you.getHome().getCol() - map[r][c].getCol();
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
                    else if(map[r][c].getColor() == 15){
                        map[r][c].draw(graphics2,wood,(width/2) - 30 - (60 * x), (height/2) - 30 - (60 * y));
                    }
                    else if(map[r][c].getColor() == 16){
                        map[r][c].draw(graphics2,door,(width/2) - 30 - (60 * x), (height/2) - 30 - (60 * y));
                    }
                    else if(map[r][c].getColor() == 17){
                        map[r][c].draw(graphics2,mud,(width/2) - 30 - (60 * x), (height/2) - 30 - (60 * y));
                    }
                    else{
                        map[r][c].draw(graphics2,wall,(width/2) - 30 - (60 * x), (height/2) - 30 - (60 * y));
                    }
                }
            }
        }
    }

    public Tile getCurrentTile(){
        return you.getHome();
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
        if(you.getHome().getRow() + xTile > -1 && you.getHome().getRow() + xTile < row && you.getHome().getCol() + yTile > -1 && 
        you.getHome().getCol() + yTile < col){
            move(you.getHome(), map[you.getHome().getRow() + xTile][you.getHome().getCol() + yTile], you.getMove());
        }
        fixDistance(you.getHome(), you.getMove());
    }

    public void move(Tile start, Tile end, int move){
        Character toMove = start.getCharacter();
        start.removeCharacter();
        fixDistance(start,move);
        if(end.getMoveable()){
            end.addCharacter(toMove);
        }
        else{
            Tile newEnd = closest(end);
            newEnd.addCharacter(toMove);
        }
        fixDistance(you.getHome(), you.getMove());
    }

    public Tile closest(Tile end){
        int istart = end.getRow() - 30;
        if(istart < 0) istart = 0;
        int iend = end.getRow() + 30;
        if(iend > row) iend = row;
        int kstart = end.getCol() - 30;
        if(kstart < 0) kstart = 0;
        int kend = end.getCol() + 30;
        if(kend > col) kend = col;
        for(int i = istart; i < iend; i++){
            for(int k = kstart; k < kend; k++){
                map[i][k].setDistance(1000);
            }
        }
        end.setDistance(0);
        for(int d = 1; d <= 20; d++){
            for(int r = end.getRow() - d; r <= end.getRow() + d; r++){
                for(int c = end.getCol() - d; c <= end.getCol() + d; c++){
                    if(r == end.getRow() + d || c == end.getCol() + d || r == end.getRow() - d || c == end.getCol() - d){
                        getDistance(map[r][c]);
                    }
                }
            }
        }
        Tile closest = map[0][0];
        for(int i = end.getRow() - 10; i <= end.getRow() + 10; i++){
            for(int k = end.getCol() - 10; k <= end.getCol() + 10; k++){
                if(map[i][k].getDistance() <= closest.getDistance() && map[i][k].getMoveable()){
                    closest = map[i][k];
                } 
            }
        }
        fixDistance(you.getHome(), you.getMove());
        return closest;
    }

    public void fixDistance(Tile orgin, int move){
        int istart = orgin.getRow() - 30;
        if(istart < 0) istart = 0;
        int iend = orgin.getRow() + 30;
        if(iend > row) iend = row;
        int kstart = orgin.getCol() - 30;
        if(kstart < 0) kstart = 0;
        int kend = orgin.getCol() + 30;
        if(kend > col) kend = col;

        for(int i = istart; i < iend; i++){
            for(int k = kstart; k < kend; k++){
                map[i][k].setDistance(1000);
            }
        }
        orgin.setDistance(0);
        for(int w = 0; w < 10; w++){
            for(int d = 1; d <= 20; d++){
                for(int r = orgin.getRow() - d; r <= orgin.getRow() + d; r++){
                    for(int c = orgin.getCol() - d; c <= orgin.getCol() + d; c++){
                        if(r == orgin.getRow() + d || c == orgin.getCol() + d || r == orgin.getRow() - d || c == orgin.getCol() - d){
                            getDistance(map[r][c]);
                        }
                        if(map[r][c].getDistance() <= move) map[r][c].isMoveable(true);
                        else map[r][c].isMoveable(false);
                    }
                }
            }
        }
    }

    public void getDistance(Tile other){
        if(map[other.getRow() + 1][other.getCol()].getDistance() + other.getTerrain() < other.getDistance()){
            other.setDistance(map[other.getRow() + 1][other.getCol()].getDistance() + other.getTerrain());
        }
        else if(map[other.getRow() - 1][other.getCol()].getDistance() + other.getTerrain() < other.getDistance()){
            other.setDistance(map[other.getRow() - 1][other.getCol()].getDistance() + other.getTerrain());
        }
        else if(map[other.getRow() + 1][other.getCol() + 1].getDistance() + other.getTerrain() < other.getDistance()){
            other.setDistance(map[other.getRow() + 1][other.getCol() + 1].getDistance() + other.getTerrain());
        }
        else if(map[other.getRow()][other.getCol() + 1].getDistance() + other.getTerrain() < other.getDistance()){
            other.setDistance(map[other.getRow()][other.getCol() + 1].getDistance() + other.getTerrain());
        }
        else if(map[other.getRow() - 1][other.getCol() + 1].getDistance() + other.getTerrain() < other.getDistance()){
            other.setDistance(map[other.getRow() - 1][other.getCol() + 1].getDistance() + other.getTerrain());
        }
        else if(map[other.getRow() + 1][other.getCol() - 1].getDistance() + other.getTerrain() < other.getDistance()){
            other.setDistance(map[other.getRow() + 1][other.getCol() - 1].getDistance() + other.getTerrain());
        }
        else if(map[other.getRow()][other.getCol() - 1].getDistance() + other.getTerrain() < other.getDistance()){
            other.setDistance(map[other.getRow()][other.getCol() - 1].getDistance() + other.getTerrain());
        }
        else if(map[other.getRow() - 1][other.getCol() - 1].getDistance() + other.getTerrain() < other.getDistance()){
            other.setDistance(map[other.getRow() - 1][other.getCol() - 1].getDistance() + other.getTerrain());
        }    
        else if(map[other.getRow() - 1][other.getCol() - 1].getDistance() + other.getTerrain() > other.getDistance() && 
        map[other.getRow() - 1][other.getCol()].getDistance() + other.getTerrain() > other.getDistance() && 
        map[other.getRow() + 1][other.getCol() + 1].getDistance() + other.getTerrain() > other.getDistance() && 
        map[other.getRow() + 1][other.getCol()].getDistance() + other.getTerrain() > other.getDistance() && 
        map[other.getRow() - 1][other.getCol() + 1].getDistance() + other.getTerrain() > other.getDistance() && 
        map[other.getRow()][other.getCol() + 1].getDistance() + other.getTerrain() > other.getDistance() && 
        map[other.getRow()][other.getCol() - 1].getDistance() + other.getTerrain() > other.getDistance() && 
        map[other.getRow() + 1][other.getCol() - 1].getDistance() + other.getTerrain() > other.getDistance()){
            other.setDistance(1000);
        }
    }

    public void spawn(){

    }

    public void endTurn(){

    }
}

