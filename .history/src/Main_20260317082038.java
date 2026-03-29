import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Main {
    public static void main(String[] args) {
        // Making the window frame and titling it
        JFrame window = new JFrame("Peg Solitaire");
        
        // Create the UI that builds the board and buttons
        UI gameUI = new UI();
        // Put the UI inside the empty window frame
        window.setContentPane(gameUI);
        // Everything fits perfercly in the window with no extra space
        window.pack();
        // Calculates the monitor's screen so the came can open in the center
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation((screensize.width - window.getWidth())/2,
                           (screensize.height - window.getHeight())/2);
        
        // Program will shut down completely when X is clicked
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);  
        window.setVisible(true);
    }
}