
/**
 * Write a description of class Door here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wall extends Tiles
{
    private int CRIR; //current row in room
    private int CCIR; //current collum in room
    private String tileType;
    private Character thing;

    public Wall(int i,int k){  
        super(i,k,"Wall");
    }

    public String getTileType(){
        return "Wall";
    }

    public void action(){
        
    }
    
    public void endTurn(){

    }
}
