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
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameRunner extends JFrame
{
    private int mouseX;
    private int mouseY;

    public static void main(String[] args){
        GameRunner game = new GameRunner();
    }

    public GameRunner(){
        super(); //calling constructer of JFrame class, which extends Frame

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int width = (int)screenSize.getWidth();
        final int height = (int)screenSize.getHeight();
        this.setSize(width, height);
        this.setTitle("2D-Game-Instance");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel panel = new JPanel(true);
        LoadingComponent load = new LoadingComponent();
        panel.add(load);
        load.setPreferredSize(new Dimension(width, height));
        load.setBounds(0, 0, width, height);
        this.add(panel);
        load.setFocusable(true);

        panel.setLayout(null);

        setResizable(false);
        load.requestFocus();
        this.setVisible(true);
        load.setPreferredSize(new Dimension(width, height));
        load.setBounds(0, 0, width, height);

        load.repaint();

        MapComponent play = new MapComponent(width, height, 600, 600, new Character("JOAT",5));
        panel.add(play);
        play.createMap();
        play.createCharacter();
        play.setFocusable(true);
        play.requestFocus();
        this.setVisible(true);
        play.createRoad();
        play.createRoom();
        play.createWall();
        play.createCharacter();        
        play.createEnemys();
        play.setPreferredSize(new Dimension(width, height));
        play.setBounds(0, 0, width, height);
        play.fixDistance(play.getCurrentTile(),60);
        play.repaint();
        panel.remove(load);
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


        class KeyboardListener implements KeyListener {
            public void keyPressed(KeyEvent e){
                int k = e.getKeyCode();
                if(k == KeyEvent.VK_SPACE){
                    play.endTurn();
                }

                else if(k == KeyEvent.VK_W){
                    play.click(width/2, height/2 - 60);
                }

                else if(k == KeyEvent.VK_A){
                    play.click(width/2 - 60, height/2);
                }

                else if(k == KeyEvent.VK_S){
                    play.click(width/2, height/2 + 60);
                }

                else if(k == KeyEvent.VK_D){
                    play.click(width/2 + 60, height/2);
                }

                else if(k == KeyEvent.VK_E){
                    play.click(width/2 + 60, height/2 - 60);
                }

                else if(k == KeyEvent.VK_Z){
                    play.click(width/2 - 60, height/2 + 60);
                }

                else if(k == KeyEvent.VK_Q){
                    play.click(width/2 - 60, height/2 - 60);
                }

                else if(k == KeyEvent.VK_C){
                    play.click(width/2 + 60, height/2 + 60);
                }

                else if(k == KeyEvent.VK_NUMPAD8){
                    play.click(width/2, height/2 - 60);
                }

                else if(k == KeyEvent.VK_NUMPAD4){
                    play.click(width/2 - 60, height/2);
                }

                else if(k == KeyEvent.VK_NUMPAD5){
                    play.click(width/2, height/2 + 60);
                }

                else if(k == KeyEvent.VK_NUMPAD2){
                    play.click(width/2, height/2 + 60);
                }

                else if(k == KeyEvent.VK_NUMPAD6){
                    play.click(width/2 + 60, height/2);
                }

                else if(k == KeyEvent.VK_NUMPAD9){
                    play.click(width/2 + 60, height/2 - 60);
                }

                else if(k == KeyEvent.VK_NUMPAD1){
                    play.click(width/2 - 60, height/2 + 60);
                }

                else if(k == KeyEvent.VK_NUMPAD7){
                    play.click(width/2 - 60, height/2 - 60);
                }

                else if(k == KeyEvent.VK_NUMPAD3){
                    play.click(width/2 + 60, height/2 + 60);
                }
                
                else if(k == KeyEvent.VK_J){
                    play.D((int)(MouseInfo.getPointerInfo().getLocation().getX() - getLocation().getX() - 3),
                    (int)(MouseInfo.getPointerInfo().getLocation().getY() - getLocation().getY() - 25));
                }
                
                play.repaint();
            }

            public void keyReleased(KeyEvent e){}

            public void keyTyped(KeyEvent e){}           
        }
        play.addKeyListener(new KeyboardListener());
        play.addMouseListener(new MousePressListener());
    }
}
