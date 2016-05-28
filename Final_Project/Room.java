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

        for(int ra = 1; ra < w - 1; ra++){
            for(int ca = 1; ca < l - 1; ca++){
                if(map[ra + 1][ca].getColor() == 100 && map[ra + 1][ca + 1].getColor() == 100 && map[ra + 1][ca - 1].getColor() == 100){
                    map[ra][ca] = new Tile(ra + r, ca + c, 15,3);//floor
                    map[ra][ca].room(); 
                }
            }
        }

        for(int z = 1; z < w - 1; z++){
            for(int x = 1; x < l - 1; x++){
                if(map[z][x].getColor() != 100){                               
                    if(map[z + 1][x].getColor()  == 100){
                        map[z][x].isNotRoom();
                    }
                    else if(map[z - 1][x].getColor()  == 100){
                        map[z][x].isNotRoom();
                    }
                    else if(map[z + 1][x + 1].getColor()  == 100){
                        map[z][x].isNotRoom();
                    }
                    else if(map[z][x + 1].getColor()  == 100){
                        map[z][x].isNotRoom();
                    }
                    else if(map[z - 1][x + 1].getColor()  == 100){
                        map[z][x].isNotRoom();
                    }
                    else if(map[z + 1][x - 1].getColor()  == 100){
                        map[z][x].isNotRoom();
                    }
                    else if(map[z][x - 1].getColor()  == 100){
                        map[z][x].isNotRoom();
                    }
                    else if(map[z - 1][x - 1].getColor()  == 100){
                        map[z][x].isNotRoom();
                    }  
                }
            }
        }

        for(int z = 1; z < w - 1; z++){
            for(int x = 1; x < l - 1; x++){
                int i = 0;
                if(!map[z + 1][x].isRoom()){
                    i++;
                }
                else if(!map[z - 1][x].isRoom()){
                    i++;
                }
                else if(!map[z + 1][x + 1].isRoom()){
                    i++;
                }
                else if(!map[z][x + 1].isRoom()){
                    i++;
                }
                else if(!map[z - 1][x + 1].isRoom()){
                    i++;
                }
                else if(!map[z + 1][x - 1].isRoom()){
                    i++;
                }
                else if(!map[z][x - 1].isRoom()){
                    i++;
                }
                else if(!map[z - 1][x - 1].isRoom()){
                    i++;
                }  

                if(i > 3){
                    map[z][x] = new Tile(z + r, x + c, 100, 10000);//wall
                    map[z][x].room();
                }
            }
        }

        for(int z = 1; z < w - 1; z++){
            for(int x = 1; x < l - 1; x++){
                map[z][x].room();
            }
        }

        for(int z = 1; z < w - 1; z++){
            for(int x = 1; x < l - 1; x++){
                if(map[z + 1][x].getColor() == 100 && (map[z][x + 1].getColor() == 100 || map[z][x - 1].getColor() == 100)){
                    map[z][x] = new Tile(z + r, x + c, 100, 10000);//wall
                    map[z][x].room();
                }
            }
        }

        for(int z = 1; z < w - 1; z++){
            for(int x = 1; x < l - 1; x++){
                if(map[z][x].getColor() == 100 && map[z + 1][x].getColor() != 100 && map[z - 1][x].getColor() != 100 && map[z][x - 1].getColor() != 100){
                    map[z][x] = new Tile(z + r, x + c, 15,3);//floor
                    map[z][x].room();
                }
            }
        }

        for(int z = 1; z < w - 1; z++){
            for(int x = 1; x < l - 1; x++){
                if(map[z][x + 1].getColor() == 100 && map[z][x - 1].getColor() == 100){
                    map[z][x] = new Tile(z + r, x + c, 100, 10000);//wall
                    map[z][x].room();
                }
            }
        }
        double ran = 0;
        for(int z = 1; z < w - 1; z++){
            for(int x = 1; x < l - 1; x++){
                ran = Math.random() * 100;
                if(ran < 20 && map[z][x].getColor() == 100){
                    map[z][x] = new Tile(z + r, x + c, 16, m / 2);//door
                    map[z][x].room();
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

    //         public void addDoors(Room needsFix){
    //         int i = 0;
    //         while(needsFix.getTile(i, 1).getColor() == 100){
    //             i++;
    //         }
    //         Tile orgin = needsFix.getTile(i,1);
    //         fixDistance(orgin,5);
    //         
    //         int ran1 = 0; 
    //         int ran2 = 0; Math.random() * needsFix.getMap()[0].length;
    //         while(badRoom(needsFix)){
    //             ran1 = (int)(Math.random() * needsFix.getMap().length);
    //             ran2 = (int)(
    //         }
    //     }
    //     
    //     public boolean badRoom(Room fix){
    //         for(int i = 0; i < fix.getMap().length; i++){
    //             for(int k = 0; k < fix.getMap()[0].length; k++){
    //                 if(fix.getTile(i,k).getColor() != 100 && fix.getTile(i,k).getDistance() > 9999){
    //                     return false;
    //                 }
    //             }
    //         }
    //         return true;
    //     }
}
