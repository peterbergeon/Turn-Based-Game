import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
/**
 * Write a description of class LoadingComponent here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LoadingComponent extends JComponent
{
    private BufferedImage img;
    public LoadingComponent(){
                try {
            img = ImageIO.read(new File("loading.png"));
        } catch (IOException e) {
        }
    }

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)(g);
        g2.drawImage(img,0,0,null);
    }
}
