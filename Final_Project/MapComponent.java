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
    private int move;
    private int attacks;
    private int aCount;
    private int items;
    private int iCount;
    private Tile[][] map;
    private int paint;
    private Character you;
    private int turn;
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
    private ArrayList<Character> swarm;
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
        move = you.getMove();
        attacks = 1;
        aCount = 1;
        items = 1;
        iCount = 1;
        swarm = new ArrayList<Character>();
        width = w;
        height = h;
        currentRow = w/2;
        currentCol = h/2;
        this.you = you;
        row = rows;
        col = cols;
        paint = 0;
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
            grass1 = ImageIO.read(new File("Grass.png"));
        } catch (IOException e) {
        }

        try {
            grass2 = ImageIO.read(new File("Grass.png"));
        } catch (IOException e) {
        }

        try {
            grass3 = ImageIO.read(new File("Grass.png"));
        } catch (IOException e) {
        }

        try {
            wood = ImageIO.read(new File("Floor.png"));
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
                double ran = Math.random() * 100;
                //                 if(r != row / 2 && c != col / 2 && ran < 1){
                //                     map[r][c].addCharacter(new Enemy());
                //                 }
            }
        }
    }

    public void createWall(){
        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                if(r < 21 || r > (row - 21) || (c < 21) || (c > col - 21)){
                    map[r][c] = new Tile(r,c, 100, 10000);
                    map[r][c].room();
                }
            }
        }
    }

    public void createEnemys(){
        double ran = Math.random();
        for(int i = 250; i < 350; i++){
            for(int k = 250; k < 350; k++){
                ran = Math.random();
                if(map[i][k].isRoom() && Math.random() < 0.02 && map[i][k].getColor() != 100 && i != currentRow && k != currentCol){
                    if(ran < 0.6){
                        swarm.add(map[i][k].addCharacter(new Character("Rat",3,40,5,1)));
                    }
                    else if(ran < 0.8){
                        swarm.add(map[i][k].addCharacter(new Character("GoblinArcher",2,20,5,3)));
                    }
                    else{
                        swarm.add(map[i][k].addCharacter(new Character("Bat",5,20,5,1)));
                    }
                }
                else if (Math.random() < 0.01 && map[i][k].getColor() != 100 && i != currentRow && k != currentCol){
                    if(ran < 0.6){
                        swarm.add(map[i][k].addCharacter(new Character("Rat",3,40,5,1)));
                    }
                    else if(ran < 0.8){
                        swarm.add(map[i][k].addCharacter(new Character("GoblinArcher",2,20,5,3)));
                    }
                    else{
                        swarm.add(map[i][k].addCharacter(new Character("Bat",5,20,5,1)));
                    }
                }
            }
        }
    }

    public void createRoom(){
        int randomL = 0;
        int randomW = 0;
        Room room = new Room(you.getMove() / 2);
        Room better = new Room(you.getMove() / 2,0);
        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                if(r % 50 == 10 && c % 50 == 10 && r < 500 && c < 500 && r > 100 && c > 100){
                    better.changeRoom(r,c,35,35,0);
                    for(int i = 0; i < 35; i++){
                        for(int k = 0; k < 35; k++){
                            map[r + i][c + k] = better.getTile(i,k);
                        }
                    }
                }
            }
        }
    }

    public void createRoad(){
        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                if(r % 50 < 5 || c % 50 < 5){
                    if(r % 50 == 2){
                        map[r][c] = new Tile(r, c, 1, 3);
                    }
                    else if(c % 50 == 2){
                        map[r][c] = new Tile(r, c, 2, 3);
                    }
                    else if(((r % 50 == 0 || r % 50 == 4) && (c % 50 > 3 || c % 50 == 0)) || ((c % 50 == 0 || c % 50 == 4) && (r % 50 > 3 || r % 50 == 0))){
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
        graphics2.setColor(Color.gray);
        graphics2.fillRect(width - 200, height - 100, 200, 100);
        graphics2.setColor(Color.white);
        graphics2.drawString("End Turn", width - 175,height - 70);  
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
        if(mouseX > width - 200 && mouseY > height - 100){
            endTurn();
        }
        else if(you.getHome().getRow() + xTile > -1 && you.getHome().getRow() + xTile < row && you.getHome().getCol() + yTile > -1 && 
        you.getHome().getCol() + yTile < col && map[you.getHome().getRow() + xTile][you.getHome().getCol() + yTile].getMoveable() && move > 0){
            move -= map[you.getHome().getRow() + xTile][you.getHome().getCol() + yTile].getDistance();
            move(you, map[you.getHome().getRow() + xTile][you.getHome().getCol() + yTile]);
            //endTurn();
        }
        fixDistance(you.getHome(), move);
    }

    //     public void dMove(Enemy e){
    //         int r = e.getHome().getRow();
    //         int c = e.getHome().getCol();
    //         if(e.getHome().getRow() < currentRow) r++;
    //         if(

    public void move(Character whoToMove, Tile whereToMove){
        whoToMove.getHome().removeCharacter();
        whereToMove.addCharacter(whoToMove);
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
        for(int w = 0; w < 20; w++){
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

    public void move(Character e){
        int m = e.getMove();
        Tile orgin = e.getHome();
        int original = e.getHome().getDistance() - 1000;
        Tile toMove = orgin;
        toMove.setDistance(10000000);
        int x = 3;
        double ran = Math.random();
        while(x > 0){
            for(int r = toMove.getRow() - 1; r <= toMove.getRow() + 1; r++){
                for(int c = toMove.getCol() - 1; c <= toMove.getCol() + 1; c++){
                    if(map[r][c].getDistance() < toMove.getDistance() && map[r][c].getDistance() + m > original && map[r][c].getCharacter() == null && map[r][c].getColor() != 100 && map[r][c].getDistance() > e.getRange() * 3){
                        toMove = map[r][c];
                    }
                    else if(map[r][c].getDistance() == toMove.getDistance() && map[r][c].getDistance() + m > original && ran < 0.7 && map[r][c].getCharacter() == null && map[r][c].getColor() != 100 && map[r][c].getDistance() > e.getRange() * 5){
                        toMove = map[r][c];
                        ran = Math.random();
                    }
                }
            }   
            x--;
        }
        move(e,toMove);
    }

    public void fly(Character e){
        int m = e.getMove();
        Tile orgin = e.getHome();
        Tile toMove = orgin;
        for(int i = orgin.getRow() - m; i < orgin.getRow() + m; i++){
            for(int k = orgin.getCol() - m; k < orgin.getCol() + m; k++){
                if(map[i][k].getDistance() < toMove.getDistance() && map[i][k].getCharacter() == null){
                    toMove = map[i][k];
                }
            }
        }
        move(e,toMove);
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
            other.setDistance(10000);
        }
    }

    public void spawn(){
        int d = turn;
        int howMany = (int)(Math.random() * (turn + 199)) / 100;
        for(int s = 0; s < howMany; s++){
            for(int r = currentRow - 50; r < currentRow + 50; r++){
                for(int c = currentCol - 50; r < currentCol + 50; c++){
                    //                             Enemy toAdd = new Enemy();
                    //                             map[r][c].addCharacter(toAdd);
                    //                             swarm.add(toAdd);
                    //                             r = -1;
                    //                             c = -1;
                }
            }
        }
    }

    public Tile adjacent(Tile current){
        int r = (int)(Math.random() * 8);
        if(r == 0){
            return map[current.getRow() + 1][current.getCol() + 1];
        }
        if(r == 1){
            return map[current.getRow() + 1][current.getCol()];
        }
        if(r == 2){
            return map[current.getRow() + 1][current.getCol() - 1];
        }
        if(r == 3){
            return map[current.getRow()][current.getCol() - 1];
        }
        if(r == 4){
            return map[current.getRow()][current.getCol() + 1];
        }
        if(r == 5){
            return map[current.getRow() - 1][current.getCol() + 1];
        }
        if(r == 6){
            return map[current.getRow() - 1][current.getCol()];
        }
        else {
            return map[current.getRow() - 1][current.getCol() - 1];
        }
    }

    public void D(int mouseX, int mouseY){
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
        Character toAdd = null;
        if(map[you.getHome().getRow() + xTile][you.getHome().getCol() + yTile].getCharacter() != null){
            toAdd = map[you.getHome().getRow() + xTile][you.getHome().getCol() + yTile].getCharacter();
        }
        map[you.getHome().getRow() + xTile][you.getHome().getCol() + yTile] = new Tile(you.getHome().getRow() + xTile, you.getHome().getCol() + yTile, 16, you.getMove() / 4);
        if(toAdd != null){
            map[you.getHome().getRow() + xTile][you.getHome().getCol() + yTile].addCharacter(toAdd);
        }
        fixDistance(you.getHome(),move);
    }

    public void endTurn(){
        move += (you.getMove() / 2);
        for(Character c : swarm){
            if(Math.abs(c.getHome().getCol() - you.getHome().getCol()) < 20 && Math.abs(c.getHome().getRow() - you.getHome().getRow()) < 20 && c.getHome().isRoom() == you.getHome().isRoom()){
                if(c.getType().equals("Bat")){
                    fly(c);
                }
                else{
                    move(c);
                }
            }
        }
        if( move > you.getMove()){
            move = you.getMove();
        }
        aCount = attacks;
        iCount = items;
        turn++;
        spawn();
        fixDistance(you.getHome(),move);
    }
}

