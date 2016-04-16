import javax.swing.JFrame;

/**
 * Write a description of class GameFrame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameFrame extends JFrame
{
    public static void main(String[] args) {
        JFrame frame = new GameFrame();
    }

    public GameFrame() {
        super();

        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
