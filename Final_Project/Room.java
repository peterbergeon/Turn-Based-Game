import java.util.ArrayList;
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
    private ArrayList<Point> points;

    public Room(int r, int c, int w, int l, int m){
        map = new Tile[w][l];
        mv = m;
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

    public Room(int r, int c, int w, int l, int m, int checker){
        map = new Tile[w][l];
        mv = m;
        points = new ArrayList<Point>();
        for(int i = 0; i < w * l; i += 100)
        {
            double x = Math.random() * w;
            double y = Math.random() * l;
            points.add(new Point(x,y, new Tile()));
        }
        double closest = 100;
        double secClosest = 101;
        double distance;
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
                else {
                    closest = 100;
                    secClosest = 101;
                    for(int i = 0; i < points.size(); i++)
                    {
                        distance = Math.sqrt((Math.abs(row - points.get(i).getX())
                                * Math.abs(row - points.get(i).getX())) + 
                            ((Math.abs(col - points.get(i).getY()) * 
                                    Math.abs(col - points.get(i).getY()))));
                        if(distance < closest)
                        {
                            secClosest = closest;
                            closest = distance;
                        }
                        else if(distance < secClosest)
                        {
                            secClosest = distance;
                        }
                    }
                    if  (Math.abs(closest - secClosest) < 1)
                    {
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

        public Room(int m){
        this(1,1,1,1,m);
    }

    public Room(int m, int x){
        this(1,1,1,1,m,1);
    }

    public Tile[][] getMap(){
        return map;
    }

    public void changeRoom(int r, int c, int w, int l){
        Room other = new Room(r,c,w,l,this.mv);
        map = other.getMap();
    }

    public void changeRoom(int r, int c, int w, int l, int check){
        Room other = new Room(r,c,w,l,this.mv,0);
        map = other.getMap();
    }

    public Tile getTile(int r, int c){
        return map[r][c];
    }
}
