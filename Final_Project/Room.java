
/**
 * Write a description of class Room here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Room
{
    private Tile[][] map;  
    public Room(int r, int c, int w, int l, String t, int m){
        map = new Tile[w][l];
        if(t.equals("Simple House")){
            for(int row = 0; row < w; row++){
                for(int col = 0; col < l; col++){
                    if(row == 0 && col == l / 2){
                        map[row][col] = new Tile(row + r, col + c, 16, m / 2);//door
                    }
                    else if(row == 0 || col == 0 || row == w - 1 || col == l - 1){
                        map[row][col] = new Tile(row + r, col + c, 100, 100);//wall
                    }
                    else{
                        map[row][col] = new Tile(row + r, col + c, 15,1);//floor
                    }
                }
            }
        }
    }

    public Tile getTile(int row, int col){
        return map[row][col];
    }
}
