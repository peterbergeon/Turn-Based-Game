import java.awt.Graphics2D;
/**
 * Write a description of class Draw here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Storage
{
    private Floor currentFloor;
    private Room currentRoom;
    private Tiles currentTiles;
    private int roomHeight;
    private int roomWidth;
    private int centerX;
    private int centerY;
    private int topDoor;
    public void Draw(Floor f, int w, int h){
        currentFloor = f;
        centerX = w / 2 - 30;
        centerY = h / 2 - 30;
        currentRoom = currentFloor.getRoom();
        currentTiles = currentRoom.getCurrentTiles();
    }

    public void draw(Graphics2D graphics2){
        int tilesHorizontal = 0;
        int tilesVertical = 0;
        int CRIR = currentTiles.getCRIR();
        int CCIR = currentTiles.getCCIR();
        if(currentRoom.getTopDoor().isOpen()){
            drawTopRoom(graphics2);
        }
        if(currentRoom.getBotDoor().isOpen()){
            drawBotRoom(graphics2);
        }
        if(currentRoom.getLeftDoor().isOpen()){
            drawLeftRoom(graphics2);                               
        }
        if(currentRoom.getRightDoor().isOpen()){
            drawRightRoom(graphics2);
        }       
        for(int i = 0; i < currentRoom.getWidth(); i++){
            for(int k = 0; k < currentRoom.getHeight(); k++){
                if(i == CRIR && k == CCIR){
                    currentRoom.getRoom()[i][k].draw(graphics2, centerX, centerY);
                }
                else {
                    tilesHorizontal = i - CCIR;
                    tilesVertical = k - CRIR;
                    currentRoom.getRoom()[i][k].draw(graphics2, centerX + 60 * tilesHorizontal, centerY + 60 * tilesVertical);
                }
            }
        }
    }

    public void drawTopRoom(Graphics2D graphics2){
        if(currentRoom.getCRIF() > 0){
            Room topRoom = currentFloor.getFloor()[currentRoom.getCRIF() - 1][currentRoom.getCCIF()];
            int topLeftHorizontal = (currentRoom.getTopDoor().getCCIR() - currentTiles.getCCIR()) - topRoom.getBotDoor().getCCIR();//looks at how many spaces left of center is topRooms left edge, should always be negative
            int topLeftVertical = -1 * (currentRoom.getCurrentTiles().getCRIR() +  topRoom.getHeight() - 1);
            for(int i = 0; i < topRoom.getWidth(); i++){
                for(int k = 0; k < topRoom.getHeight(); k++){
                    topRoom.getRoom()[i][k].draw(graphics2, centerX + 60 * (topLeftHorizontal + i), centerY + 60 * (topLeftVertical + k));
                }
            }
        }
    }

    public void drawBotRoom(Graphics2D graphics2){
        if(currentRoom.getCRIF() < 15){
            Room botRoom = currentFloor.getFloor()[currentRoom.getCRIF() + 1][currentRoom.getCCIF()];
            int topLeftHorizontal = (currentRoom.getBotDoor().getCCIR() - currentTiles.getCCIR()) - botRoom.getTopDoor().getCCIR();//looks at how many spaces left of center is topRooms left edge, should always be negative
            int topLeftVertical = (currentRoom.getHeight() - currentTiles.getCRIR() - 1);
            for(int i = 0; i < botRoom.getWidth(); i++){
                for(int k = 0; k < botRoom.getHeight(); k++){
                    botRoom.getRoom()[i][k].draw(graphics2, centerX + 60 * (topLeftHorizontal + i), centerY + 60 * (topLeftVertical + k));
                }
            }
        }
    }

    public void drawLeftRoom(Graphics2D graphics2){
        if(currentRoom.getCCIF() > 0){
            Room leftRoom = currentFloor.getFloor()[currentRoom.getCRIF()][currentRoom.getCCIF() - 1];
            int topLeftHorizontal = -1 * (currentRoom.getCurrentTiles().getCCIR() + leftRoom.getWidth() - 1);
            int topLeftVertical = (currentRoom.getLeftDoor().getCRIR() - currentTiles.getCRIR()) - leftRoom.getRightDoor().getCRIR();
            for(int i = 0; i < leftRoom.getWidth(); i++){
                for(int k = 0; k < leftRoom.getHeight(); k++){
                    leftRoom.getRoom()[i][k].draw(graphics2, centerX + 60 * (topLeftHorizontal + i), centerY + 60 * (topLeftVertical + k));
                }
            }
        }
    }

    public void drawRightRoom(Graphics2D graphics2){
        if(currentRoom.getCCIF() < 15){
            Room rightRoom = currentFloor.getFloor()[currentRoom.getCRIF()][currentRoom.getCCIF() + 1];
            int topLeftHorizontal = (currentRoom.getWidth() - currentRoom.getCurrentTiles().getCCIR() - 1);
            int topLeftVertical = ((currentRoom.getRightDoor().getCRIR() - currentTiles.getCRIR()) - rRoom.getRightDoor().getCRIR() - 1);
            for(int i = 0; i < rightRoom.getWidth(); i++){
                for(int k = 0; k < rightRoom.getHeight(); k++){
                    topRoom.getRoom()[i][k].draw(graphics2, centerX + 60 * (topLeftHorizontal + i), centerY + 60 * (topLeftVertical + k));
                }
            }
        }
    }
}
