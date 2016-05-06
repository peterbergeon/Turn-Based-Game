import java.awt.Graphics2D;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
/**
 * Write a description of class Room here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Room
{
    private int row;
    private int col;
    private int width;
    private int height;
    private int CRIF; //current row in floor
    private int CCIF; //current collum in floor
    private int sizeW;
    private int sizeL;
    private Door leftDoor;
    private Door rightDoor;
    private Door botDoor;
    private Door topDoor;   
    private Tiles[][] room;
    private String roomType;
    BufferedImage img;
    private Tile currentTile;

    public Room(int w, int h, int r, int c){
        this(w,h,r,c,(int)(Math.random() * 5 + 7),(int)(Math.random() * 5 + 7),"Normal",null);
    }

    public Room(int w, int h, int r, int c, int width, int length, String roomType, Hero you){
        CRIF = r;
        CCIF = c;
        width = w;
        height = h;
        int sizeW = width + 2;
        int sizeL = height + 2;
        int top = (int)(Math.random() * height) + 1;
        int bot = (int)(Math.random() * height) + 1;
        int left = (int)(Math.random() * width) + 1;
        int right = (int)(Math.random() * width) + 1;
        this.roomType = roomType;
        String color = whatColor(r+c);
        try {
            img = ImageIO.read(new File(color + " Tile.png"));
        } catch (IOException e) {
        }
        for(int i = 0; i < sizeW; i++){
            for(int k = 0; k < sizeL; k++){
                if(k == 0 || i == 0){
                    try {
                        img = ImageIO.read(new File("Wall Tile.png"));
                    } catch (IOException e) {
                    }
                    room[i][k] = new Wall(w,h,i,k,roomType,img);
                }
                else if((i != top && i != bot) || k != 0 && ((k != left && k != right) || i != 0)){
                    room[i][k] = new Tiles(w,h,i,k,roomType, img);
                }
                else { 
                    try {
                        img = ImageIO.read(new File("Door Tile.png"));
                    } catch (IOException e) {
                    }
                    if(i == top && k == 0 && this.CRIF != 0){
                        topDoor = new Door(w,h,i,k,roomType,img);
                        room[i][k] = topDoor;
                    }
                    else if(i == bot && k == sizeL - 1 && this.CRIF != 14){
                        botDoor = new Door(w,h,i,k,roomType,img);
                        room[i][k] = botDoor;
                    }
                    else if(k == left && i == 0 && this.CCIF != 0){
                        leftDoor = new Door(w,h,i,k,roomType,img);
                        room[i][k] = leftDoor;
                    }
                    else if(k == right && i == sizeL - 1 && this.CCIF != 14){
                        rightDoor = new Door(w,h,i,k,roomType,img);
                        room[i][k] = rightDoor;
                    }
                }
            }
        }
        if(roomType.equals("Start")){
            room[0][0].addHero(you);
        }
    }

    public String whatColor(int t){
        if(t % 5 == 0){
            return "Red";
        }
        if(t % 5 == 1){
            return "Blue";
        }
        if(t % 5 == 2){
            return "Yellow";
        }
        if(t % 5 == 3){
            return "Purple";
        }
        if(t % 5 == 4){
            return "Green";
        }
        else return "WTF";
    }

    public void draw(Graphics2D graphics2){
        for(int i = 0; i < sizeW; i++){
            for(int k = 0; k < sizeL; k++){
                room[i][k].draw(graphics2);
            }
        }
    }
    
    public Tiles hasHero(){
        for(int i = 0; i < sizeW; i++){
            for(int k = 0; k < sizeL; k++){
                if(room[i][k].hasHero()){
                    return room[i][k];
                }
            }
        }
        return null;
    }    

    public int getWidth(){
        return sizeW;
    }

    public int getLength(){
        return sizeL;
    }
    
    public int getCRIF(){
        return CRIF;
    }
    
    public int getCCIF(){
        return CCIF;
    }

    public boolean isAdjacentAndOpen(Room other){
        if(other.CRIF - this.CRIF == 1 && botDoor.isOpen()) return true;
        if(other.CRIF - this.CRIF == -1 && topDoor.isOpen()) return true;
        if(other.CCIF - this.CCIF == 1 && rightDoor.isOpen()) return true;
        if(other.CCIF - this.CCIF == -1 && leftDoor.isOpen()) return true;
        return false;
    }
}
