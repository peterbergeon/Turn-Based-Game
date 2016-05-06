import java.awt.Graphics2D;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
/**
 * Write a description of class Floor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Floor
{
    private Room[][] floor;
    private int row;
    private int col;
    private int level;
    private int centerX;
    private int centerY;
    private Room currentRoom;
    private Tiles currentTiles;
    private BufferedImage door;
    private BufferedImage wall;
    private BufferedImage tile;

    public Floor(int w, int h, int row, int col, int level, Hero you){
        centerX = w / 2 - 30;
        centerY = h / 2 - 30;
        this.row = row;
        this.col = col;
        this.level = level;
        floor = new Room[row][col];
        currentRoom = floor[0][0];
        try {
            door = ImageIO.read(new File("Door.png"));
        } catch (IOException e) {
        }
        try {
            wall = ImageIO.read(new File("Wall.png"));
        } catch (IOException e) {
        }
        for(int i = 0; i < row; i++){
            for (int k = 0; k < col; k++){
                floor[i][k] = new Room(i,k,you);
                floor[0][0] = new Room(0,0,5,5,"Start",you);
            }
        }
    }
    
    public Room getRoom(int r, int c){
        return floor[r][c];
    }

    public int getLevel(){
        return level;
    }

    public void changeCurrentTiles(Tiles t){
        currentTiles = t;        
    }

    public Room getCurrentRoom(){
        return currentRoom;
    }

    public void changeRoom(int row, int col){
        currentRoom = floor[row][col];
    }

    public void draw(Graphics2D graphics2){
        changeCurrentTiles(currentRoom.getCurrentTiles());
        Room topRoom = getRoom(currentRoom.getCRIF() - 1, currentRoom.getCCIF());
        Room botRoom = getRoom(currentRoom.getCRIF() + 1, currentRoom.getCCIF());
        Room leftRoom = getRoom(currentRoom.getCRIF(), currentRoom.getCCIF() - 1);
        Room rightRoom = getRoom(currentRoom.getCRIF(), currentRoom.getCCIF() + 1);
        int tilesHorizontal = 0;
        int tilesVertical = 0;
        int CRIR = currentTiles.getCRIR();
        int CCIR = currentTiles.getCCIR();
        if(currentRoom.getTopDoor().isOpen()){
            try {
                tile = ImageIO.read(new File(topRoom.whatColor(topRoom.getCRIF() + topRoom.getCCIF()) + " Tile.png"));
            } catch (IOException e) {
            }
            drawTopRoom(graphics2,tile);
        }
        if(currentRoom.getBotDoor().isOpen()){
            try {
                tile = ImageIO.read(new File(botRoom.whatColor(botRoom.getCRIF() + botRoom.getCCIF()) + " Tile.png"));
            } catch (IOException e) {
            }
            drawBotRoom(graphics2, tile);
        }
        if(currentRoom.getLeftDoor().isOpen()){
            try {
                tile = ImageIO.read(new File(leftRoom.whatColor(leftRoom.getCRIF() + leftRoom.getCCIF()) + " Tile.png"));
            } catch (IOException e) {
            }
            drawLeftRoom(graphics2,tile);                               
        }
        if(currentRoom.getRightDoor().isOpen()){
            try {
                tile = ImageIO.read(new File(rightRoom.whatColor(rightRoom.getCRIF() + rightRoom.getCCIF()) + " Tile.png"));
            } catch (IOException e) {
            }
            drawRightRoom(graphics2,tile);
        }       
        try {
            tile = ImageIO.read(new File(currentRoom.whatColor(currentRoom.getCRIF() + currentRoom.getCCIF()) + " Tile.png"));
        } catch (IOException e) {
        }
        for(int i = 0; i < currentRoom.getWidth(); i++){
            for(int k = 0; k < currentRoom.getHeight(); k++){
                if(i == CRIR && k == CCIR){
                    graphics2.drawImage(tile, centerX, centerY, null);
                }
                else {
                    tilesHorizontal = i - CCIR;
                    tilesVertical = k - CRIR;
                    graphics2.drawImage(tile, centerX + 60 * tilesHorizontal, centerY + 60 * tilesVertical, null);
                }
            }
        }
    }

    public void drawTopRoom(Graphics2D graphics2, BufferedImage tile){
        if(currentRoom.getCRIF() > 0){
            Room topRoom = floor[currentRoom.getCRIF() - 1][currentRoom.getCCIF()];
            int topLeftHorizontal = (currentRoom.getTopDoor().getCCIR() - currentTiles.getCCIR()) - topRoom.getBotDoor().getCCIR();//looks at how many spaces left of center is topRooms left edge, should always be negative
            int topLeftVertical = -1 * (currentRoom.getCurrentTiles().getCRIR() +  topRoom.getHeight() - 1);
            for(int i = 0; i < topRoom.getWidth(); i++){
                for(int k = 0; k < topRoom.getHeight(); k++){
                    if(topRoom.getTile(
                    graphics2.drawImage(tile, centerX + 60 * (topLeftHorizontal + i), centerY + 60 * (topLeftVertical + k),null);
                }
            }
        }
    }

    public void drawBotRoom(Graphics2D graphics2, BufferedImage tile){
        if(currentRoom.getCRIF() < 15){
            Room botRoom = floor[currentRoom.getCRIF() + 1][currentRoom.getCCIF()];
            int topLeftHorizontal = (currentRoom.getBotDoor().getCCIR() - currentTiles.getCCIR()) - botRoom.getTopDoor().getCCIR();//looks at how many spaces left of center is topRooms left edge, should always be negative
            int topLeftVertical = (currentRoom.getHeight() - currentTiles.getCRIR() - 1);
            for(int i = 0; i < botRoom.getWidth(); i++){
                for(int k = 0; k < botRoom.getHeight(); k++){
                    graphics2.drawImage(tile, centerX + 60 * (topLeftHorizontal + i), centerY + 60 * (topLeftVertical + k),null);
                }
            }
        }
    }

    public void drawLeftRoom(Graphics2D graphics2, BufferedImage tile){
        if(currentRoom.getCCIF() > 0){
            Room leftRoom = floor[currentRoom.getCRIF()][currentRoom.getCCIF() - 1];
            int topLeftHorizontal = -1 * (currentRoom.getCurrentTiles().getCCIR() + leftRoom.getWidth() - 1);
            int topLeftVertical = (currentRoom.getLeftDoor().getCRIR() - currentTiles.getCRIR()) - leftRoom.getRightDoor().getCRIR();
            for(int i = 0; i < leftRoom.getWidth(); i++){
                for(int k = 0; k < leftRoom.getHeight(); k++){
                    graphics2.drawImage(tile, centerX + 60 * (topLeftHorizontal + i), centerY + 60 * (topLeftVertical + k),null);
                }
            }
        }
    }

    public void drawRightRoom(Graphics2D graphics2,BufferedImage tile){
        if(currentRoom.getCCIF() < 15){
            Room rightRoom = floor[currentRoom.getCRIF()][currentRoom.getCCIF() + 1];
            int topLeftHorizontal = (currentRoom.getWidth() - currentRoom.getCurrentTiles().getCCIR() - 1);
            int topLeftVertical = ((currentRoom.getRightDoor().getCRIR() - currentTiles.getCRIR()) - rightRoom.getRightDoor().getCRIR() - 1);
            for(int i = 0; i < rightRoom.getWidth(); i++){
                for(int k = 0; k < rightRoom.getHeight(); k++){
                    graphics2.drawImage(tile, centerX + 60 * (topLeftHorizontal + i), centerY + 60 * (topLeftVertical + k),null);
                }
            }
        }
    }
}
