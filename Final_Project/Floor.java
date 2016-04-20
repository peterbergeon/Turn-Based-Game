import java.awt.Graphics2D;
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
    private int height;
    private int width;
    private int level;
    private Room currentRoom;

    public Floor(int width, int height, int row, int col, int level, Hero you){
        this.row = row;
        this.col = col;
        this.height = height;
        this.width = width;
        this.level = level;
        floor = new Room[row][col];
        currentRoom = floor[0][0];
        for(int i = 0; i < row; i++){
            for (int k = 0; k < col; k++){
                floor[i][k] = new Room(width,height,i,k);
                floor[0][0] = new Room(width,height,0,0,5,5,"Start",you);
            }
        }
    }

    public void draw(Graphics2D graphics2){

        Draw it = new Draw(this,this.width,this.height);
        it.draw(graphics2);
    }
    
    public Room getRoom(){
        return currentRoom;
    }
    
    public void changeRoom(int row, int col){
        currentRoom = floor[row][col];
    }
}
