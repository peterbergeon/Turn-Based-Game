
/**
 * Write a description of class Room here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Room
{
    private Tile[][] map; 
    private String str;
    private int mv;
    
    public Room(int r, int c, int w, int l, String t, int m){
        map = new Tile[w][l];
        str = t;
        mv = m;
        if(t.equals("Simple House")){
            for(int row = 0; row < w; row++){
                for(int col = 0; col < l; col++){
                    if(row == 0 && col == l / 2){
                        map[row][col] = new Tile(row + r, col + c, 16, m / 2);//door
                        map[row][col].room();
                    }
                    else if(row == 0 || col == 0 || row == w - 1 || col == l - 1){
                        map[row][col] = new Tile(row + r, col + c, 100, 10000);//wall
                        map[row][col].room();
                    }
                    else{
                        map[row][col] = new Tile(row + r, col + c, 15,3);//floor
                        map[row][col].room();
                    }
                }
            }
        }
    }  
    
    public Room(String str, int m){
        this(1,1,1,1,str,m);
    }
    
    public Tile[][] getMap(){
        return map;
    }
    
    public void changeRoom(int r, int c, int w, int l){
        Room other = new Room(r,c,w,l,this.str,this.mv);
        map = other.getMap();
    }

    public Tile getTile(int row, int col){
        return map[row][col];
    }
}
