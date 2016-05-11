import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Point;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.awt.MouseInfo;
import javax.swing.JPanel;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.net.URL;
import java.io.File;
import java.io.IOException;
/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Game extends JFrame
{
    private int mouseX;
    private int mouseY;

    public static void main(String[] args){
        Game game = new Game();
    }

    public Game(){
        super(); //calling constructer of JFrame class, which extends Frame

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int width = (int)screenSize.getWidth();
        final int height = (int)screenSize.getHeight();
        this.setSize(width, height);
        this.setTitle("2D-Game-Instance");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel panel = new JPanel(true);

        MapComponent play = new MapComponent(width, height, 2000, 2000, new Character("JOAT",5));


        class MousePressListener implements MouseListener
        {
            public void mouseReleased(MouseEvent event) {
                play.click((int)(MouseInfo.getPointerInfo().getLocation().getX() - getLocation().getX() - 3),
                    (int)(MouseInfo.getPointerInfo().getLocation().getY() - getLocation().getY() - 25));
                play.repaint();
            }

            public void mousePressed(MouseEvent event)
            {

            }

            public void mouseClicked(MouseEvent event) {}

            public void mouseEntered(MouseEvent event) {}

            public void mouseExited(MouseEvent event) {}
        }
        play.setPreferredSize(new Dimension(width, height));
        play.setBounds(0, 0, width, height);
        play.setFocusable(true);
        play.setVisible(true);
        play.addMouseListener(new MousePressListener());

        panel.setLayout(null);

        panel.add(play);
        this.add(panel);

        this.setVisible(true);

        setResizable(false);
        play.requestFocus();
    }
}
