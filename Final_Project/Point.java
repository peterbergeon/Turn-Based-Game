
/**
 * Write a description of class Point here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Point
{
    private double x;
    private double y;
    private Tile model;
    
    public Point(double x, double y, Tile model)
    {
        this.x = x;
        this.y = y;
        this.model = model;
    }
    
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
}
