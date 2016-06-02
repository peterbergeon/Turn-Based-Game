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
                if((row == 0 || row == 1) && col == l / 2){
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
                    if  (Math.abs(closest - secClosest) < 2)
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

        //clean left
        for(int i = 2; i < w - 1; i++){
            for(int k = 1; k < l - 2; k++){
                if(map[i + 1][k - 1].getColor() == 100 && map[i - 1][k - 1].getColor() == 100 && map[i][k - 1].getColor() == 100 && map[i][k + 1].getColor() != 100){
                    map[i][k] = new Tile(i + r, k + c, 15,3);//floor
                    map[i][k].room(); 
                }
            }
        }

        //clean top
        for(int i = w - 2; i > 0; i--){
            for(int k = 2; k < l - 1; k++){
                if(map[i - 1][k + 1].getColor() == 100 && map[i - 1][k].getColor() == 100 && map[i - 1][k - 1].getColor() == 100 && map[i + 1][k].getColor() == 15){
                    map[i][k] = new Tile(i + r, k + c, 15,3);//floor
                    map[i][k].room(); 
                }
            }
        }

        //fill holes
        for(int i = 1; i < w - 1; i++){
            for(int k = 1; k < l - 1; k++){
                if(map[i][k + 1].getColor() == 100 && map[i + 1][k].getColor() == 100 && map[i + 1][k + 1].getColor() == 15 && map[i][k].getColor() == 15){
                    map[i][k] = new Tile(i + r, k + c, 100,10000);//wall
                    map[i][k].room(); 
                }

                else if(map[i][k + 1].getColor() == 100 && map[i - 1][k].getColor() == 100 && map[i - 1][k + 1].getColor() == 15 && map[i][k].getColor() == 15){
                    map[i][k] = new Tile(i + r, k + c, 100,10000);//wall
                    map[i][k].room(); 
                }
            }
        }

        for(int i = 0; i < points.size(); i++){
            int which = (int)(Math.random() * 4);
            int x = 0;
            int y = 0;
            //up
            if(which == 0){
                int h = 0;
                x = (int)points.get(i).getX();
                y = (int)points.get(i).getY();
                while(y >= 0 && h == 0){
                    if(y == 0){
                        map[x][y] = new Tile(x + r, y + c, 16, m / 2);//door
                        map[x][y].room(); 
                        break;
                    }
                    else if(map[x][y].getColor() == 100){
                        map[x][y] = new Tile(x + r, y + c, 16, m / 2);//door
                        map[x][y].room();   
                        h++;
                    }
                    y--;
                }
            }
            //down
            if(which == 1){
                int h = 0;
                x = (int)points.get(i).getX();
                y = (int)points.get(i).getY();
                while(y < l && h == 0){
                    if(y == l - 1){
                        map[x][y] = new Tile(x + r, y + c, 16, m / 2);//door
                        map[x][y].room();
                        break;
                    }
                    else if(map[x][y].getColor() == 100){
                        map[x][y] = new Tile(x + r, y + c, 16, m / 2);//door
                        map[x][y].room();   
                        h++;
                    }
                    y++;
                }
            }
            //left
            if(which == 2){
                int h = 0;
                x = (int)points.get(i).getX();
                y = (int)points.get(i).getY();
                while(x >= 0 && h == 0){
                    if(x == 0){
                        map[x][y] = new Tile(x + r, y + c, 16, m / 2);//door
                        map[x][y].room();  
                        break;
                    }
                    else if(map[x][y].getColor() == 100){
                        map[x][y] = new Tile(x + r, y + c, 16, m / 2);//door
                        map[x][y].room();   
                        h++;
                    }
                    x--;
                }
            }

            if(which == 3){
                int h = 0;
                x = (int)points.get(i).getX();
                y = (int)points.get(i).getY();
                while(x < w && h == 0){
                    if(x == w - 1){
                        map[x][y] = new Tile(x + r, y + c, 16, m / 2);//door
                        map[x][y].room();   
                        break;
                    }
                    else if(map[x][y].getColor() == 100){
                        map[x][y] = new Tile(x + r, y + c, 16, m / 2);//door
                        map[x][y].room();   
                        h++;
                    }
                    x++;
                }
            }
        }
       

        //fill hole
        // 
        //         for(int ra = 1; ra < w - 1; ra++){
        //             for(int ca = 1; ca < l - 1; ca++){
        //                 if(map[ra + 1][ca].getColor() == 100 && map[ra + 1][ca + 1].getColor() == 100 && map[ra + 1][ca - 1].getColor() == 100){
        //                     map[ra][ca] = new Tile(ra + r, ca + c, 15,3);//floor
        //                     map[ra][ca].room(); 
        //                 }
        //             }
        //         }
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
