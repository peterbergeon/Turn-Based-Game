
/**
 * Write a description of class Head here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pants
{
    private int pantsDF;
    private int valueP;
    private int durabilityP;
    public Pants(){
        pantsDF = 10;
        valueP = 0;
        durabilityP = 5;
    }

    public void damaged(){
        durabilityP--;
    }
}
