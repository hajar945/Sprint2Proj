import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Main {
    public static void main(String[] args) {
        // Making the window frame and titling it
        JFrame window = new JFrame("Peg Solitaire");
        
        // Create the UI that builds the board and buttons
        UI gameUI = new UI();
        // Put the 
        window.setContentPane(gameUI);
        
        window.pack();
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation((screensize.width - window.getWidth())/2,
                           (screensize.height - window.getHeight())/2);
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);  
        window.setVisible(true);
    }
}