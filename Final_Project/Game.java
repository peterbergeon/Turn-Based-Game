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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent; 
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
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
        
        MapComponent play = new MapComponent(width, height);
        
        class TimeListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                mouseX = (int)(MouseInfo.getPointerInfo().getLocation().getX() - getLocation().getX() - 3);
                mouseY = (int)(MouseInfo.getPointerInfo().getLocation().getY() - getLocation().getY() - 25);
                comp.updateMouse(mouseX, mouseY);
                comp.repaint();
            }
        }

        class KeyboardListener implements KeyListener {
            /**
             * Updates which keys are currently pressed.
             * 
             * @param  e  key pressed on the keyboard
             * @return    void
             */
            public void keyPressed(KeyEvent e)
            {
                char c = e.getKeyChar();
            }

            /**
             * Updates when a key is released.
             * 
             * @param  e  key released from the keyboard
             * @return    void
             */
            public void keyReleased(KeyEvent e) {
                char c = e.getKeyChar();
            }

            /**
             * Updates when a key is typed.
             * 
             * @param  e  key typed on the keyboard
             * @return    void
             */
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
            }
        }
        
        class MousePressListener implements MouseListener
        {
            /**
             * Updates when the mouse button is pressed.
             * 
             * @param  event  mouse button press
             * @return        void
             */
            public void mousePressed(MouseEvent event)
            {

            }

            /**
             * Updates when the mouse button is released.
             * 
             * @param  event  mouse button is released
             * @return        void
             */
            public void mouseReleased(MouseEvent event) {
                comp.click();
            }

            public void mouseClicked(MouseEvent event) {}

            public void mouseEntered(MouseEvent event) {}

            public void mouseExited(MouseEvent event) {}
        }
        play.setPreferredSize(new Dimension(width, height));
        play.addKeyListener(new KeyboardListener());
        play.addMouseListener(new MousePressListener());
        play.setBounds(0, 0, width, height);
        play.setFocusable(true);
        play.setVisible(true);

        final int DELAY = 1000 / 60;//60 frames per second
        Timer t = new Timer(DELAY, new TimeListener());
        t.start();

        panel.setLayout(null);

        panel.add(play);
        this.add(panel);

        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setVisible(true);

        setResizable(false);
        play.requestFocus();
    }
}
