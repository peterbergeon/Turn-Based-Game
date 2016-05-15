
/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Character
{  
    public Enemy(){
        super("Rat",2,1,1);
    }
    
    public Enemy(String str, int m, int h, int d){
        super(str,m,h,d);
    }    
    
    public void kill(){
        this.getHome().addCharacter(null);
    }
}
