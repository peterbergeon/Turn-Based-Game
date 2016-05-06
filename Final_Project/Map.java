
/**
 * Write a description of class Map here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Map
{
    private Box[][] location;
    private int centerX;
    private int centerY;
    public Map(){
        location = new Box[50][50];
        centerX = 25;
        centerY = 25;
    }
    public Map(int n, int m, int x, int y){
        location = new Box[n][m];
        centerX = x;
        centerY = y;
    }
    public Map(int n, int m, Box b){
        location = new Box[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                location[i][j] = b;
            }
        }
    }
}
