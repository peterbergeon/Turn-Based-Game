
/**
 * Write a description of class Door here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Chest extends Tiles
{
    private int CRIR; //current row in room
    private int CCIR; //current collum in room
    private String tileType;
    private Character thing;
    private boolean open;

    public Chest(int i,int k){  
        super(i,k,"Chest");
        open = false;
    }

    public String getTileType(){
        return "Chest";
    }

    public boolean isOpen(){
        return open;
    }

    public void action(){
        if(!open){
            open = true;
        }
    }
        
    public void endTurn(){
        
    }
}
