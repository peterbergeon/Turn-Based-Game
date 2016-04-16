
/**
 * Write a description of class Map here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Map
{
    private Box[][] location;
    public Map(){
        location = new Box[50][50];
    }
    public Map(int n, int m){
        location = new Box[n][m];
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
