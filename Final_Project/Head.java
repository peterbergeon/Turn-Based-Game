
/**
 * Write a description of class Head here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Head
{
    private int headDF;
    private int valueH;
    private int durabilityH;
    public Head(){
        headDF = 10;
        valueH = 0;
        durabilityH = 5;
    }

    public void damaged(){
        durabilityH--;
    }
}
