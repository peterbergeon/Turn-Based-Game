import java.awt.Graphics2D;
/**
 * Write a description of class Draw here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Draw
{
    private Floor currentFloor;
    private Room currentRoom;
    private Tiles currentTiles;
    private int roomHeight;
    private int roomWidth;
    private int centerX;
    private int centerY;
    public Draw(Floor f, int w, int h){
        currentFloor = f;
        centerX = w / 2 - 30;
        centerY = h / 2 - 30;
        currentRoom = currentFloor.getRoom();
        currentTiles = currentRoom.hasHero();
    }

    public void draw(Graphics2D g){
        int CRIF = currentRoom.getCRIF();
        int CCIF = currentRoom.getCCIF();
        int tilesHorizontal = 0;
        int tilesVertical = 0;
        int CRIR = currentTiles.getCRIR();
        int CCIR = currentTiles.getCCIR();
        int doorHorizontal = 0;
        int doorVertical = 0;

        for(int i = 0; i < row; i++){
            for (int k = 0; k < col; k++){
                if(currentRoom.isAdjacentAndOpen(floor[i][k])){
                    
                    for(int i = 0; i < sizeW; i++){
                        for(int k = 0; k < sizeL; k++){
                            Room currentR = floor[i][k];
                            Tiles 
                            room[i][k].draw(graphics2);
                        }
                    }
                }
                else if(currentRoom.getCCIF() == k && currentRoom.getCRIF() == i){
                    for(int j = 0; i < sizeW; i++){
                        for(int l = 0; k < sizeL; k++){
                            Room currentR = floor[i][k];
                            if(room[j][l].getCharacter().hasHero()){
                                currentR[j][l].draw(graphics2, centerX, centerY);
                            }
                            else {
                                tilesHorizontal = l - CCIR;
                                tilesVertical = j - CRIR;
                                currentR[j][l].draw(graphics2, centerX + 60 * tilesHorizontal, centerY + 60 * tilesVertical);
                            }
                        }
                    }
                }
            }
        }
    }
}
